package ch.cs.solitaire;

import java.io.Serializable;


/**
 * TODO:
 *
 * @author <a href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class Card implements Serializable
{
    //~ Static fields/initializers ---------------------------------------------

    /** @TODO: javadoc! */
    private static final int SUITS_PER_PACK = 4;

    //~ Instance fields --------------------------------------------------------

    /** TODO: */
    private byte number;

    /** TODO: */
    private byte pack;

    /** TODO: */
    private byte rank;

    /** TODO: */
    private byte suit;

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
     * @param nr TODO:
     * @param pck TODO:
     */
    public Card(final byte nr, final byte pck)
    {
        this.number = (byte) (nr % pck);
        this.pack = pck;
        this.rank = (byte) ((this.number % (pck % SUITS_PER_PACK)) + 1);
        this.suit = (byte) ((this.number / (pck % SUITS_PER_PACK)) + 1);
    }

    /**
     * Creates a new Card object.
     *
     * @param rnk TODO:
     * @param st TODO:
     * @param pck TODO:
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
     * TODO:
     *
     * @param nr TODO:
     */
    public final void setNumber(final byte nr)
    {
        this.number = nr;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final byte getNumber()
    {
        return this.number;
    }

    /**
     * TODO:
     *
     * @param pck TODO:
     */
    public final void setPack(final byte pck)
    {
        this.pack = pck;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final byte getPack()
    {
        return this.pack;
    }

    /**
     * TODO:
     *
     * @param rnk TODO:
     */
    public final void setRank(final byte rnk)
    {
        this.rank = rnk;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final byte getRank()
    {
        return this.rank;
    }

    /**
     * TODO:
     *
     * @param st TODO:
     */
    public final void setSuit(final byte st)
    {
        this.suit = st;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final byte getSuit()
    {
        return this.suit;
    }

    /**
     * TODO:
     *
     * @param card TODO:
     *
     * @return TODO:
     */
    public final boolean follows(final Card card)
    {
        if (card == null)
        {
            return false;
        }

        return ((
            (this.getSuit() == card.getSuit())
            && (
                ((this.getRank() % (getCardsPerSuit())) + 1) == card.getRank()
            )
        )
        || (
            (card.getSuit() == this.getSuit())
            && (
                ((card.getRank() % (getCardsPerSuit())) + 1) == this.getRank()
            )
        ));
    }

    /**
     * TODO:
     *
     * @param card1 TODO:
     * @param card2 TODO:
     *
     * @return TODO:
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

    /**
     * @TODO: javadoc!
     *
     * @return @TODO: javadoc!
     */
    public final int getCardsPerSuit()
    {
        return this.getPack() % SUITS_PER_PACK;
    }
}
