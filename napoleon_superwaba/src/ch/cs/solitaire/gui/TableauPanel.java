package ch.cs.solitaire.gui;

import ch.cs.solitaire.Card;

import waba.fx.Coord;
import waba.fx.Font;
import waba.fx.Graphics;
import waba.fx.Image;

import waba.ui.Control;

import waba.util.Vector;


/**
 * TODO: javadoc
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public abstract class TableauPanel extends Control
{
    //~ Static variables/initializers --------------------------------

    /** TODO: javadoc */
    public static final int LEFT_PILE = 0;

    /** TODO: javadoc */
    public static final int UP_PILE = 1;

    /** TODO: javadoc */
    public static final int RIGHT_PILE = 2;

    /** TODO: javadoc */
    public static final int DOWN_PILE = 3;

    //~ Instance variables -------------------------------------------

    /** TODO:  javadoc */
    private DeckSet deckSet;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new TableauPanel object.
     */
    public TableauPanel()
    {
        super();
        this.setFont(this.getFont());
    }

    //~ Methods ------------------------------------------------------

    /**
     * Returns the cardSlotOffset.
     *
     * @return int
     */
    public Font getFont()
    {
        return this.getDeckSet().getFont();
    }

    /**
     * TODO: javadoc
     *
     * @param graphics TODO: javadoc
     */
    public void onPaint(Graphics graphics)
    {
        this.drawDeck(graphics);
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    protected Image getBackSuit()
    {
        return this.getCardSuits()[4][0];
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    protected Coord getCardSize()
    {
        return this.getDeckSet().getCardSize();
    }

    /**
     * Returns the cardSlotOffset.
     *
     * @return int
     */
    protected int getCardSlotOffset()
    {
        return this.getDeckSet().getCardSlotOffset();
    }

    /**
     * Returns the fontHeight.
     *
     * @return int
     */
    protected int getFontHeight()
    {
        return this.getDeckSet().getFontHeight();
    }

    /**
     * Returns the maxPileSize.
     *
     * @return int
     */
    protected int getMaxPileSize()
    {
        return this.getDeckSet().getMaxPileSize();
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    protected int getPileOffset()
    {
        return this.getDeckSet().getPileOffset();
    }

    /**
     * TODO: javadoc
     *
     * @param pileSize TODO: javadoc
     *
     * @return TODO: javadoc
     */
    protected int getPileSize(int pileSize)
    {
        return (pileSize > 0)
        ? Math.min(
            pileSize,
            this.getMaxPileSize()) : 0;
    }

    /**
     * Returns the fontWidth.
     *
     * @param text TODO: javadoc
     *
     * @return int
     */
    protected int getTextWidth(String text)
    {
        return this.getDeckSet().getTextWidth(text);
    }

    /**
     * TODO: javadoc
     *
     * @param graphics TODO: javadoc
     * @param x TODO: javadoc
     * @param y TODO: javadoc
     */
    protected void drawBack(Graphics graphics, int x, int y)
    {
        graphics.drawImage(
            this.getBackSuit(),
            x,
            y);
    }

    /**
     * TODO: javadoc
     *
     * @param graphics TODO: javadoc
     * @param x TODO: javadoc
     * @param y TODO: javadoc
     * @param card TODO: javadoc
     * @param front TODO: javadoc
     */
    protected void drawCard(Graphics graphics, int x, int y,
        Card card, boolean front)
    {
        if (front && (card != null))
        {
            this.drawFront(graphics, x, y, card);
        }
        else
        {
            this.drawBack(graphics, x, y);
        }
    }

    /**
     * TODO: javadoc
     *
     * @param graphics TODO: javadoc
     */
    protected abstract void drawDeck(Graphics graphics);

    /**
     * TODO: DOWN_PILE and UP_PILE implementation
     *
     * @param graphics TODO: javadoc
     * @param cards TODO: javadoc
     * @param front TODO: javadoc
     * @param pileFront TODO: javadoc
     * @param slotLocation TODO: javadoc
     * @param dir TODO: javadoc
     * @param clear TODO: javadoc
     *
     * @throws RuntimeException TODO: javadoc
     */
    protected void drawCardPile(Graphics graphics, Vector cards,
        boolean front, boolean pileFront, Coord slotLocation,
        int dir, boolean clear)
    {
        int pileSize = this.getPileSize(cards.size());
        int clearOffset = (pileSize - 1) * this.getPileOffset();
        if (dir == LEFT_PILE)
        {
            if (clear)
            {
                graphics.fillRect(slotLocation.x - clearOffset
                    - this.getPileOffset() - 1, slotLocation.y,
                    clearOffset + this.getPileOffset() + 1,
                    this.getCardSize().y + 1);
            }
            for (int i = 0; i < (pileSize - 1); i++)
            {
                this.drawCard(graphics,
                    slotLocation.x - (i * getPileOffset()),
                    slotLocation.y,
                    (Card) cards.get((i == 0) ? 0
                                              : (cards.size()
                        - pileSize + i)), pileFront);
            }
            this.drawCard(graphics, slotLocation.x - clearOffset,
                slotLocation.y, (Card) cards.get(cards.size() - 1),
                front);
        }
        else if (dir == RIGHT_PILE)
        {
            if (clear)
            {
                graphics.fillRect(slotLocation.x
                    + this.getCardSize().x, slotLocation.y,
                    clearOffset + this.getPileOffset() + 1,
                    this.getCardSize().y + 1);
            }
            for (int i = 0; i < (pileSize - 1); i++)
            {
                this.drawCard(graphics,
                    slotLocation.x + (i * getPileOffset()),
                    slotLocation.y,
                    (Card) cards.get((i == 0) ? 0
                                              : (cards.size()
                        - pileSize + i)), pileFront);
            }
            this.drawCard(graphics, slotLocation.x + clearOffset,
                slotLocation.y, (Card) cards.get(cards.size() - 1),
                front);
        }

        else if (dir == UP_PILE)
        {
            throw new RuntimeException();
        }
        else if (dir == DOWN_PILE)
        {
            throw new RuntimeException();
        }
    }

    /**
     * TODO: javadoc
     *
     * @param graphics TODO: javadoc
     * @param x TODO: javadoc
     * @param y TODO: javadoc
     * @param card TODO: javadoc
     */
    protected void drawFront(Graphics graphics, int x, int y,
        Card card)
    {
        if (card == null)
        {
            return;
        }
        graphics.drawImage(
            this.getCardSuit(card),
            x,
            y);
    }

    /**
     * TODO: javadoc
     *
     * @param graphics TODO: javadoc
     * @param x TODO: javadoc
     * @param y TODO: javadoc
     */
    protected void drawSelectedBack(Graphics graphics, int x, int y)
    {
        graphics.drawImage(
            this.getBackSuit(),
            x,
            y,
            Graphics.DRAW_PAINT_INVERSE,
            this.getBackColor(),
            false);
    }

    /**
     * TODO: javadoc
     *
     * @param graphics TODO: javadoc
     * @param x TODO: javadoc
     * @param y TODO: javadoc
     * @param card TODO: javadoc
     * @param front TODO: javadoc
     * @param selected TODO: javadoc
     */
    protected void drawSelectedCard(Graphics graphics, int x, int y,
        Card card, boolean front, boolean selected)
    {
        if (card == null)
        {
            return;
        }
        if (selected == false)
        {
            this.drawCard(graphics, x, y, card, front);
        }
        else
        {
            if (front)
            {
                this.drawSelectedFront(graphics, x, y, card);
            }
            else
            {
                this.drawSelectedBack(graphics, x, y);
            }
        }
    }

    /**
     * TODO: javadoc
     *
     * @param graphics TODO: javadoc
     * @param x TODO: javadoc
     * @param y TODO: javadoc
     * @param card TODO: javadoc
     */
    protected void drawSelectedFront(Graphics graphics, int x, int y,
        Card card)
    {
        if (card == null)
        {
            return;
        }
        graphics.drawImage(
            this.getCardSuit(card),
            x,
            y,
            Graphics.DRAW_PAINT_INVERSE,
            this.getBackColor(),
            false);
    }

    /**
     * TODO: javadoc
     *
     * @param card TODO: javadoc
     *
     * @return TODO: javadoc
     */
    private Image getCardSuit(Card card)
    {
        return this.getCardSuits()[card.getSuit() - 1][card.getRank()
        - 1];
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    private Image[][] getCardSuits()
    {
        return this.getDeckSet().getCardSuits();
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    private DeckSet getDeckSet()
    {
        if (this.deckSet == null)
        {
            this.deckSet = new DeckSet(this);
        }
        return this.deckSet;
    }
}
