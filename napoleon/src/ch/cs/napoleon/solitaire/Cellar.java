package ch.cs.napoleon.solitaire;

import ch.cs.solitaire.Card;

import java.io.Serializable;


/**
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public class Cellar implements Serializable
{
    //~ Instance fields --------------------------------------------------------

    /** @TODO: javadoc! */
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
     * @param card javadoc!
     */
    public Cellar(final Card card)
    {
        this.cellar = card;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * @TODO: javadoc!
     *
     * @param cllr javadoc!
     */
    public final void setCellar(final Card cllr)
    {
        this.cellar = cllr;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final Card getCellar()
    {
        return this.cellar;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final boolean isEmpty()
    {
        return this.cellar == null;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final boolean clear()
    {
        this.cellar = null;

        return true;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final Card get()
    {
        return this.cellar;
    }

    /**
     * @TODO: javadoc!
     *
     * @param card javadoc!
     *
     * @return javadoc!
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
