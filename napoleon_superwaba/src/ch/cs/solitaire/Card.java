package ch.cs.solitaire;

import waba.io.DataStream;

import waba.sys.Convert;


/**
 * TODO: javadoc
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class Card {
    /** TODO: javadoc */
    private byte number;

    /** TODO: javadoc */
    private byte pack;

    /** TODO: javadoc */
    private byte rank;

    /** TODO: javadoc */
    private byte suit;

    /**
     * Creates a new Card object.
     */
    public Card() {
    }

    /**
     * Creates a new Card object.
     *
     * @param number TODO: javadoc
     * @param pack TODO: javadoc
     */
    public Card(byte nmbr, byte pck) {
        this.init(nmbr, pck);
    }

    /**
     * Creates a new Card object.
     *
     * @param rank TODO: javadoc
     * @param suit TODO: javadoc
     * @param pack TODO: javadoc
     */
    public Card(byte rnk, byte st, byte pck) {
        this.init(rnk, st, pck);
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public byte getNumber() {
        return this.number;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public byte getPack() {
        return this.pack;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public byte getRank() {
        return this.rank;
    }

    /**
     * TODO: javadoc
     *
     * @param rank TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public static String getRankString(byte rank) {
        switch (rank) {
        case (0):
            return "A";

        case (10):
            return "J";

        case (11):
            return "Q";

        case (12):
            return "K";

        default:
            return Convert.toString(rank + 1);
        }
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public byte getSuit() {
        return this.suit;
    }

    /**
     * TODO: javadoc
     *
     * @param card TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public boolean follows(Card card) {
        if (card == null) {
            return false;
        }

        return (((this.getSuit() == card.getSuit()) &&
        (((this.getRank() % 13) + 1) == card.getRank())) ||
        ((card.getSuit() == this.getSuit()) &&
        (((card.getRank() % 13) + 1) == this.getRank())));
    }

    /**
     * TODO: javadoc
     *
     * @param card1 TODO: javadoc
     * @param card2 TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public boolean follows(Card card1, Card card2) {
        if ((card1 == null) || (card2 == null)) {
            return false;
        }

        return (((this.getSuit() == card1.getSuit()) &&
        (card1.getSuit() == card2.getSuit()) &&
        (((this.getRank() % 13) + 1) == card1.getRank()) &&
        (((card1.getRank() % 13) + 1) == card2.getRank())) ||
        ((card2.getSuit() == card1.getSuit()) &&
        (card1.getSuit() == this.getSuit()) &&
        (((card2.getRank() % 13) + 1) == card1.getRank()) &&
        (((card1.getRank() % 13) + 1) == this.getRank())));
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void load(DataStream stream) {
        this.init(stream.readByte(), stream.readByte());
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void save(DataStream stream) {
        stream.writeByte(this.number);
        stream.writeByte(this.pack);
    }

    /**
     * TODO: javadoc
     *
     * @param number TODO: javadoc
     * @param pack TODO: javadoc
     */
    private void init(byte nmbr, byte pck) {
        this.number = (byte) (nmbr % 52);
        this.pack = pck;
        this.rank = (byte) ((this.number % 13) + 1);
        this.suit = (byte) ((this.number / 13) + 1);
    }

    /**
     * TODO: javadoc
     *
     * @param rank TODO: javadoc
     * @param suit TODO: javadoc
     * @param pack TODO: javadoc
     */
    private void init(byte rnk, byte st, byte pck) {
        this.rank = rnk;
        this.suit = st;
        this.pack = pck;
        this.number = (byte) ((this.rank - 1) + ((this.suit - 1) * 13));
    }
}
