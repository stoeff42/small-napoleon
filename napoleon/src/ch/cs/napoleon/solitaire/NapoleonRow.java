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
public class NapoleonRow implements Serializable
{
    //~ Static fields/initializers ---------------------------------------------

    /** @TODO: javadoc! */
    private static final int BASE_SIZE = 5;

    /** @TODO: javadoc! */
    private static final int END_SIZE = 13;

    //~ Instance fields --------------------------------------------------------

    /** @TODO: javadoc! */
    private ArrayList base;

    /** @TODO: javadoc! */
    private ArrayList end;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new NapoleonRow object.
     */
    public NapoleonRow()
    {
        this.base = new ArrayList(BASE_SIZE);
        this.end = new ArrayList(END_SIZE);
    }

    /**
     * Creates a new NapoleonRow object.
     *
     * @param cards javadoc!
     */
    public NapoleonRow(final ArrayList cards)
    {
        this();
        this.set(cards);
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * @TODO: javadoc!
     *
     * @param bs javadoc!
     */
    public final void setBase(final ArrayList bs)
    {
        this.base = bs;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final ArrayList getBase()
    {
        return this.base;
    }

    /**
     * @TODO: javadoc!
     *
     * @param nd javadoc!
     */
    public final void setEnd(final ArrayList nd)
    {
        this.end = nd;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final ArrayList getEnd()
    {
        return this.end;
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
            return (Card) this.end.get(this.end.size() - 1);
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
            return (Card) this.end.get(this.end.size() - 2);
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
        return this.base.size() + this.end.size();
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

        if (last != null)
        {
            Card secondLast = this.getSecondLast();

            if (secondLast != null)
            {
                if (secondLast.follows(last, card))
                {
                    this.end.add(card);

                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                if (last.follows(card))
                {
                    this.end.add(card);

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            this.end.add(card);

            return true;
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @param card javadoc!
     *
     * @return javadoc!
     */
    public final boolean forceAdd(final Card card)
    {
        Card last = this.getLast();

        if (last != null)
        {
            if (this.end.size() == 1)
            {
                this.base.addAll(this.end);
                this.end.clear();
                this.end.add(card);

                return true;
            }
        }
        else
        {
            this.end.add(card);

            return true;
        }

        return false;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final boolean remove()
    {
        if (this.end.size() > 0)
        {
            this.end.remove(this.end.size() - 1);

            if ((this.end.size() == 0) && (this.base.size() > 0))
            {
                this.end.add(this.base.remove(this.base.size() - 1));
            }

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
        this.base.clear();
        this.end.clear();

        for (Iterator iter = cards.iterator(); iter.hasNext();)
        {
            Card card = (Card) iter.next();

            if (iter.hasNext())
            {
                this.base.add(card);
            }
            else
            {
                this.end.add(card);
            }
        }
    }
}
