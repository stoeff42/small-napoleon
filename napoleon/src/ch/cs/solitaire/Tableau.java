package ch.cs.solitaire;

import java.io.Serializable;

import java.util.Random;


/**
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public class Tableau implements Serializable
{
    //~ Static fields/initializers ---------------------------------------------

    /** @TODO: javadoc! */
    public static final int PACK_OF_52 = 52;

    /** @TODO: javadoc! */
    public static final int PACK_OF_36 = 36;

    /** @TODO: javadoc! */
    public static final int PACK_OF_32 = 32;

    //~ Instance fields --------------------------------------------------------

    /** @TODO: javadoc! */
    private Card[] tableau;

    /** @TODO: javadoc! */
    private int nrOfPacks;

    /** @TODO: javadoc! */
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
     * @param pckTpe javadoc!
     * @param nOPcks javadoc!
     */
    public Tableau(final int pckTpe, final int nOPcks)
    {
        this.packType = pckTpe;
        this.nrOfPacks = nOPcks;
        this.tableau = new Card[this.packType * this.nrOfPacks];

        for (int i = 0; i < this.tableau.length; i++)
        {
            this.tableau[i] =
                new Card(this.packType, (byte) (i % this.packType),
                    (byte) (i / this.packType));
        }

        this.shuffle();
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * @TODO: javadoc!
     *
     * @param nOPcks javadoc!
     */
    public final void setNrOfPacks(final int nOPcks)
    {
        this.nrOfPacks = nOPcks;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final int getNrOfPacks()
    {
        return this.nrOfPacks;
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
     * @return javadoc!
     */
    public final int getSize()
    {
        return this.getTableau().length;
    }

    /**
     * @TODO: javadoc!
     *
     * @param tabl javadoc!
     */
    public final void setTableau(final Card[] tabl)
    {
        this.tableau = tabl;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final Card[] getTableau()
    {
        return this.tableau;
    }

    /**
     * @TODO: javadoc!
     *
     * @param i javadoc!
     *
     * @return javadoc!
     */
    public final Card get(final int i)
    {
        return this.tableau[i];
    }

    /**
     * @TODO: javadoc!
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
     * @TODO: javadoc!
     *
     * @param i javadoc!
     * @param card javadoc!
     */
    private void set(final int i, final Card card)
    {
        this.tableau[i] = card;
    }
}
