package ch.cs.napoleon.solitaire.gui;

import ch.cs.solitaire.Card;

import waba.fx.Coord;

import waba.io.DataStream;


/**
 * TODO: javadoc
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class CardSelection
{
    //~ Static variables/initializers --------------------------------

    /** TODO: javadoc */
    public static final int FOUNDATION = 0;

    /** TODO: javadoc */
    public static final int ROW = 1;

    /** TODO: javadoc */
    public static final int HELP = 2;

    /** TODO: javadoc */
    public static final int CELLAR = 3;

    //~ Instance variables -------------------------------------------

    /** TODO: javadoc */
    private Card card;

    /** TODO: javadoc */
    private Coord slot;

    /** TODO: javadoc */
    private int info1;

    /** TODO: javadoc */
    private int info2;

    /** TODO: javadoc */
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
     * @param card TODO: javadoc
     * @param slot TODO: javadoc
     * @param type TODO: javadoc
     * @param info1 TODO: javadoc
     * @param info2 TODO: javadoc
     */
    public CardSelection(Card card, Coord slot, int type, int info1,
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
     * @param card TODO: javadoc
     * @param slot TODO: javadoc
     * @param type TODO: javadoc
     * @param info1 TODO: javadoc
     */
    public CardSelection(Card card, Coord slot, int type, int info1)
    {
        this.card = card;
        this.slot = slot;
        this.type = type;
        this.info1 = info1;
    }

    /**
     * Creates a new CardSelection object.
     *
     * @param card TODO: javadoc
     * @param slot TODO: javadoc
     * @param type TODO: javadoc
     */
    public CardSelection(Card card, Coord slot, int type)
    {
        this.card = card;
        this.slot = slot;
        this.type = type;
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public Card getCard()
    {
        return this.card;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public int getInfo1()
    {
        return this.info1;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public int getInfo2()
    {
        return this.info2;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public Coord getSlot()
    {
        return this.slot;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public int getType()
    {
        return this.type;
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void load(DataStream stream)
    {
        this.card = new Card();
        card.load(stream);
        this.slot = new Coord();
        this.slot.x = stream.readByte();
        this.slot.y = stream.readByte();
        this.info1 = stream.readByte();
        this.info2 = stream.readByte();
        this.type = stream.readByte();
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void save(DataStream stream)
    {
        card.save(stream);
        stream.writeByte(this.slot.x);
        stream.writeByte(this.slot.y);
        stream.writeByte(this.info1);
        stream.writeByte(this.info2);
        stream.writeByte(this.type);
    }
}
