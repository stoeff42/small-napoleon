package ch.cs.napoleon.solitaire;

import ch.cs.solitaire.Card;

import java.io.Serializable;

import java.util.ArrayList;


/**
 * TODO:
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class Help implements Serializable
{
    //~ Instance variables -------------------------------------------

    /** TODO: */
    private ArrayList help;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new Help object.
     */
    public Help()
    {
        this.help = new ArrayList(4);
    }

    /**
     * Creates a new Help object.
     *
     * @param cards TODO:
     */
    public Help(ArrayList cards)
    {
        this();
        this.set(cards);
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO:
     *
     * @param help TODO:
     */
    public void setHelp(ArrayList help)
    {
        this.help = help;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public ArrayList getHelp()
    {
        return this.help;
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
        return (Card) this.help.get(i);
    }

    /**
     * TODO:
     *
     * @param i TODO:
     */
    public void remove(int i)
    {
        this.help.set(i, null);
    }

    /**
     * TODO:
     *
     * @param cards TODO:
     */
    public void set(ArrayList cards)
    {
        if (cards.size() <= 4)
        {
            this.help.clear();
            this.help.addAll(cards);
        }
    }

    /**
     * TODO:
     *
     * @param i TODO:
     * @param card TODO:
     */
    public void set(int i, Card card)
    {
        this.help.set(i, card);
    }
}
