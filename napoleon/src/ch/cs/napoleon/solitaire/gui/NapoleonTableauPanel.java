package ch.cs.napoleon.solitaire.gui;

import ch.cs.gui.MessageProperties;

import ch.cs.napoleon.gui.NapoleonMessage;
import ch.cs.napoleon.score.ScoreTable;
import ch.cs.napoleon.solitaire.Help;
import ch.cs.napoleon.solitaire.NapoleonRow;
import ch.cs.napoleon.solitaire.NapoleonTableau;
import ch.cs.napoleon.solitaire.UndoMove;

import ch.cs.solitaire.Card;
import ch.cs.solitaire.gui.PileInfo;
import ch.cs.solitaire.gui.TableauPanel;

import ch.cs.time.UsedTimeHandler;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Timer;


/**
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public class NapoleonTableauPanel extends TableauPanel
{
    //~ Static fields/initializers ---------------------------------------------

    /** @TODO: javadoc! */
    private static final int MILLIS_IN_SEC = 1000;

    /** @TODO: javadoc! */
    private static final String NR_OF_MOVES = "napoleontableaupanel.nrofmoves"; //$NON-NLS-1$

    /** @TODO: javadoc! */
    private static final String TIME_USED = "napoleontableaupanel.timeused"; //$NON-NLS-1$

    /** @TODO: javadoc! */
    private static final String SCORE_TABLE = "napoleontableaupanel.scoretable"; //$NON-NLS-1$

    /** @TODO: javadoc! */
    private static final String SELECTED_CARD =
        "napoleontableaupanel.selectedcard"; //$NON-NLS-1$

    /** @TODO: javadoc! */
    private static final String SUSPENDED = "napoleontableaupanel.suspended"; //$NON-NLS-1$

    /** @TODO: javadoc! */
    private static final String SETTINGS_ID = "napoleontableaupanel.settingsid"; //$NON-NLS-1$

    /** @TODO: javadoc! */
    private static final Point INFO_SLOT = new Point(10, 0);

    //~ Instance fields --------------------------------------------------------

    /** @TODO: javadoc! */
    private CardSelection selectedCard;

    /** @TODO: javadoc! */
    private NapoleonTableau tableau;

    /** @TODO: javadoc! */
    private ScoreTable scoreTable;

    /** @TODO: javadoc! */
    private Timer updateTimer;

    /** @TODO: javadoc! */
    private boolean pileFront;

    /** @TODO: javadoc! */
    private boolean suspended;

    /** @TODO: javadoc! */
    private int nrOfMoves;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new NapoleonTableauPanel object.
     *
     * @throws Exception javadoc!
     */
    public NapoleonTableauPanel() throws Exception
    {
        super();
        this.setDoubleBuffered(true);
        this.setTableau(new NapoleonTableau());
        this.setScoreTable(new ScoreTable());
        this.setPileFront(getBoolean("pilefront")); //$NON-NLS-1$
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.createUpdateTimer();
        this.addMouseListener(new MouseHandler(this));
        new DnDHandler(this);
        this.getCardSuits();
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * @TODO: javadoc!
     *
     * @param map javadoc!
     */
    public final void setData(final Map map)
    {
        this.init();

        try
        {
            this.setNrOfMoves(((Integer) map.get(NR_OF_MOVES)).intValue());
            this.setScoreTable((ScoreTable) map.get(SCORE_TABLE));
            this.setSelectedCard((CardSelection) map.get(SELECTED_CARD));
            this.setSet((String) map.get(SETTINGS_ID));
            this.setSuspended(((Boolean) map.get(SUSPENDED)).booleanValue());

            if (this.getSuspended())
            {
                UsedTimeHandler.suspend();
                this.stopUpdateTimer();
            }

            UsedTimeHandler.setTimeUsed(((Integer) map.get(TIME_USED)).intValue());
            this.getTableau().setData(map);
        }
        catch (Throwable e)
        {
            this.newGame();
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final Map getData()
    {
        Map map = new HashMap();
        map.put(TIME_USED, new Integer(UsedTimeHandler.getTimeUsed()));
        map.put(NR_OF_MOVES, new Integer(this.getNrOfMoves()));
        UsedTimeHandler.updateTimeUsed();
        map.put(SCORE_TABLE, this.getScoreTable());
        map.put(SELECTED_CARD, this.getSelectedCard());
        map.put(SETTINGS_ID, this.getCurrentSetId());
        map.put(SUSPENDED, Boolean.valueOf(this.getSuspended()));
        map.putAll(this.getTableau().getData());

        return map;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final Dimension getPreferredSize()
    {
        return new Dimension((
                (this.getCardSize().width + this.getCardSlotOffset()) * 11
            ) + this.getCardSlotOffset()
            + (2 * (this.getMaxPileSize() - 1) * this.getPileOffset()),
            (
                ((this.getCardSize().height + this.getCardSlotOffset()) * 6)
                + (2 * this.getCardSlotOffset())
            ) - 1);
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final boolean getSuspended()
    {
        return this.suspended;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final boolean canRedo()
    {
        return this.getTableau().canRedo();
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final boolean canUndo()
    {
        return this.getTableau().canUndo();
    }

    /**
     * @TODO: javadoc!
     */
    public final void clearScoreTable()
    {
        NapoleonMessage.clearScoreTable(this, this.getScoreTable());
    }

    /**
     * @TODO: javadoc!
     */
    public final void newGame()
    {
        this.init();
        this.getTableau().newGame();
        this.repaint();
    }

    /**
     * @TODO: javadoc!
     */
    public final void redo()
    {
        Graphics graphics = this.getGraphics();

        if (this.canRedo() && !this.getSuspended())
        {
            UndoMove undoMove = this.getTableau().redo();

            if (undoMove != null)
            {
                CardSelection src = undoMove.getSource();
                CardSelection dst = undoMove.getDest();
                this.incrNrOfMoves();
                this.drawSlot(graphics, src.getSlot());
                this.drawSlot(graphics, dst.getSlot());
                updateStatus(graphics);
            }
        }
    }

    /**
     * @TODO: javadoc!
     */
    public final void restartGame()
    {
        this.init();
        this.getTableau().restartGame();
        this.repaint();
    }

    /**
     * @TODO: javadoc!
     */
    public final void resume()
    {
        if (this.getSuspended())
        {
            this.setSuspended(false);
            this.repaint();
            UsedTimeHandler.resume();
            this.startUpdateTimer();
        }
    }

    /**
     * @TODO: javadoc!
     */
    public final void showScoreTable()
    {
        NapoleonMessage.showScoreTable(this, this.getScoreTable());
    }

    /**
     * @TODO: javadoc!
     */
    public final void suspend()
    {
        if (!this.getSuspended())
        {
            UsedTimeHandler.suspend();
            this.stopUpdateTimer();
            this.setSuspended(true);
            this.repaint();
        }
    }

    /**
     * @TODO: javadoc!
     */
    public final void toggleSuspendResume()
    {
        if (this.getSuspended())
        {
            this.resume();
        }
        else
        {
            this.suspend();
        }
    }

    /**
     * @TODO: javadoc!
     */
    public final void undo()
    {
        Graphics graphics = this.getGraphics();

        if (this.canUndo() && !this.getSuspended())
        {
            UndoMove undoMove = this.getTableau().undo();

            if (undoMove != null)
            {
                CardSelection src = undoMove.getSource();
                CardSelection dst = undoMove.getDest();
                this.decrNrOfMoves();
                this.drawSlot(graphics, src.getSlot());
                this.drawSlot(graphics, dst.getSlot());
                this.updateStatus(graphics);
            }
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    protected final CardSelection getSelectedCard()
    {
        return this.selectedCard;
    }

    /**
     * @TODO: javadoc!
     *
     * @param location javadoc!
     *
     * @return javadoc!
     */
    protected final Point calculateSlotOffset(final Point location)
    {
        Point offset = this.getSlotLocation(this.getSelectedCard().getSlot());
        offset.translate(-(int) location.getX(), -(int) location.getY());

        return offset;
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     */
    protected final void drawDeck(final Graphics graphics)
    {
        Card card;
        graphics.clearRect(0, 0, this.getSize().width, this.getSize().height);

        if (!getSuspended())
        {
            card = this.getTableau().getFoundation(0).getFirst();
            this.drawCardInSlot(graphics, 6, 0, card);

            for (int i = 0; i < 4; i++)
            {
                card = this.getTableau().getFoundation(i).getLast();
                this.drawCardInSlot(graphics, 6, i + 1, card);
            }

            drawNapoleonRows(graphics);

            drawHelp(graphics);

            card = this.getTableau().getCellar().get();

            if (card != null)
            {
                this.drawCardInSlot(graphics, 6, 5, card);
            }

            this.drawCardSelection(graphics, this.getSelectedCard());
        }

        this.updateStatus(graphics);
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     * @param x javadoc!
     * @param y javadoc!
     * @param clickCount javadoc!
     */
    protected final void processSelection(final Graphics graphics, final int x,
        final int y, final int clickCount)
    {
        if (this.getSelectedCard() == null)
        {
            this.setSelectedCard(this.getSelectedCard(x, y));

            if (this.getSelectedCard() != null)
            {
                processSecondSelection(graphics, clickCount);
            }
        }
        else
        {
            processFirstSelection(graphics, x, y);
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     */
    protected final void updateProgress(final Graphics graphics)
    {
        this.drawTimeUsed(graphics);
    }

    /**
     * @TODO: javadoc!
     *
     * @param property javadoc!
     *
     * @return javadoc!
     */
    private static boolean getBoolean(final String property)
    {
        return MessageProperties.getBoolean("napoleontableaupanel", //$NON-NLS-1$
            property);
    }

    /**
     * @TODO: javadoc!
     *
     * @param nOMovs javadoc!
     */
    private void setNrOfMoves(final int nOMovs)
    {
        this.nrOfMoves = nOMovs;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private int getNrOfMoves()
    {
        return this.nrOfMoves;
    }

    /**
     * @TODO: javadoc!
     *
     * @param pileFrnt javadoc!
     */
    private void setPileFront(final boolean pileFrnt)
    {
        this.pileFront = pileFrnt;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private boolean getPileFront()
    {
        return this.pileFront;
    }

    /**
     * @TODO: javadoc!
     *
     * @param scorTab javadoc!
     */
    private void setScoreTable(final ScoreTable scorTab)
    {
        this.scoreTable = scorTab;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private ScoreTable getScoreTable()
    {
        return this.scoreTable;
    }

    /**
     * @TODO: javadoc!
     *
     * @param selectedCardSlot javadoc!
     *
     * @return javadoc!
     */
    private CardSelection getSelectRowCard(final Point selectedCardSlot)
    {
        if (selectedCardSlot.getX() == 6)
        {
            return new CardSelection(this.getTableau()
                                         .getFoundation((int) selectedCardSlot
                    .getY() - 1).getLast(), selectedCardSlot,
                CardSelection.FOUNDATION, (int) selectedCardSlot.getY() - 1);
        }
        else
        {
            int rowNr = 0;
            NapoleonRow napoleonRow = null;
            int last = 0;
            int slotWidth = 0;

            if (selectedCardSlot.getX() < 6)
            {
                rowNr = (int) selectedCardSlot.getY() - 1;
                napoleonRow = this.getTableau().getNapoleonRow(rowNr);
                last = napoleonRow.getBase().size() + 1;
                slotWidth = 6 - last;
            }
            else if (selectedCardSlot.getX() > 6)
            {
                rowNr = (int) selectedCardSlot.getY() + 3;
                napoleonRow = this.getTableau().getNapoleonRow(rowNr);
                last = napoleonRow.getBase().size() + 1;
                slotWidth = 6 + last;
            }

            if (last > Math.abs(6 - selectedCardSlot.getX()))
            {
                return null;
            }

            return new CardSelection(napoleonRow.getLast(),
                new Point(slotWidth, (int) selectedCardSlot.getY()),
                CardSelection.ROW, rowNr, last);
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @param selCrd javadoc!
     */
    private void setSelectedCard(final CardSelection selCrd)
    {
        this.selectedCard = selCrd;
    }

    /**
     * @TODO: javadoc!
     *
     * @param x javadoc!
     * @param y javadoc!
     *
     * @return javadoc!
     */
    private CardSelection getSelectedCard(final int x, final int y)
    {
        CardSelection cardSelection = null;

        if (this.getTableau() != null)
        {
            Point selectedCardSlot = this.getSlot(x, y);

            if (selectedCardSlot.getY() < 5)
            {
                cardSelection = getSelectRowCard(selectedCardSlot);
            }
            else if (selectedCardSlot.getY() == 5)
            {
                cardSelection = getSelectedHelpCard(selectedCardSlot);
            }
        }

        return cardSelection;
    }

    /**
     * @TODO: javadoc!
     *
     * @param selectedCardSlot javadoc!
     *
     * @return javadoc!
     */
    private CardSelection getSelectedHelpCard(final Point selectedCardSlot)
    {
        CardSelection cardSelection = null;

        if (selectedCardSlot.getX() == 6)
        {
            cardSelection =
                new CardSelection(this.getTableau().getCellar().get(),
                    selectedCardSlot, CardSelection.CELLAR);
        }
        else if ((selectedCardSlot.getX() != 5)
            && (selectedCardSlot.getX() != 7))
        {
            int helpNr = 0;
            int helpColNr = 0;

            if (selectedCardSlot.getX() < 5)
            {
                helpNr = 0;
                helpColNr = (int) selectedCardSlot.getX() - 1;
            }

            if (selectedCardSlot.getX() > 7)
            {
                helpNr = 1;
                helpColNr = (int) selectedCardSlot.getX() - 8;
            }

            Help help = this.getTableau().getHelp(helpNr);

            if (help.get(helpColNr) != null)
            {
                cardSelection =
                    new CardSelection(help.get(helpColNr), selectedCardSlot,
                        CardSelection.HELP, helpNr, helpColNr);
            }
        }

        return cardSelection;
    }

    /**
     * @TODO: javadoc!
     *
     * @param x javadoc!
     * @param y javadoc!
     *
     * @return javadoc!
     */
    private Point getSlot(final int x, final int y)
    {
        return new Point((
                (
                    (x - this.getCardSlotOffset())
                    - (
                        ((this.getMaxPileSize() - 1) * this.getPileOffset())
                        - 1
                    )
                ) / (this.getCardSize().width + this.getCardSlotOffset())
            ) + 1,
            (y - (2 * this.getCardSlotOffset()) + 1) / (
                this.getCardSize().height + this.getCardSlotOffset()
            ));
    }

    /**
     * @TODO: javadoc!
     *
     * @param x javadoc!
     * @param y javadoc!
     *
     * @return javadoc!
     */
    private Point getSlotLocation(final int x, final int y)
    {
        return new Point((
                (this.getCardSize().width + this.getCardSlotOffset()) * (
                    x - 1
                )
            ) + this.getCardSlotOffset()
            + ((this.getMaxPileSize() - 1) * this.getPileOffset()) + 1,
            (
                ((this.getCardSize().height + this.getCardSlotOffset()) * y)
                + (2 * this.getCardSlotOffset())
            ) - 1);
    }

    /**
     * @TODO: javadoc!
     *
     * @param location javadoc!
     *
     * @return javadoc!
     */
    private Point getSlotLocation(final Point location)
    {
        return this.getSlotLocation((int) location.getX(), (int) location.getY());
    }

    /**
     * @TODO: javadoc!
     *
     * @param property javadoc!
     *
     * @return javadoc!
     */
    private static String getString(final String property)
    {
        return MessageProperties.getString("napoleontableaupanel", //$NON-NLS-1$
            property);
    }

    /**
     * @TODO: javadoc!
     *
     * @param susp javadoc!
     */
    private void setSuspended(final boolean susp)
    {
        this.suspended = susp;
    }

    /**
     * @TODO: javadoc!
     *
     * @param tabl javadoc!
     */
    private void setTableau(final NapoleonTableau tabl)
    {
        this.tableau = tabl;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private NapoleonTableau getTableau()
    {
        return this.tableau;
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     * @param clickCount javadoc!
     */
    private void autoPlace(final Graphics graphics, final int clickCount)
    {
        if (clickCount > 1)
        {
            boolean done = false;

            for (int i = 0; (i < 4) && !done; i++)
            {
                CardSelection newSelectedCard =
                    new CardSelection(this.getTableau().getFoundation(i)
                                          .getLast(), new Point(6, i + 1),
                        CardSelection.FOUNDATION, i);

                if (this.tryMove(graphics, newSelectedCard))
                {
                    done = true;
                }
            }
        }
    }

    /**
     * @TODO: javadoc!
     */
    private void createUpdateTimer()
    {
        this.updateTimer =
            new Timer(MILLIS_IN_SEC,
                new ActionListener()
                {
                    public final void actionPerformed(final ActionEvent event)
                    {
                        updateProgress(getGraphics());
                    }
                });
        this.updateTimer.setInitialDelay(0);
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private int decrNrOfMoves()
    {
        return --this.nrOfMoves;
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     * @param x javadoc!
     * @param y javadoc!
     * @param card javadoc!
     */
    private void drawCardInSlot(final Graphics graphics, final int x,
        final int y, final Card card)
    {
        Point slotLocation = this.getSlotLocation(x, y);
        this.drawFront(graphics, (int) slotLocation.getX(),
            (int) slotLocation.getY(), card);
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     * @param x javadoc!
     * @param y javadoc!
     * @param cards javadoc!
     * @param clear javadoc!
     */
    private void drawCardPileInSlot(final Graphics graphics, final int x,
        final int y, final ArrayList cards, final boolean clear)
    {
        Point slotLocation = this.getSlotLocation(x, y);

        PileInfo pileInfo = new PileInfo();
        pileInfo.setFront(true);
        pileInfo.setPileFront(this.getPileFront());
        pileInfo.setClear(clear);

        if (x > 6)
        {
            pileInfo.setDir(TableauPanel.RIGHT_PILE);
        }
        else if (x < 6)
        {
            pileInfo.setDir(TableauPanel.LEFT_PILE);
        }

        this.drawCardPile(graphics, cards, slotLocation, pileInfo);
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     * @param cardSelection javadoc!
     */
    private void drawCardSelection(final Graphics graphics,
        final CardSelection cardSelection)
    {
        if (cardSelection != null)
        {
            Point slot = cardSelection.getSlot();
            Point slotLocation = this.getSlotLocation(slot);

            if (slot.getY() < 5)
            {
                if (slot.getX() < 6)
                {
                    int pileSize =
                        this.getPileSize(this.getTableau()
                                             .getNapoleonRow((int) slot.getY()
                                - 1).getEnd().size());

                    if (pileSize > 0)
                    {
                        slotLocation.translate(-(pileSize - 1) * this
                            .getPileOffset(), 0);
                    }
                }
                else if (slot.getX() > 6)
                {
                    int pileSize =
                        this.getPileSize(this.getTableau()
                                             .getNapoleonRow((int) slot.getY()
                                + 3).getEnd().size());

                    if (pileSize > 0)
                    {
                        slotLocation.translate((pileSize - 1) * this
                            .getPileOffset(), 0);
                    }
                }
            }

            this.drawSelection(graphics, (int) slotLocation.getX(),
                (int) slotLocation.getY());
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     */
    private void drawCardsFinished(final Graphics graphics)
    {
        Point slotLocation = this.getSlotLocation(INFO_SLOT);
        graphics.clearRect((int) slotLocation.getX(),
            (int) slotLocation.getY(), (2 * getCardSize().width) + 1,
            this.getFontSize() + 2);
        graphics.drawString(new StringBuffer().append(
                this.getTableau().cardsFinished())
                                              .append(getString("progresstext")) //$NON-NLS-1$
        .append(this.getTableau().getSize()).toString(),
            (int) slotLocation.getX() + 1,
            (int) slotLocation.getY() + this.getFontSize() + 1);
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     */
    private void drawHelp(final Graphics graphics)
    {
        Card card;

        for (int j = 0; j < 2; j++)
        {
            Help help = this.getTableau().getHelp(j);

            for (int i = 0; i < 4; i++)
            {
                card = help.get(i);

                if (card != null)
                {
                    this.drawCardInSlot(graphics, (j * 7) + i + 1, 5, card);
                }
            }
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     * @param j javadoc!
     * @param row javadoc!
     */
    private void drawNapoleonRowBase(final Graphics graphics, final int j,
        final NapoleonRow row)
    {
        ArrayList cards = row.getBase();

        for (int i = 0; i < cards.size(); i++)
        {
            int x;
            int y;

            if (j < 4)
            {
                x = 5 - i;
            }
            else
            {
                x = 7 + i;
            }

            if (j < 4)
            {
                y = j + 1;
            }
            else
            {
                y = j - 3;
            }

            this.drawCardInSlot(graphics, x, y, (Card) cards.get(i));
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     * @param j javadoc!
     * @param row javadoc!
     */
    private void drawNapoleonRowEnd(final Graphics graphics, final int j,
        final NapoleonRow row)
    {
        int lb = row.getBase().size();
        ArrayList cards = row.getEnd();

        if (cards.size() > 0)
        {
            int x;
            int y;

            if (j < 4)
            {
                x = 5 - lb;
            }
            else
            {
                x = 7 + lb;
            }

            if (j < 4)
            {
                y = j + 1;
            }
            else
            {
                y = j - 3;
            }

            this.drawCardPileInSlot(graphics, x, y, cards, false);
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     */
    private void drawNapoleonRows(final Graphics graphics)
    {
        for (int j = 0; j < 8; j++)
        {
            NapoleonRow row = this.getTableau().getNapoleonRow(j);
            drawNapoleonRowBase(graphics, j, row);

            drawNapoleonRowEnd(graphics, j, row);
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     */
    private void drawNrOfMoves(final Graphics graphics)
    {
        Point slotLocation = this.getSlotLocation(INFO_SLOT);
        graphics.clearRect((int) slotLocation.getX(),
            (int) slotLocation.getY() + this.getFontSize() + 2,
            (2 * getCardSize().width) + 1, this.getFontSize() + 2);
        graphics.drawString(new StringBuffer().append(this.getNrOfMoves())
                                              .append(getString("nrofmovestext")) //$NON-NLS-1$
        .toString(), (int) slotLocation.getX() + 1,
            (int) slotLocation.getY() + (2 * (this.getFontSize() + 1)) + 1);
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     * @param slot javadoc!
     * @param card javadoc!
     */
    private void drawRowCards(final Graphics graphics, final Point slot,
        final Card card)
    {
        if (slot.getX() == 6)
        {
            this.drawCardInSlot(graphics, (int) slot.getX(), (int) slot.getY(),
                card);
        }
        else
        {
            drawRowEndCards(graphics, slot);
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     * @param slot javadoc!
     */
    private void drawRowEndCards(final Graphics graphics, final Point slot)
    {
        int rowNr = -1;

        if (slot.getX() < 6)
        {
            rowNr = (int) slot.getY() - 1;
        }

        if (slot.getX() > 6)
        {
            rowNr = (int) slot.getY() + 3;
        }

        ArrayList cards = this.getTableau().getNapoleonRow(rowNr).getEnd();

        if (cards.size() > 0)
        {
            this.drawCardPileInSlot(graphics, (int) slot.getX(),
                (int) slot.getY(), cards, true);
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     * @param slot javadoc!
     */
    private void drawSlot(final Graphics graphics, final Point slot)
    {
        Point slotLocation = this.getSlotLocation(slot);
        CardSelection cardSelection =
            this.getSelectedCard((int) slotLocation.getX(),
                (int) slotLocation.getY());
        graphics.clearRect((int) slotLocation.getX(),
            (int) slotLocation.getY(), this.getCardSize().width + 1,
            this.getCardSize().height + 1);

        if (cardSelection != null)
        {
            Point newSlot = cardSelection.getSlot();

            if ((slot.getX() == newSlot.getX())
                && (slot.getY() == newSlot.getY()))
            {
                Card card = cardSelection.getCard();

                if (card != null)
                {
                    if (slot.getY() < 5)
                    {
                        this.drawRowCards(graphics, slot, card);
                    }
                    else if (slot.getY() == 5)
                    {
                        this.drawCardInSlot(graphics, (int) slot.getX(),
                            (int) slot.getY(), card);
                    }
                }
            }
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     */
    private void drawTimeUsed(final Graphics graphics)
    {
        if (graphics != null)
        {
            Point slotLocation = this.getSlotLocation(INFO_SLOT);
            graphics.clearRect((int) slotLocation.getX(),
                (int) slotLocation.getY() + (2 * this.getFontSize()) + 4,
                (2 * getCardSize().width) + 1, this.getFontSize() + 2);
            graphics.drawString(NapoleonMessage.makeDurationString(
                    UsedTimeHandler.getTimeUsed(), 2),
                (int) slotLocation.getX() + 1,
                (int) slotLocation.getY() + (3 * (this.getFontSize() + 1)) + 2);
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private int incrNrOfMoves()
    {
        return ++this.nrOfMoves;
    }

    /**
     * @TODO: javadoc!
     */
    private void init()
    {
        this.setSelectedCard(null);
        this.setNrOfMoves(0);
        this.setSuspended(false);
        UsedTimeHandler.setTimeUsed(0);
        UsedTimeHandler.resume();
        this.startUpdateTimer();
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     * @param x javadoc!
     * @param y javadoc!
     */
    private void processFirstSelection(final Graphics graphics, final int x,
        final int y)
    {
        CardSelection newSelectedCard = this.getSelectedCard(x, y);

        if (newSelectedCard != null)
        {
            Point slot = this.getSelectedCard().getSlot();
            Point newSlot = newSelectedCard.getSlot();

            if (slot.equals(newSlot))
            {
                this.drawCardSelection(graphics, this.getSelectedCard());
                this.setSelectedCard(null);
            }
            else
            {
                this.tryMove(graphics, newSelectedCard);
            }
        }

        /* Enable this to cancel any selection
         *  after move try that is not allowed
         */
        /* else
           {
               this.drawCardSelection(graphics, this.getSelectedCard());
               this.setSelectedCard(null);
           } */
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     * @param clickCount javadoc!
     */
    private void processSecondSelection(final Graphics graphics,
        final int clickCount)
    {
        autoPlace(graphics, clickCount);

        this.drawCardSelection(graphics, this.getSelectedCard());
    }

    /**
     * @TODO: javadoc!
     */
    private void startUpdateTimer()
    {
        this.updateTimer.restart();
    }

    /**
     * @TODO: javadoc!
     */
    private void stopUpdateTimer()
    {
        this.updateTimer.stop();
    }

    /**
     * @TODO: javadoc!
     */
    private void tableauFinished()
    {
        UsedTimeHandler.suspend();
        this.stopUpdateTimer();
        this.getScoreTable().addEntry(NapoleonMessage.askScoreText(this),
            this.getNrOfMoves(), UsedTimeHandler.getTimeUsed(), new Date());
        this.showScoreTable();
        this.newGame();
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     * @param newSelectedCard javadoc!
     *
     * @return javadoc!
     */
    private boolean tryMove(final Graphics graphics,
        final CardSelection newSelectedCard)
    {
        if (this.getTableau().move(this.getSelectedCard(), newSelectedCard))
        {
            this.incrNrOfMoves();
            this.drawSlot(graphics, this.getSelectedCard().getSlot());
            this.drawSlot(graphics, newSelectedCard.getSlot());
            this.updateStatus(graphics);
            this.setSelectedCard(null);

            if (this.getTableau().isFinished())
            {
                this.tableauFinished();
            }

            return true;
        }

        return false;
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     */
    private void updateStatus(final Graphics graphics)
    {
        this.drawCardsFinished(graphics);
        this.drawNrOfMoves(graphics);
        this.drawTimeUsed(graphics);
    }
}
