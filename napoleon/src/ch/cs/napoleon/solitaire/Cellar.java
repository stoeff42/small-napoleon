package ch.cs.napoleon.solitaire;

import ch.cs.solitaire.Card;

import java.io.Serializable;


/**
 * TODO:
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class Cellar implements Serializable
{
    //~ Instance variables -------------------------------------------

    /** TODO: */
    private Card cellar;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new Cellar object.
     */
    public Cellar()
    {
    }

    /**
     * Creates a new Cellar object.
     *
     * @param card TODO:
     */
    public Cellar(Card card)
    {
        this.cellar = card;
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO:
     *
     * @param cellar TODO:
     */
    public void setCellar(Card cellar)
    {
        this.cellar = cellar;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public Card getCellar()
    {
        return cellar;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public boolean isEmpty()
    {
        return this.cellar == null;
    }

    /**
     * TODO:
     */
    public void clear()
    {
        this.cellar = null;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public Card get()
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
