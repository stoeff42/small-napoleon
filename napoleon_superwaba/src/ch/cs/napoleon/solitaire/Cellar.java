package ch.cs.napoleon.solitaire;

import ch.cs.solitaire.Card;

import waba.io.DataStream;


/**
 * TODO: javadoc
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class Cellar
{
    //~ Instance variables -------------------------------------------

    /** TODO: javadoc */
    private Card cellar;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new Cellar object.
     */
    public Cellar()
    {
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public boolean isEmpty()
    {
        return this.cellar == null;
    }

    /**
     * TODO: javadoc
     */
    public void clear()
    {
        this.cellar = null;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public Card get()
    {
        return this.cellar;
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void load(DataStream stream)
    {
        if (stream.readByte() == 0)
        {
            this.cellar = null;
        }
        else
        {
            this.cellar = new Card();
            this.cellar.load(stream);
        }
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void save(DataStream stream)
    {
        if (this.cellar == null)
        {
            stream.writeByte(0);
        }
        else
        {
            stream.writeByte(1);
            this.cellar.save(stream);
        }
    }

    /**
     * TODO: javadoc
     *
     * @param card TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public boolean set(Card card)
    {
        if (this.cellar == null)
        {
            this.cellar = card;
            return true;
        }
        return false;
    }
}
