package ch.cs.solitaire;

import java.io.Serializable;


/**
 * TODO:
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class Card implements Serializable
{
    //~ Instance variables -------------------------------------------

    /** TODO: */
    private byte number;

    /** TODO: */
    private byte pack;

    /** TODO: */
    private byte rank;

    /** TODO: */
    private byte suit;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new Card object.
     */
    public Card()
    {
    }

    /**
     * Creates a new Card object.
     *
     * @param number TODO:
     * @param pack TODO:
     */
    public Card(byte number, byte pack)
    {
        this.number = (byte) (number % 52);
        this.pack = pack;
        this.rank = (byte) ((this.number % 13) + 1);
        this.suit = (byte) ((this.number / 13) + 1);
    }

    /**
     * Creates a new Card object.
     *
     * @param rank TODO:
     * @param suit TODO:
     * @param pack TODO:
     */
    public Card(byte rank, byte suit, byte pack)
    {
        this.rank = rank;
        this.suit = suit;
        this.pack = pack;
        this.number =
            (byte) ((this.rank - 1) + ((this.suit - 1) * 13));
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO:
     *
     * @param number TODO:
     */
    public void setNumber(byte number)
    {
        this.number = number;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public byte getNumber()
    {
        return this.number;
    }

    /**
     * TODO:
     *
     * @param pack TODO:
     */
    public void setPack(byte pack)
    {
        this.pack = pack;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public byte getPack()
    {
        return this.pack;
    }

    /**
     * TODO:
     *
     * @param rank TODO:
     */
    public void setRank(byte rank)
    {
        this.rank = rank;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public byte getRank()
    {
        return this.rank;
    }

    /**
     * TODO:
     *
     * @param suit TODO:
     */
    public void setSuit(byte suit)
    {
        this.suit = suit;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public byte getSuit()
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
    public boolean follows(Card card)
    {
        if (card == null)
        {
            return false;
        }
        return (((this.getSuit() == card.getSuit())
            && (((this.getRank() % 13) + 1) == card.getRank()))
            || ((card.getSuit() == this.getSuit())
            && (((card.getRank() % 13) + 1) == this.getRank())));
    }

    /**
     * TODO:
     *
     * @param card1 TODO:
     * @param card2 TODO:
     *
     * @return TODO:
     */
    public boolean follows(Card card1, Card card2)
    {
        if ((card1 == null) || (card2 == null))
        {
            return false;
        }
        return (((this.getSuit() == card1.getSuit())
            && (card1.getSuit() == card2.getSuit())
            && (((this.getRank() % 13) + 1) == card1.getRank())
            && (((card1.getRank() % 13) + 1) == card2.getRank()))
            || ((card2.getSuit() == card1.getSuit())
            && (card1.getSuit() == this.getSuit())
            && (((card2.getRank() % 13) + 1) == card1.getRank())
            && (((card1.getRank() % 13) + 1) == this.getRank())));
    }
}
