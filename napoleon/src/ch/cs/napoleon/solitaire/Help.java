package ch.cs.napoleon.solitaire;

import ch.cs.solitaire.Card;

import java.io.Serializable;

import java.util.ArrayList;


/**
 * TODO:
 *
 * @author <a href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class Help implements Serializable
{
    //~ Static fields/initializers ---------------------------------------------

    /** @TODO: javadoc! */
    private static final int HELP_SIZE = 4;

    //~ Instance fields --------------------------------------------------------

    /** TODO: */
    private ArrayList help;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new Help object.
     */
    public Help()
    {
        this.help = new ArrayList(HELP_SIZE);
    }

    /**
     * Creates a new Help object.
     *
     * @param cards TODO:
     */
    public Help(final ArrayList cards)
    {
        this();
        this.set(cards);
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * TODO:
     *
     * @param hlp TODO:
     */
    public final void setHelp(final ArrayList hlp)
    {
        this.help = hlp;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final ArrayList getHelp()
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
    public final Card get(final int i)
    {
        return (Card) this.help.get(i);
    }

    /**
     * TODO:
     *
     * @param i TODO:
     */
    public final void remove(final int i)
    {
        this.help.set(i, null);
    }

    /**
     * TODO:
     *
     * @param cards TODO:
     */
    public final void set(final ArrayList cards)
    {
        if (cards.size() <= HELP_SIZE)
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
    public final void set(final int i, final Card card)
    {
        this.help.set(i, card);
    }
}
