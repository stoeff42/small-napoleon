package ch.cs.solitaire;

import java.io.Serializable;


/**
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public class Card implements Serializable
{
    //~ Static fields/initializers ---------------------------------------------

    /** @TODO: javadoc! */
    private static final int SUITS_PER_PACK = 4;

    //~ Instance fields --------------------------------------------------------

    /** @TODO: javadoc! */
    private byte number;

    /** @TODO: javadoc! */
    private byte pack;

    /** @TODO: javadoc! */
    private byte rank;

    /** @TODO: javadoc! */
    private byte suit;

    /** @TODO: javadoc! */
    private int packType;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new Card object.
     */
    public Card()
    {
        /**
         * Make constructor private in order to implement singleton correctly
         */
    }

    /**
     * Creates a new Card object.
     *
     * @param pckTpe javadoc!
     * @param nr javadoc!
     * @param pck javadoc!
     */
    public Card(final int pckTpe, final byte nr, final byte pck)
    {
        this.packType = pckTpe;
        this.number = (byte) (nr % pckTpe);
        this.pack = pck;
        this.rank = (byte) ((this.number % (pckTpe / SUITS_PER_PACK)) + 1);
        this.suit = (byte) ((this.number / (pckTpe / SUITS_PER_PACK)) + 1);
    }

    /**
     * Creates a new Card object.
     *
     * @param rnk javadoc!
     * @param st javadoc!
     * @param pck javadoc!
     */
    public Card(final byte rnk, final byte st, final byte pck)
    {
        this.rank = rnk;
        this.suit = st;
        this.pack = pck;
        this.number =
            (byte) (
                (this.rank - 1) + (((this.suit - 1) * pck) % SUITS_PER_PACK)
            );
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final int getCardsPerSuit()
    {
        return this.getPackType() / SUITS_PER_PACK;
    }

    /**
     * @TODO: javadoc!
     *
     * @param nr javadoc!
     */
    public final void setNumber(final byte nr)
    {
        this.number = nr;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final byte getNumber()
    {
        return this.number;
    }

    /**
     * @TODO: javadoc!
     *
     * @param pck javadoc!
     */
    public final void setPack(final byte pck)
    {
        this.pack = pck;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final byte getPack()
    {
        return this.pack;
    }

    /**
     * @TODO: javadoc!
     *
     * @param pckTpe javadoc!
     */
    public final void setPackType(final int pckTpe)
    {
        this.packType = pckTpe;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final int getPackType()
    {
        return this.packType;
    }

    /**
     * @TODO: javadoc!
     *
     * @param rnk javadoc!
     */
    public final void setRank(final byte rnk)
    {
        this.rank = rnk;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final byte getRank()
    {
        return this.rank;
    }

    /**
     * @TODO: javadoc!
     *
     * @param st javadoc!
     */
    public final void setSuit(final byte st)
    {
        this.suit = st;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final byte getSuit()
    {
        return this.suit;
    }

    /**
     * @TODO: javadoc!
     *
     * @param card javadoc!
     *
     * @return javadoc!
     */
    public final boolean follows(final Card card)
    {
        if (card == null)
        {
            return false;
        }

        return ((
            (this.getSuit() == card.getSuit())
            && (((this.getRank() % getCardsPerSuit()) + 1) == card.getRank())
        )
        || (
            (card.getSuit() == this.getSuit())
            && (((card.getRank() % getCardsPerSuit()) + 1) == this.getRank())
        ));
    }

    /**
     * @TODO: javadoc!
     *
     * @param card1 javadoc!
     * @param card2 javadoc!
     *
     * @return javadoc!
     */
    public final boolean follows(final Card card1, final Card card2)
    {
        if ((card1 == null) || (card2 == null))
        {
            return false;
        }

        return ((
            (this.getSuit() == card1.getSuit())
            && (card1.getSuit() == card2.getSuit())
            && (
                ((this.getRank() % (getCardsPerSuit())) + 1) == card1.getRank()
            )
            && (
                ((card1.getRank() % (getCardsPerSuit())) + 1) == card2.getRank()
            )
        )
        || (
            (card2.getSuit() == card1.getSuit())
            && (card1.getSuit() == this.getSuit())
            && (
                ((card2.getRank() % (getCardsPerSuit())) + 1) == card1.getRank()
            )
            && (
                ((card1.getRank() % (getCardsPerSuit())) + 1) == this.getRank()
            )
        ));
    }
}
