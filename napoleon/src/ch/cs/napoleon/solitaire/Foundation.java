package ch.cs.napoleon.solitaire;

import ch.cs.solitaire.Card;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public class Foundation implements Serializable
{
    //~ Static fields/initializers ---------------------------------------------

    /** @TODO: javadoc! */
    private static final int FOUNDATION_SIZE = 13;

    //~ Instance fields --------------------------------------------------------

    /** @TODO: javadoc! */
    private ArrayList foundation;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new Foundation object.
     */
    public Foundation()
    {
        this.foundation = new ArrayList(FOUNDATION_SIZE);
    }

    /**
     * Creates a new Foundation object.
     *
     * @param cards javadoc!
     */
    public Foundation(final ArrayList cards)
    {
        this();
        this.set(cards);
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final Card getFirst()
    {
        return (Card) this.foundation.get(0);
    }

    /**
     * @TODO: javadoc!
     *
     * @param fnd javadoc!
     */
    public final void setFoundation(final ArrayList fnd)
    {
        this.foundation = fnd;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final ArrayList getFoundation()
    {
        return this.foundation;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final Card getLast()
    {
        try
        {
            return (Card) this.foundation.get(this.foundation.size() - 1);
        }
        catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final Card getSecondLast()
    {
        try
        {
            return (Card) this.foundation.get(this.foundation.size() - 2);
        }
        catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final int getSize()
    {
        return this.foundation.size();
    }

    /**
     * @TODO: javadoc!
     *
     * @param card javadoc!
     *
     * @return javadoc!
     */
    public final boolean add(final Card card)
    {
        Card last = this.getLast();
        Card secondLast = this.getSecondLast();

        if (last != null)
        {
            if (secondLast != null)
            {
                if (secondLast.follows(last, card))
                {
                    this.foundation.add(card);

                    return true;
                }
                else
                {
                    return false;
                }
            }

            if (last.follows(card))
            {
                this.foundation.add(card);

                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final boolean remove()
    {
        if (this.foundation.size() > 1)
        {
            this.foundation.remove(this.foundation.size() - 1);

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @param cards javadoc!
     */
    public final void set(final ArrayList cards)
    {
        this.foundation.clear();

        Iterator iter = cards.iterator();
        this.set((Card) iter.next());

        while (iter.hasNext())
        {
            this.add((Card) iter.next());
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @param card javadoc!
     */
    public final void set(final Card card)
    {
        this.foundation.clear();
        this.foundation.add(card);
    }
}
