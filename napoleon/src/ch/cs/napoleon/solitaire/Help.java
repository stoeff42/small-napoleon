package ch.cs.napoleon.solitaire;

import ch.cs.solitaire.Card;

import java.io.Serializable;

import java.util.ArrayList;


/**
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public class Help implements Serializable
{
    //~ Static fields/initializers ---------------------------------------------

    /** @TODO: javadoc! */
    private static final int HELP_SIZE = 4;

    //~ Instance fields --------------------------------------------------------

    /** @TODO: javadoc! */
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
     * @param cards javadoc!
     */
    public Help(final ArrayList cards)
    {
        this();
        this.set(cards);
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * @TODO: javadoc!
     *
     * @param hlp javadoc!
     */
    public final void setHelp(final ArrayList hlp)
    {
        this.help = hlp;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final ArrayList getHelp()
    {
        return this.help;
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
        return (Card) this.help.get(i);
    }

    /**
     * @TODO: javadoc!
     *
     * @param i javadoc!
     *
     * @return javadoc!
     */
    public final boolean remove(final int i)
    {
        this.help.set(i, null);

        return true;
    }

    /**
     * @TODO: javadoc!
     *
     * @param cards javadoc!
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
     * @TODO: javadoc!
     *
     * @param i javadoc!
     * @param card javadoc!
     */
    public final void set(final int i, final Card card)
    {
        this.help.set(i, card);
    }
}
