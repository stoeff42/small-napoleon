package ch.cs.napoleon.solitaire.gui;

import ch.cs.solitaire.Card;

import java.awt.Point;

import java.io.Serializable;


/**
 * TODO:
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class CardSelection implements Serializable
{
    //~ Static variables/initializers --------------------------------

    /** TODO: */
    public static final int FOUNDATION = 0;

    /** TODO: */
    public static final int ROW = 1;

    /** TODO: */
    public static final int HELP = 2;

    /** TODO: */
    public static final int CELLAR = 3;

    //~ Instance variables -------------------------------------------

    /** TODO: */
    private Card card;

    /** TODO: */
    private Point slot;

    /** TODO: */
    private int info1;

    /** TODO: */
    private int info2;

    /** TODO: */
    private int type;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new CardSelection object.
     */
    public CardSelection()
    {
    }

    /**
     * Creates a new CardSelection object.
     *
     * @param card TODO:
     * @param slot TODO:
     * @param type TODO:
     * @param info1 TODO:
     * @param info2 TODO:
     */
    public CardSelection(Card card, Point slot, int type, int info1,
        int info2)
    {
        this.card = card;
        this.slot = slot;
        this.type = type;
        this.info1 = info1;
        this.info2 = info2;
    }

    /**
     * Creates a new CardSelection object.
     *
     * @param card TODO:
     * @param slot TODO:
     * @param type TODO:
     * @param info1 TODO:
     */
    public CardSelection(Card card, Point slot, int type, int info1)
    {
        this.card = card;
        this.slot = slot;
        this.type = type;
        this.info1 = info1;
    }

    /**
     * Creates a new CardSelection object.
     *
     * @param card TODO:
     * @param slot TODO:
     * @param type TODO:
     */
    public CardSelection(Card card, Point slot, int type)
    {
        this.card = card;
        this.slot = slot;
        this.type = type;
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO:
     *
     * @param card TODO:
     */
    public void setCard(Card card)
    {
        this.card = card;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public Card getCard()
    {
        return this.card;
    }

    /**
     * TODO:
     *
     * @param info1 TODO:
     */
    public void setInfo1(int info1)
    {
        this.info1 = info1;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public int getInfo1()
    {
        return this.info1;
    }

    /**
     * TODO:
     *
     * @param info2 TODO:
     */
    public void setInfo2(int info2)
    {
        this.info2 = info2;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public int getInfo2()
    {
        return this.info2;
    }

    /**
     * TODO:
     *
     * @param slot TODO:
     */
    public void setSlot(Point slot)
    {
        this.slot = slot;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public Point getSlot()
    {
        return this.slot;
    }

    /**
     * TODO:
     *
     * @param type TODO:
     */
    public void setType(int type)
    {
        this.type = type;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public int getType()
    {
        return this.type;
    }
}
