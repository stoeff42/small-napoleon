package ch.cs.solitaire;

import waba.io.DataStream;

import waba.util.Random;


/**
 * TODO: javadoc
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class Tableau
{
    //~ Static variables/initializers --------------------------------

    /** TODO: javadoc */
    public static final int PACK_OF_52 = 52;

    /** TODO: javadoc */
    public static final int PACK_OF_36 = 36;

    /** TODO: javadoc */
    public static final int PACK_OF_32 = 32;

    //~ Instance variables -------------------------------------------

    /** TODO: javadoc */
    private Card[] tableau;

    /** TODO: javadoc */
    private int nrOfPacks;

    /** TODO: javadoc */
    private int packType;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new Tableau object.
     *
     * @param packType TODO: javadoc
     * @param nrOfPacks TODO: javadoc
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
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public int getSize()
    {
        return this.tableau.length;
    }

    /**
     * TODO: javadoc
     *
     * @param i TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public Card get(int i)
    {
        return this.tableau[i];
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void load(DataStream stream)
    {
        this.tableau = new Card[stream.readInt()];
        for (int i = 0; i < this.tableau.length; i++)
        {
            this.tableau[i] = new Card();
            this.tableau[i].load(stream);
        }
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void save(DataStream stream)
    {
        stream.writeInt(this.tableau.length);
        for (int i = 0; i < this.tableau.length; i++)
        {
            this.tableau[i].save(stream);
        }
    }

    /**
     * TODO: javadoc
     */
    public void shuffle()
    {
        Random rnd = new Random();
        Card tmp;
        int j = 0;
        for (int i = 0; i < this.tableau.length; i++)
        {
            j = rnd.nextInt(this.tableau.length);
            tmp = this.get(i);
            this.tableau[i] = this.get(j);
            this.tableau[j] = tmp;
        }
    }
}
