package ch.cs.solitaire;

import java.io.Serializable;

import java.util.Random;


/**
 * TODO:
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class Tableau implements Serializable
{
    //~ Static variables/initializers --------------------------------

    /** TODO: */
    public static final int PACK_OF_52 = 52;

    /** TODO: */
    public static final int PACK_OF_36 = 36;

    /** TODO: */
    public static final int PACK_OF_32 = 32;

    //~ Instance variables -------------------------------------------

    /** TODO: */
    private Card[] tableau;

    /** TODO: */
    private int nrOfPacks;

    /** TODO: */
    private int packType;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new Tableau object.
     */
    public Tableau()
    {
    }

    /**
     * Creates a new Tableau object.
     *
     * @param packType TODO:
     * @param nrOfPacks TODO:
     */
    public Tableau(int packType, int nrOfPacks)
    {
        this.packType = packType;
        this.nrOfPacks = nrOfPacks;
        this.tableau = new Card[this.packType * this.nrOfPacks];
        for (int i = 0; i < this.tableau.length; i++)
        {
            this.tableau[i] =
                new Card((byte) (i % this.packType),
                    (byte) (i / this.packType));
        }
        this.shuffle();
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO:
     *
     * @param nrOfPacks TODO:
     */
    public void setNrOfPacks(int nrOfPacks)
    {
        this.nrOfPacks = nrOfPacks;
    }

    /**
     * Returns the nrOfPacks.
     *
     * @return int
     */
    public int getNrOfPacks()
    {
        return this.nrOfPacks;
    }

    /**
     * TODO:
     *
     * @param packType TODO:
     */
    public void setPackType(int packType)
    {
        this.packType = packType;
    }

    /**
     * Returns the packType.
     *
     * @return int
     */
    public int getPackType()
    {
        return this.packType;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public int getSize()
    {
        return this.getTableau().length;
    }

    /**
     * TODO:
     *
     * @param tableau TODO:
     */
    public void setTableau(Card[] tableau)
    {
        this.tableau = tableau;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public Card[] getTableau()
    {
        return tableau;
    }

    /**
     * TODO:
     *
     * @param i TODO:
     *
     * @return TODO:
     */
    public Card get(int i)
    {
        return this.tableau[i];
    }

    /**
     * TODO:
     */
    public void shuffle()
    {
        Random rnd = new Random();
        Card tmp;
        int j = 0;
        for (int i = 0; i < this.getSize(); i++)
        {
            j = Math.abs(rnd.nextInt()) % this.getSize();
            tmp = this.get(i);
            this.set(
                i,
                this.get(j));
            this.set(j, tmp);
        }
    }

    /**
     * TODO:
     *
     * @param i TODO:
     * @param card TODO:
     */
    private void set(int i, Card card)
    {
        this.tableau[i] = card;
    }
}
