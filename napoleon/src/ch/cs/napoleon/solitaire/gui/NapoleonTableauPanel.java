package ch.cs.napoleon.solitaire.gui;

import ch.cs.gui.MessageProperties;
import ch.cs.napoleon.gui.NapoleonMessage;
import ch.cs.napoleon.score.ScoreTable;
import ch.cs.napoleon.solitaire.Help;
import ch.cs.napoleon.solitaire.NapoleonRow;
import ch.cs.napoleon.solitaire.NapoleonTableau;
import ch.cs.napoleon.solitaire.UndoMove;
import ch.cs.solitaire.Card;
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
 * TODO:
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class NapoleonTableauPanel extends TableauPanel
{
    //~ Static variables/initializers --------------------------------

    /** TODO: */
    private static final String NR_OF_MOVES =
        "napoleontableaupanel.nrofmoves"; //$NON-NLS-1$

    /** TODO: */
    private static final String TIME_USED =
        "napoleontableaupanel.timeused"; //$NON-NLS-1$

    /** TODO: */
    private static final String SCORE_TABLE =
        "napoleontableaupanel.scoretable"; //$NON-NLS-1$

    /** TODO: */
    private static final String SELECTED_CARD =
        "napoleontableaupanel.selectedcard"; //$NON-NLS-1$

    /** TODO: */
    private static final String SUSPENDED =
        "napoleontableaupanel.suspended"; //$NON-NLS-1$

    /** TODO: */
    private static final String SETTINGS_ID =
        "napoleontableaupanel.settingsid"; //$NON-NLS-1$

    //~ Instance variables -------------------------------------------

    /** TODO: */
    private CardSelection selectedCard;

    /** TODO: */
    private NapoleonTableau tableau;

    /** TODO: */
    private ScoreTable scoreTable;

    /** TODO: */
    private Timer updateTimer;

    /** TODO: */
    private boolean pileFront;

    /** TODO: */
    private boolean suspended;

    /** TODO: */
    private int nrOfMoves;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new NapoleonTableauPanel object.
     *
     * @throws Exception TODO:
     */
    public NapoleonTableauPanel()
            throws Exception
    {
        super();
        this.setDoubleBuffered(true);
        this.setTableau(new NapoleonTableau());
        this.setScoreTable(new ScoreTable());
        this.setPileFront(getBoolean("pilefront")); //$NON-NLS-1$
        this.setCursor(
            Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.createUpdateTimer();
        this.addMouseListener(new MouseHandler(this));
        new DnDHandler(this);
        this.getCardSuits();
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO:
     *
     * @param map TODO:
     */
    public void setData(Map map)
    {
        this.init();
        try
        {
            this.setNrOfMoves(
                ((Integer) map.get(NR_OF_MOVES)).intValue());
            this.setScoreTable((ScoreTable) map.get(SCORE_TABLE));
            this.setSelectedCard((CardSelection) map.get(
                    SELECTED_CARD));
            this.setSet((String) map.get(SETTINGS_ID));
            this.setSuspended(
                ((Boolean) map.get(SUSPENDED)).booleanValue());
            if (this.getSuspended())
            {
                UsedTimeHandler.suspend();
                this.stopUpdateTimer();
            }
            UsedTimeHandler.setTimeUsed(
                ((Integer) map.get(TIME_USED)).intValue());
            this.getTableau().setData(map);
        }
        catch (Throwable e)
        {
            this.newGame();
        }
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public Map getData()
    {
        Map map = new HashMap();
        map.put(
            TIME_USED,
            new Integer(UsedTimeHandler.getTimeUsed()));
        map.put(
            NR_OF_MOVES,
            new Integer(this.getNrOfMoves()));
        UsedTimeHandler.updateTimeUsed();
        map.put(
            SCORE_TABLE,
            this.getScoreTable());
        map.put(
            SELECTED_CARD,
            this.getSelectedCard());
        map.put(
            SETTINGS_ID,
            this.getCurrentSetId());
        map.put(
            SUSPENDED,
            Boolean.valueOf(this.getSuspended()));
        map.putAll(this.getTableau().getData());
        return map;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public Dimension getPreferredSize()
    {
        return new Dimension(((this.getCardSize().width
            + this.getCardSlotOffset()) * 11)
            + this.getCardSlotOffset()
            + (2 * (this.getMaxPileSize() - 1) * this.getPileOffset()),
            (((this.getCardSize().height + this.getCardSlotOffset()) * 6)
            + (2 * this.getCardSlotOffset())) - 1);
    }

    /**
     * Returns the suspended.
     *
     * @return boolean
     */
    public boolean getSuspended()
    {
        return suspended;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public boolean canRedo()
    {
        return this.getTableau().canRedo();
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public boolean canUndo()
    {
        return this.getTableau().canUndo();
    }

    /**
     * TODO:
     */
    public void clearScoreTable()
    {
        NapoleonMessage.clearScoreTable(
            this,
            this.getScoreTable());
    }

    /**
     * TODO:
     */
    public void newGame()
    {
        this.init();
        this.getTableau().newGame();
        this.repaint();
    }

    /**
     * TODO:
     */
    public void redo()
    {
        Graphics graphics = this.getGraphics();
        if (this.canRedo() && !this.getSuspended())
        {
            UndoMove undoMove = this.getTableau().redo();
            if (undoMove != null)
            {
                CardSelection src = undoMove.getSrc();
                CardSelection dst = undoMove.getDst();
                this.incrNrOfMoves();
                this.drawSlot(
                    graphics,
                    src.getSlot());
                this.drawSlot(
                    graphics,
                    dst.getSlot());
                updateStatus(graphics);
            }
        }
    }

    /**
     * TODO:
     */
    public void restartGame()
    {
        this.init();
        this.getTableau().restartGame();
        this.repaint();
    }

    /**
     * TODO:
     */
    public void resume()
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
     * TODO:
     */
    public void showScoreTable()
    {
        NapoleonMessage.showScoreTable(
            this,
            this.getScoreTable());
    }

    /**
     * TODO:
     */
    public void suspend()
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
     * TODO:
     */
    public void toggleSuspendResume()
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
     * TODO:
     */
    public void undo()
    {
        Graphics graphics = this.getGraphics();
        if (this.canUndo() && !this.getSuspended())
        {
            UndoMove undoMove = this.getTableau().undo();
            if (undoMove != null)
            {
                CardSelection src = undoMove.getSrc();
                CardSelection dst = undoMove.getDst();
                this.decrNrOfMoves();
                this.drawSlot(
                    graphics,
                    src.getSlot());
                this.drawSlot(
                    graphics,
                    dst.getSlot());
                this.updateStatus(graphics);
            }
        }
    }

    /**
     * Returns the selectedCard.
     *
     * @return CardSelection
     */
    protected CardSelection getSelectedCard()
    {
        return selectedCard;
    }

    /**
     * TODO:
     *
     * @param location TODO:
     *
     * @return TODO:
     */
    protected Point calculateSlotOffset(Point location)
    {
        Point offset =
            this.getSlotLocation(this.getSelectedCard().getSlot());
        offset.translate(-(int) location.getX(),
            -(int) location.getY());
        return offset;
    }

    /**
     * TODO:
     *
     * @param graphics TODO:
     */
    protected void drawDeck(Graphics graphics)
    {
        ArrayList cards;
        Card card;
        graphics.clearRect(0, 0, this.getSize().width,
            this.getSize().height);
        if (!getSuspended())
        {
            card = this.getTableau().getFoundation(0).getFirst();
            this.drawCardInSlot(graphics, 6, 0, card);
            for (int i = 0; i < 4; i++)
            {
                card = this.getTableau().getFoundation(i).getLast();
                this.drawCardInSlot(graphics, 6, i + 1, card);
            }
            for (int j = 0; j < 8; j++)
            {
                NapoleonRow row = this.getTableau().getNapoleonRow(j);
                cards = row.getBase();
                for (int i = 0; i < cards.size(); i++)
                {
                    this.drawCardInSlot(graphics,
                        (j < 4) ? (5 - i) : (7 + i),
                        (j < 4) ? (j + 1) : (j - 3),
                        (Card) cards.get(i));
                }
                int lb = cards.size();
                cards = row.getEnd();
                if (cards.size() > 0)
                {
                    this.drawCardPileInSlot(graphics,
                        (j < 4) ? (5 - lb) : (7 + lb),
                        (j < 4) ? (j + 1) : (j - 3), cards, false);
                }
            }
            for (int j = 0; j < 2; j++)
            {
                Help help = this.getTableau().getHelp(j);
                for (int i = 0; i < 4; i++)
                {
                    card = help.get(i);
                    if (card != null)
                    {
                        this.drawCardInSlot(graphics,
                            (j * 7) + i + 1, 5, card);
                    }
                }
            }
            card = this.getTableau().getCellar().get();
            if (card != null)
            {
                this.drawCardInSlot(graphics, 6, 5, card);
            }
            this.drawCardSelection(
                graphics,
                this.getSelectedCard());
        }
        this.updateStatus(graphics);
    }

    /**
     * TODO:
     *
     * @param graphics TODO:
     * @param x TODO:
     * @param y TODO:
     * @param clickCount TODO:
     */
    protected void processSelection(Graphics graphics, int x, int y,
        int clickCount)
    {
        if (this.getSelectedCard() == null)
        {
            this.setSelectedCard(this.getSelectedCard(x, y));
            if (this.getSelectedCard() != null)
            {
                if (clickCount > 1)
                {
                    for (int i = 0; i < 4; i++)
                    {
                        CardSelection newSelectedCard =
                            new CardSelection(
                                this.getTableau().getFoundation(i)
                                        .getLast(),
                                new Point(6, i + 1),
                                CardSelection.FOUNDATION,
                                i);
                        if (this.tryMove(graphics, newSelectedCard))
                        {
                            return;
                        }
                    }
                }
                this.drawCardSelection(
                    graphics,
                    this.getSelectedCard());
            }
        }
        else
        {
            CardSelection newSelectedCard =
                this.getSelectedCard(x, y);
            if (newSelectedCard == null)
            {
                /* Enable this to cancel any selection
                 *  after move try that is not allowed
                 */
                /* this.drawCardSelection(graphics, this.getSelectedCard());
                 * this.setSelectedCard(null);
                 */
            }
            else
            {
                Point slot = this.getSelectedCard().getSlot();
                Point newSlot = newSelectedCard.getSlot();
                if (slot.equals(newSlot))
                {
                    this.drawCardSelection(
                        graphics,
                        this.getSelectedCard());
                    this.setSelectedCard(null);
                }
                else
                {
                    this.tryMove(graphics, newSelectedCard);
                }
            }
        }
    }

    /**
     * TODO:
     *
     * @param property TODO:
     *
     * @return TODO:
     */
    private static boolean getBoolean(String property)
    {
        return MessageProperties.getBoolean("napoleontableaupanel", //$NON-NLS-1$
            property);
    }

    /**
     * TODO:
     *
     * @param nrOfMoves TODO:
     */
    private void setNrOfMoves(int nrOfMoves)
    {
        this.nrOfMoves = nrOfMoves;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private int getNrOfMoves()
    {
        return this.nrOfMoves;
    }

    /**
     * TODO:
     *
     * @param pileFront TODO:
     */
    private void setPileFront(boolean pileFront)
    {
        this.pileFront = pileFront;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private boolean getPileFront()
    {
        return pileFront;
    }

    /**
     * TODO:
     *
     * @param scoreTable TODO:
     */
    private void setScoreTable(ScoreTable scoreTable)
    {
        this.scoreTable = scoreTable;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private ScoreTable getScoreTable()
    {
        return scoreTable;
    }

    /**
     * TODO:
     *
     * @param selectedCard TODO:
     */
    private void setSelectedCard(CardSelection selectedCard)
    {
        this.selectedCard = selectedCard;
    }

    /**
     * TODO:
     *
     * @param x TODO:
     * @param y TODO:
     *
     * @return TODO:
     */
    private CardSelection getSelectedCard(int x, int y)
    {
        if (this.getTableau() != null)
        {
            Point selectedCardSlot = this.getSlot(x, y);
            if (selectedCardSlot.getY() == 0)
            {
                return null;
            }
            else if (selectedCardSlot.getY() < 5)
            {
                if (selectedCardSlot.getX() == 6)
                {
                    return new CardSelection(
                        this.getTableau()
                                .getFoundation((int) selectedCardSlot
                                .getY() - 1).getLast(),
                        selectedCardSlot,
                        CardSelection.FOUNDATION,
                        (int) selectedCardSlot.getY() - 1);
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
                        napoleonRow =
                            this.getTableau().getNapoleonRow(rowNr);
                        last = napoleonRow.getBase().size() + 1;
                        slotWidth = 6 - last;
                    }
                    else if (selectedCardSlot.getX() > 6)
                    {
                        rowNr = (int) selectedCardSlot.getY() + 3;
                        napoleonRow =
                            this.getTableau().getNapoleonRow(rowNr);
                        last = napoleonRow.getBase().size() + 1;
                        slotWidth = 6 + last;
                    }
                    if (last > Math.abs(6 - selectedCardSlot.getX()))
                    {
                        return null;
                    }
                    return new CardSelection(
                        napoleonRow.getLast(),
                        new Point(slotWidth,
                            (int) selectedCardSlot.getY()),
                        CardSelection.ROW,
                        rowNr,
                        last);
                }
            }
            else if (selectedCardSlot.getY() == 5)
            {
                if (selectedCardSlot.getX() == 6)
                {
                    return new CardSelection(
                        this.getTableau().getCellar().get(),
                        selectedCardSlot,
                        CardSelection.CELLAR);
                }
                else if ((selectedCardSlot.getX() == 5)
                        || (selectedCardSlot.getX() == 7))
                {
                    return null;
                }
                else
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
                    if (help.get(helpColNr) == null)
                    {
                        return null;
                    }
                    return new CardSelection(
                        help.get(helpColNr),
                        selectedCardSlot,
                        CardSelection.HELP,
                        helpNr,
                        helpColNr);
                }
            }
        }
        return null;
    }

    /**
     * TODO:
     *
     * @param x TODO:
     * @param y TODO:
     *
     * @return TODO:
     */
    private Point getSlot(int x, int y)
    {
        return new Point((((x - this.getCardSlotOffset())
            - (((this.getMaxPileSize() - 1) * this.getPileOffset())
            - 1)) / (this.getCardSize().width
                + this.getCardSlotOffset())) + 1,
            (y - (2 * this.getCardSlotOffset()) + 1) / (this
                .getCardSize().height + this.getCardSlotOffset()));
    }

    /**
     * TODO:
     *
     * @param x TODO:
     * @param y TODO:
     *
     * @return TODO:
     */
    private Point getSlotLocation(int x, int y)
    {
        return new Point(((this.getCardSize().width
            + this.getCardSlotOffset()) * (x - 1))
            + this.getCardSlotOffset()
            + ((this.getMaxPileSize() - 1) * this.getPileOffset())
            + 1,
            (((this.getCardSize().height + this.getCardSlotOffset()) * y)
            + (2 * this.getCardSlotOffset())) - 1);
    }

    /**
     * TODO:
     *
     * @param location TODO:
     *
     * @return TODO:
     */
    private Point getSlotLocation(Point location)
    {
        return this.getSlotLocation((int) location.getX(),
            (int) location.getY());
    }

    /**
     * TODO:
     *
     * @param property TODO:
     *
     * @return TODO:
     */
    private static String getString(String property)
    {
        return MessageProperties.getString("napoleontableaupanel", //$NON-NLS-1$
            property);
    }

    /**
     * TODO:
     *
     * @param suspended TODO:
     */
    private void setSuspended(boolean suspended)
    {
        this.suspended = suspended;
    }

    /**
     * TODO:
     *
     * @param tableau TODO:
     */
    private void setTableau(NapoleonTableau tableau)
    {
        this.tableau = tableau;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private NapoleonTableau getTableau()
    {
        return tableau;
    }

    /**
     * TODO:
     */
    private void createUpdateTimer()
    {
        this.updateTimer =
            new Timer(
                1000,
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        updateProgress(getGraphics());
                    }
                });
        this.updateTimer.setInitialDelay(0);
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private int decrNrOfMoves()
    {
        return --this.nrOfMoves;
    }

    /**
     * TODO:
     *
     * @param graphics TODO:
     * @param x TODO:
     * @param y TODO:
     * @param card TODO:
     */
    private void drawCardInSlot(Graphics graphics, int x, int y,
        Card card)
    {
        Point slotLocation = this.getSlotLocation(x, y);
        this.drawFront(graphics, (int) slotLocation.getX(),
            (int) slotLocation.getY(), card);
    }

    /**
     * TODO:
     *
     * @param graphics TODO:
     * @param x TODO:
     * @param y TODO:
     * @param cards TODO:
     * @param clear TODO:
     */
    private void drawCardPileInSlot(Graphics graphics, int x, int y,
        ArrayList cards, boolean clear)
    {
        Point slotLocation = this.getSlotLocation(x, y);
        if (x > 6)
        {
            this.drawCardPile(
                graphics,
                cards,
                true,
                this.getPileFront(),
                slotLocation,
                TableauPanel.RIGHT_PILE,
                clear);
        }
        else if (x < 6)
        {
            this.drawCardPile(
                graphics,
                cards,
                true,
                this.getPileFront(),
                slotLocation,
                TableauPanel.LEFT_PILE,
                clear);
        }
    }

    /**
     * TODO:
     *
     * @param graphics TODO:
     * @param cardSelection TODO:
     */
    private void drawCardSelection(Graphics graphics,
        CardSelection cardSelection)
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
                        this.getPileSize(
                            this.getTableau()
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
                        this.getPileSize(
                            this.getTableau()
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
     * TODO:
     *
     * @param graphics TODO:
     */
    private void drawCardsFinished(Graphics graphics)
    {
        Point slotLocation = this.getSlotLocation(10, 0);
        graphics.clearRect((int) slotLocation.getX(),
            (int) slotLocation.getY(), (2 * getCardSize().width) + 1,
            this.getFontSize() + 2);
        graphics.drawString(
            new StringBuffer().append(
                this.getTableau().cardsFinished())
                                  .append(getString("progresstext")) //$NON-NLS-1$
        .append(this.getTableau().getSize()).toString(),
            (int) slotLocation.getX() + 1,
            (int) slotLocation.getY() + this.getFontSize() + 1);
    }

    /**
     * TODO:
     *
     * @param graphics TODO:
     */
    private void drawNrOfMoves(Graphics graphics)
    {
        Point slotLocation = this.getSlotLocation(10, 0);
        graphics.clearRect((int) slotLocation.getX(),
            (int) slotLocation.getY() + this.getFontSize() + 2,
            (2 * getCardSize().width) + 1, this.getFontSize() + 2);
        graphics.drawString(
            new StringBuffer().append(this.getNrOfMoves())
                                  .append(getString("nrofmovestext")) //$NON-NLS-1$
                                  .toString(),
            (int) slotLocation.getX() + 1,
            (int) slotLocation.getY()
            + (2 * (this.getFontSize() + 1)) + 1);
    }

    /**
     * TODO:
     *
     * @param graphics TODO:
     * @param slot TODO:
     */
    private void drawSlot(Graphics graphics, Point slot)
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
                        if (slot.getX() == 6)
                        {
                            this.drawCardInSlot(graphics,
                                (int) slot.getX(), (int) slot.getY(),
                                card);
                        }
                        else
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
                            ArrayList cards =
                                this.getTableau().getNapoleonRow(rowNr)
                                        .getEnd();
                            if (cards.size() > 0)
                            {
                                this.drawCardPileInSlot(graphics,
                                    (int) slot.getX(),
                                    (int) slot.getY(), cards, true);
                            }
                        }
                    }
                    else if (slot.getY() == 5)
                    {
                        this.drawCardInSlot(graphics,
                            (int) slot.getX(), (int) slot.getY(), card);
                    }
                }
            }
        }
    }

    /**
     * TODO:
     *
     * @param graphics TODO:
     */
    private void drawTimeUsed(Graphics graphics)
    {
        if (graphics != null)
        {
            Point slotLocation = this.getSlotLocation(10, 0);
            graphics.clearRect((int) slotLocation.getX(),
                (int) slotLocation.getY() + (2 * this.getFontSize())
                + 4, (2 * getCardSize().width) + 1,
                this.getFontSize() + 2);
            graphics.drawString(
                NapoleonMessage.makeDurationString(
                    UsedTimeHandler.getTimeUsed(),
                    2),
                (int) slotLocation.getX() + 1,
                (int) slotLocation.getY()
                + (3 * (this.getFontSize() + 1)) + 2);
        }
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private int incrNrOfMoves()
    {
        return ++this.nrOfMoves;
    }

    /**
     * TODO:
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
     * TODO:
     */
    private void startUpdateTimer()
    {
        updateTimer.restart();
    }

    /**
     * TODO:
     */
    private void stopUpdateTimer()
    {
        updateTimer.stop();
    }

    /**
     * TODO:
     */
    private void tableauFinished()
    {
        UsedTimeHandler.suspend();
        this.stopUpdateTimer();
        this.getScoreTable().addEntry(
            (String) NapoleonMessage.askScoreText(this),
            this.getNrOfMoves(),
            UsedTimeHandler.getTimeUsed(),
            new Date());
        this.showScoreTable();
        this.newGame();
    }

    /**
     * TODO:
     *
     * @param graphics TODO:
     * @param newSelectedCard TODO:
     *
     * @return TODO:
     */
    private boolean tryMove(Graphics graphics,
        CardSelection newSelectedCard)
    {
        if (this.getTableau().move(
                    this.getSelectedCard(),
                    newSelectedCard))
        {
            this.incrNrOfMoves();
            this.drawSlot(
                graphics,
                this.getSelectedCard().getSlot());
            this.drawSlot(
                graphics,
                newSelectedCard.getSlot());
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
     * TODO:
     *
     * @param graphics TODO:
     */
    private void updateProgress(Graphics graphics)
    {
        this.drawTimeUsed(graphics);
    }

    /**
     * TODO:
     *
     * @param graphics TODO:
     */
    private void updateStatus(Graphics graphics)
    {
        this.drawCardsFinished(graphics);
        this.drawNrOfMoves(graphics);
        this.drawTimeUsed(graphics);
    }
}