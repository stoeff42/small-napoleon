package ch.cs.napoleon.solitaire;

import ch.cs.solitaire.Card;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * TODO:
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class Foundation implements Serializable
{
    //~ Instance variables -------------------------------------------

    /** TODO: */
    private ArrayList foundation;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new Foundation object.
     */
    public Foundation()
    {
        this.foundation = new ArrayList(13);
    }

    /**
     * Creates a new Foundation object.
     *
     * @param cards TODO:
     */
    public Foundation(ArrayList cards)
    {
        this();
        this.set(cards);
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO:
     *
     * @return TODO:
     */
    public Card getFirst()
    {
        return (Card) this.foundation.get(0);
    }

    /**
     * TODO:
     *
     * @param foundation TODO:
     */
    public void setFoundation(ArrayList foundation)
    {
        this.foundation = foundation;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public ArrayList getFoundation()
    {
        return foundation;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public Card getLast()
    {
        try
        {
            return (Card) this.foundation.get(this.foundation.size()
                - 1);
        }
        catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public Card getSecondLast()
    {
        try
        {
            return (Card) this.foundation.get(this.foundation.size()
                - 2);
        }
        catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public int getSize()
    {
        return this.foundation.size();
    }

    /**
     * TODO:
     *
     * @param card TODO:
     *
     * @return TODO:
     */
    public boolean add(Card card)
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
     * TODO:
     *
     * @return TODO:
     */
    public boolean remove()
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
     * TODO:
     *
     * @param cards TODO:
     */
    public void set(ArrayList cards)
    {
        this.foundation.clear();
        Iterator iter = cards.iterator();
        this.set((Card) iter.next());
        for (; iter.hasNext();)
        {
            this.add((Card) iter.next());
        }
    }

    /**
     * TODO:
     *
     * @param card TODO:
     */
    public void set(Card card)
    {
        this.foundation.clear();
        this.foundation.add(card);
    }
}
