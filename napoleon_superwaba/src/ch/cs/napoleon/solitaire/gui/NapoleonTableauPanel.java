package ch.cs.napoleon.solitaire.gui;

import ch.cs.helpers.time.StopWatch;
import ch.cs.napoleon.gui.Dialogs;
import ch.cs.napoleon.score.ScoreEntries;
import ch.cs.napoleon.solitaire.Help;
import ch.cs.napoleon.solitaire.NapoleonRow;
import ch.cs.napoleon.solitaire.NapoleonTableau;
import ch.cs.solitaire.Card;
import ch.cs.solitaire.gui.TableauPanel;

import waba.fx.Color;
import waba.fx.Coord;
import waba.fx.Graphics;

import waba.io.DataStream;

import waba.sys.Convert;
import waba.sys.Settings;

import waba.util.Vector;


/**
 * TODO: javadoc
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class NapoleonTableauPanel extends TableauPanel
{
    //~ Instance variables -------------------------------------------

    /** TODO: javadoc */
    private CardSelection selectedCard;

    /** TODO: javadoc */
    private NapoleonTableau tableau;

    /** TODO: javadoc */
    private ScoreEntries scoreEntries;

    /** TODO: javadoc */
    private int nrOfMoves;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new NapoleonTableauPanel object.
     */
    public NapoleonTableauPanel()
    {
        super();
        this.setBackColor(Color.WHITE);
        this.setForeColor(Color.BLACK);
        this.tableau = new NapoleonTableau();
        this.scoreEntries = new ScoreEntries();
        this.init();
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */

    /* public boolean canRedo()
       {
           return this.tableau.canRedo();
       } */

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */

    /* public boolean canUndo()
       {
           return this.tableau.canUndo();
       } */

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void load(DataStream stream)
    {
        this.tableau.load(stream);
        this.scoreEntries.load(stream);
        this.init();
        if (stream.readByte() == 0)
        {
            this.selectedCard = null;
        }
        else
        {
            this.selectedCard = new CardSelection();
            this.selectedCard.load(stream);
        }
        this.nrOfMoves = stream.readInt();
        int time = stream.readInt();
        StopWatch.getStopWatch().set(time);
    }

    /**
     * TODO: javadoc
     */
    public void newGame()
    {
        this.tableau.newGame();
        this.init();
        this.repaint();
    }

    /**
     * TODO: javadoc
     *
     * @param graphics TODO: javadoc
     * @param x TODO: javadoc
     * @param y TODO: javadoc
     */
    public void processSelection(Graphics graphics, int x, int y)
    {
        if (selectedCard == null)
        {
            this.selectedCard = this.getSelectedCard(x, y);
            if (selectedCard != null)
            {
                this.drawSelectedCard(graphics, selectedCard, true);
            }
        }
        else
        {
            if (selectedCard.getCard() == null)
            {
                this.selectedCard = null;
                this.processSelection(graphics, x, y);
                return;
            }
            CardSelection newSelectedCard =
                this.getSelectedCard(x, y);
            if (newSelectedCard == null)
            {
                /* Enable this to cancel any selection
                 *  after move try that is not allowed
                 */
                /* this.drawSelectedCard(graphics, this.getSelectedCard(), true, false);
                 * this.selectedCard = null;
                 */
            }
            else
            {
                Coord slot = selectedCard.getSlot();
                Coord newSlot = newSelectedCard.getSlot();
                if (slot.equals(newSlot))
                {
                    this.drawSelectedCard(graphics, selectedCard,
                        false);
                    this.selectedCard = null;
                }
                else
                {
                    this.tryMove(graphics, newSelectedCard);
                }
            }
        }
    }

    /**
     * TODO: javadoc
     */

    /* public void redo()
       {
           if (this.canRedo())
           {
               UndoMove undoMove = this.tableau.redo();
               if (undoMove != null)
               {
                   CardSelection src = undoMove.getSrc();
                   CardSelection dst = undoMove.getDst();
                   Graphics graphics = this.createGraphics();
                   ++this.nrOfMoves;
                   this.drawSlot(
                       graphics,
                       src.getSlot());
                   this.drawSlot(
                       graphics,
                       dst.getSlot());
                   drawStatus(graphics);
               }
           }
       } */

    /**
     * TODO: javadoc
     */
    public void restartGame()
    {
        this.tableau.restartGame();
        this.init();
        this.repaint();
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void save(DataStream stream)
    {
        this.tableau.save(stream);
        this.scoreEntries.save(stream);
        if (this.selectedCard == null)
        {
            stream.writeByte(0);
        }
        else
        {
            stream.writeByte(1);
            this.selectedCard.save(stream);
        }
        stream.writeInt(this.nrOfMoves);
        stream.writeInt(StopWatch.getStopWatch().get());
        StopWatch.getStopWatch().update();
    }

    /**
     * TODO: javadoc
     */
    public void showScores()
    {
        Dialogs.showScores(
            this.getParentWindow(),
            scoreEntries);
    }

    /**
     * TODO: javadoc
     *
     * @param graphics TODO: javadoc
     */

    /* public void undo()
       {
           if (this.canUndo())
           {
               UndoMove undoMove = this.tableau.undo();
               if (undoMove != null)
               {
                   CardSelection src = undoMove.getSrc();
                   CardSelection dst = undoMove.getDst();
                   Graphics graphics = this.createGraphics();
                   --this.nrOfMoves;
                   this.drawSlot(
                       graphics,
                       src.getSlot());
                   this.drawSlot(
                       graphics,
                       dst.getSlot());
                   this.drawStatus(graphics);
               }
           }
       } */

    /**
     * TODO: javadoc
     *
     * @param graphics TODO: javadoc
     */
    protected void drawDeck(Graphics graphics)
    {
        this.drawStatus(graphics);
        Vector cards;
        Card card;
        graphics.fillRect(0, 0, this.getSize().x, this.getSize().y);
        card = this.tableau.getFoundation(0).getFirst();
        this.drawCardInSlot(graphics, 6, 0, card);
        for (int i = 0; i < 4; i++)
        {
            card = this.tableau.getFoundation(i).getLast();
            this.drawCardInSlot(graphics, 6, i + 1, card);
        }
        for (int j = 0; j < 8; j++)
        {
            NapoleonRow row = this.tableau.getNapoleonRow(j);
            cards = row.getBase();
            for (int i = 0; i < cards.size(); i++)
            {
                this.drawCardInSlot(graphics,
                    (j < 4) ? (5 - i) : (7 + i),
                    (j < 4) ? (j + 1) : (j - 3), (Card) cards.get(i));
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
            Help help = this.tableau.getHelp(j);
            for (int i = 0; i < 4; i++)
            {
                card = help.get(i);
                if (card != null)
                {
                    this.drawCardInSlot(graphics, (j * 7) + i + 1, 5,
                        card);
                }
            }
        }
        card = this.tableau.getCellar().get();
        if (card != null)
        {
            this.drawCardInSlot(graphics, 6, 5, card);
        }
        this.drawSelectedCard(graphics, this.selectedCard, true);
    }

    /**
     * TODO: javadoc
     *
     * @param x TODO: javadoc
     * @param y TODO: javadoc
     *
     * @return TODO: javadoc
     */
    private CardSelection getSelectedCard(int x, int y)
    {
        if (this.tableau != null)
        {
            Coord selectedCardSlot = this.getSlot(x, y);
            if (selectedCardSlot.y == 0)
            {
                return null;
            }
            else if (selectedCardSlot.y < 5)
            {
                if (selectedCardSlot.x == 6)
                {
                    return new CardSelection(
                        this.tableau.getFoundation(selectedCardSlot.y
                            - 1).getLast(),
                        selectedCardSlot,
                        CardSelection.FOUNDATION,
                        selectedCardSlot.y - 1);
                }
                else
                {
                    int rowNr = 0;
                    NapoleonRow napoleonRow = null;
                    int last = 0;
                    int slotWidth = 0;
                    if (selectedCardSlot.x < 6)
                    {
                        rowNr = selectedCardSlot.y - 1;
                        napoleonRow =
                            this.tableau.getNapoleonRow(rowNr);
                        last = napoleonRow.getBase().size() + 1;
                        slotWidth = 6 - last;
                    }
                    else if (selectedCardSlot.x > 6)
                    {
                        rowNr = selectedCardSlot.y + 3;
                        napoleonRow =
                            this.tableau.getNapoleonRow(rowNr);
                        last = napoleonRow.getBase().size() + 1;
                        slotWidth = 6 + last;
                    }
                    if (last > Math.abs(6 - selectedCardSlot.x))
                    {
                        return null;
                    }
                    return new CardSelection(
                        napoleonRow.getLast(),
                        new Coord(slotWidth, selectedCardSlot.y),
                        CardSelection.ROW,
                        rowNr,
                        last);
                }
            }
            else if (selectedCardSlot.y == 5)
            {
                if (selectedCardSlot.x == 6)
                {
                    return new CardSelection(
                        this.tableau.getCellar().get(),
                        selectedCardSlot,
                        CardSelection.CELLAR);
                }
                else if ((selectedCardSlot.x == 5)
                        || (selectedCardSlot.x == 7))
                {
                    return null;
                }
                else
                {
                    int helpNr = 0;
                    int helpColNr = 0;
                    if (selectedCardSlot.x < 5)
                    {
                        helpNr = 0;
                        helpColNr = selectedCardSlot.x - 1;
                    }
                    if (selectedCardSlot.x > 7)
                    {
                        helpNr = 1;
                        helpColNr = selectedCardSlot.x - 8;
                    }
                    Help help = this.tableau.getHelp(helpNr);
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
     * TODO: javadoc
     *
     * @param x TODO: javadoc
     * @param y TODO: javadoc
     *
     * @return TODO: javadoc
     */
    private Coord getSlot(int x, int y)
    {
        return new Coord((((x - this.getCardSlotOffset())
            - (((this.getMaxPileSize() - 1) * this.getPileOffset())
            - 1)) / (this.getCardSize().x + this.getCardSlotOffset()))
            + 1,
            (y - (2 * this.getCardSlotOffset()) + 1) / (this
                .getCardSize().y + this.getCardSlotOffset()));
    }

    /**
     * TODO: javadoc
     *
     * @param x TODO: javadoc
     * @param y TODO: javadoc
     *
     * @return TODO: javadoc
     */
    private Coord getSlotLocation(int x, int y)
    {
        return new Coord(((this.getCardSize().x
            + this.getCardSlotOffset()) * (x - 1))
            + this.getCardSlotOffset()
            + ((this.getMaxPileSize() - 1) * this.getPileOffset())
            + 1,
            (((this.getCardSize().y + this.getCardSlotOffset()) * y)
            + (2 * this.getCardSlotOffset())) - 1);
    }

    /**
     * TODO: javadoc
     *
     * @param graphics TODO: javadoc
     * @param x TODO: javadoc
     * @param y TODO: javadoc
     * @param card TODO: javadoc
     */
    private void drawCardInSlot(Graphics graphics, int x, int y,
        Card card)
    {
        Coord slotLocation = this.getSlotLocation(x, y);
        this.drawFront(graphics, slotLocation.x, slotLocation.y, card);
    }

    /**
     * TODO: javadoc
     *
     * @param graphics TODO: javadoc
     * @param x TODO: javadoc
     * @param y TODO: javadoc
     * @param cards TODO: javadoc
     * @param clear TODO: javadoc
     */
    private void drawCardPileInSlot(Graphics graphics, int x, int y,
        Vector cards, boolean clear)
    {
        Coord slotLocation = this.getSlotLocation(x, y);
        if (x > 6)
        {
            this.drawCardPile(graphics, cards, true, true,
                slotLocation, TableauPanel.RIGHT_PILE, clear);
        }
        else if (x < 6)
        {
            this.drawCardPile(graphics, cards, true, true,
                slotLocation, TableauPanel.LEFT_PILE, clear);
        }
    }

    /**
     * TODO: javadoc
     *
     * @param graphics TODO: javadoc
     */
    private void drawCardsFinished(Graphics graphics)
    {
        Coord slotLocation = this.getSlotLocation(10, 6);

        graphics.fillRect(slotLocation.x, slotLocation.y,
            (2 * getCardSize().x) + this.getCardSlotOffset() + 1,
            this.getFontHeight() + 2);
        graphics.drawText(
            new StringBuffer().append(this.tableau.cardsFinished())
                                  .append("/")
                                  .append(this.tableau.getSize())
                                  .toString(),
            slotLocation.x + 1,
            slotLocation.y + 1);
    }

    /**
     * TODO: javadoc
     *
     * @param graphics TODO: javadoc
     */
    private void drawNrOfMoves(Graphics graphics)
    {
        Coord slotLocation = this.getSlotLocation(1, 6);

        graphics.fillRect(slotLocation.x, slotLocation.y,
            (2 * getCardSize().x) + this.getCardSlotOffset() + 1,
            this.getFontHeight() + 2);
        graphics.drawText(
            Convert.toString(this.nrOfMoves),
            slotLocation.x + 1,
            slotLocation.y + 1);
    }

    /**
     * TODO: javadoc
     *
     * @param graphics TODO: javadoc
     * @param cardSelection TODO: javadoc
     * @param selected TODO: javadoc
     */
    private void drawSelectedCard(Graphics graphics,
        CardSelection cardSelection, boolean selected)
    {
        if (cardSelection != null)
        {
            Coord slot = cardSelection.getSlot();
            Coord location = slot;
            Coord slotLocation =
                this.getSlotLocation(location.x, location.y);
            if (slot.y < 5)
            {
                if (slot.x < 6)
                {
                    int pileSize =
                        this.getPileSize(
                            this.tableau.getNapoleonRow(slot.y - 1)
                                            .getEnd().size());
                    if (pileSize > 0)
                    {
                        slotLocation.translate(-(pileSize - 1) * this
                                .getPileOffset(), 0);
                    }
                }
                else if (slot.x > 6)
                {
                    int pileSize =
                        this.getPileSize(
                            this.tableau.getNapoleonRow(slot.y + 3)
                                            .getEnd().size());
                    if (pileSize > 0)
                    {
                        slotLocation.translate((pileSize - 1) * this
                                .getPileOffset(), 0);
                    }
                }
            }
            this.drawSelectedCard(
                graphics,
                slotLocation.x,
                slotLocation.y,
                cardSelection.getCard(),
                true,
                selected);
        }
    }

    /**
     * TODO: javadoc
     *
     * @param graphics TODO: javadoc
     * @param slot TODO: javadoc
     */
    private void drawSlot(Graphics graphics, Coord slot)
    {
        Coord slotLocation = this.getSlotLocation(slot.x, slot.y);
        CardSelection cardSelection =
            this.getSelectedCard(slotLocation.x, slotLocation.y);
        graphics.fillRect(slotLocation.x, slotLocation.y,
            this.getCardSize().x, this.getCardSize().y);
        if (cardSelection != null)
        {
            Coord newSlot = cardSelection.getSlot();
            if ((slot.x == newSlot.x) && (slot.y == newSlot.y))
            {
                Card card = cardSelection.getCard();
                if (card != null)
                {
                    if (slot.y < 5)
                    {
                        if (slot.x == 6)
                        {
                            this.drawCardInSlot(graphics, slot.x,
                                slot.y, card);
                        }
                        else
                        {
                            int rowNr = -1;
                            if (slot.x < 6)
                            {
                                rowNr = slot.y - 1;
                            }
                            if (slot.x > 6)
                            {
                                rowNr = slot.y + 3;
                            }
                            Vector cards =
                                this.tableau.getNapoleonRow(rowNr)
                                                .getEnd();
                            if (cards.size() > 0)
                            {
                                this.drawCardPileInSlot(graphics,
                                    slot.x, slot.y, cards, true);
                            }
                        }
                    }
                    else if (slot.y == 5)
                    {
                        this.drawCardInSlot(graphics, slot.x, slot.y,
                            card);
                    }
                }
            }
        }
    }

    /**
     * TODO: javadoc
     *
     * @param graphics TODO: javadoc
     */
    private void drawStatus(Graphics graphics)
    {
        this.drawCardsFinished(graphics);
        this.drawNrOfMoves(graphics);
    }

    /**
     * TODO: javadoc
     */
    private void finishTableau()
    {
        StopWatch.getStopWatch().stop();
        scoreEntries.addEntry(
            (String) Dialogs.askName(
                this.getParentWindow(),
                Settings.userName),
            this.nrOfMoves,
            StopWatch.getStopWatch().get());
        this.showScores();
        this.newGame();
    }

    /**
     * TODO: javadoc
     */
    private void init()
    {
        this.selectedCard = null;
        this.nrOfMoves = 0;
        StopWatch.getStopWatch().reset();
        StopWatch.getStopWatch().start();
    }

    /**
     * TODO: javadoc
     *
     * @param graphics TODO: javadoc
     * @param newSelectedCard TODO: javadoc
     *
     * @return TODO: javadoc
     */
    private boolean tryMove(Graphics graphics,
        CardSelection newSelectedCard)
    {
        if (this.tableau.move(selectedCard, newSelectedCard))
        {
            ++this.nrOfMoves;
            this.drawSlot(
                graphics,
                selectedCard.getSlot());
            this.drawSlot(
                graphics,
                newSelectedCard.getSlot());
            this.drawStatus(graphics);
            this.selectedCard = null;
            if (this.tableau.isFinished())
            {
                this.finishTableau();
            }
            return true;
        }
        return false;
    }
}
