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
public class CardSelection {
    /** TODO: javadoc */
    public static final int FOUNDATION = 0;

    /** TODO: javadoc */
    public static final int ROW = 1;

    /** TODO: javadoc */
    public static final int HELP = 2;

    /** TODO: javadoc */
    public static final int CELLAR = 3;

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

    /**
     * Creates a new CardSelection object.
     */
    public CardSelection() {
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
    public CardSelection(Card crd, Coord slt, int tpe, int inf1, int inf2) {
        this.card = crd;
        this.slot = slt;
        this.type = tpe;
        this.info1 = inf1;
        this.info2 = inf2;
    }

    /**
     * Creates a new CardSelection object.
     *
     * @param card TODO: javadoc
     * @param slot TODO: javadoc
     * @param type TODO: javadoc
     * @param info1 TODO: javadoc
     */
    public CardSelection(Card crd, Coord slt, int tpe, int inf1) {
        this.card = crd;
        this.slot = slt;
        this.type = tpe;
        this.info1 = inf1;
    }

    /**
     * Creates a new CardSelection object.
     *
     * @param card TODO: javadoc
     * @param slot TODO: javadoc
     * @param type TODO: javadoc
     */
    public CardSelection(Card crd, Coord slt, int tpe) {
        this.card = crd;
        this.slot = slt;
        this.type = tpe;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public Card getCard() {
        return this.card;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public int getInfo1() {
        return this.info1;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public int getInfo2() {
        return this.info2;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public Coord getSlot() {
        return this.slot;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public int getType() {
        return this.type;
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void load(DataStream stream) {
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
    public void save(DataStream stream) {
        card.save(stream);
        stream.writeByte(this.slot.x);
        stream.writeByte(this.slot.y);
        stream.writeByte(this.info1);
        stream.writeByte(this.info2);
        stream.writeByte(this.type);
    }
}
