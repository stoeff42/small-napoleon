package ch.cs.napoleon.solitaire;

import ch.cs.solitaire.Card;

import java.io.Serializable;


/**
 * TODO:
 *
 * @author <a href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class Cellar implements Serializable
{
    //~ Instance fields --------------------------------------------------------

    /** TODO: */
    private Card cellar;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new Cellar object.
     */
    public Cellar()
    {
        /**
         * Empty constructor
         */
    }

    /**
     * Creates a new Cellar object.
     *
     * @param card TODO:
     */
    public Cellar(final Card card)
    {
        this.cellar = card;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * TODO:
     *
     * @param cllr TODO:
     */
    public final void setCellar(final Card cllr)
    {
        this.cellar = cllr;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final Card getCellar()
    {
        return this.cellar;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final boolean isEmpty()
    {
        return this.cellar == null;
    }

    /**
     * TODO:
     */
    public final void clear()
    {
        this.cellar = null;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final Card get()
    {
        return this.cellar;
    }

    /**
     * TODO:
     *
     * @param card TODO:
     *
     * @return TODO:
     */
    public final boolean set(final Card card)
    {
        if (this.cellar == null)
        {
            this.cellar = card;

            return true;
        }

        return false;
    }
}
