package ch.cs.solitaire;

import java.io.Serializable;

import java.util.Random;


/**
 * TODO:
 *
 * @author <a href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class Tableau implements Serializable
{
    //~ Static fields/initializers ---------------------------------------------

    /** TODO: */
    public static final int PACK_OF_52 = 52;

    /** TODO: */
    public static final int PACK_OF_36 = 36;

    /** TODO: */
    public static final int PACK_OF_32 = 32;

    //~ Instance fields --------------------------------------------------------

    /** TODO: */
    private Card[] tableau;

    /** TODO: */
    private int nrOfPacks;

    /** TODO: */
    private int packType;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new Tableau object.
     */
    public Tableau()
    {
        /**
         * Empty constructor
         */
    }

    /**
     * Creates a new Tableau object.
     *
     * @param pckTpe TODO:
     * @param nOPcks TODO:
     */
    public Tableau(final int pckTpe, final int nOPcks)
    {
        this.packType = pckTpe;
        this.nrOfPacks = nOPcks;
        this.tableau = new Card[this.packType * this.nrOfPacks];

        for (int i = 0; i < this.tableau.length; i++)
        {
            this.tableau[i] =
                new Card((byte) (i % this.packType), (byte) (
                        i / this.packType
                    ));
        }

        this.shuffle();
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * TODO:
     *
     * @param nOPcks TODO:
     */
    public final void setNrOfPacks(final int nOPcks)
    {
        this.nrOfPacks = nOPcks;
    }

    /**
     * Returns the nrOfPacks.
     *
     * @return int
     */
    public final int getNrOfPacks()
    {
        return this.nrOfPacks;
    }

    /**
     * TODO:
     *
     * @param pckTpe TODO:
     */
    public final void setPackType(final int pckTpe)
    {
        this.packType = pckTpe;
    }

    /**
     * Returns the packType.
     *
     * @return int
     */
    public final int getPackType()
    {
        return this.packType;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final int getSize()
    {
        return this.getTableau().length;
    }

    /**
     * TODO:
     *
     * @param tabl TODO:
     */
    public final void setTableau(final Card[] tabl)
    {
        this.tableau = tabl;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final Card[] getTableau()
    {
        return this.tableau;
    }

    /**
     * TODO:
     *
     * @param i TODO:
     *
     * @return TODO:
     */
    public final Card get(final int i)
    {
        return this.tableau[i];
    }

    /**
     * TODO:
     */
    public final void shuffle()
    {
        Random rnd = new Random();
        Card tmp;
        int j = 0;

        for (int i = 0; i < this.getSize(); i++)
        {
            j = Math.abs(rnd.nextInt()) % this.getSize();
            tmp = this.get(i);
            this.set(i, this.get(j));
            this.set(j, tmp);
        }
    }

    /**
     * TODO:
     *
     * @param i TODO:
     * @param card TODO:
     */
    private void set(final int i, final Card card)
    {
        this.tableau[i] = card;
    }
}
