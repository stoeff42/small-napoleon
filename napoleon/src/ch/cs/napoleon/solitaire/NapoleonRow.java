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
public class NapoleonRow implements Serializable
{
    //~ Instance variables -------------------------------------------

    /** TODO: */
    private ArrayList base;

    /** TODO: */
    private ArrayList end;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new NapoleonRow object.
     */
    public NapoleonRow()
    {
        this.base = new ArrayList(5);
        this.end = new ArrayList(13);
    }

    /**
     * Creates a new NapoleonRow object.
     *
     * @param cards TODO:
     */
    public NapoleonRow(ArrayList cards)
    {
        this();
        this.set(cards);
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO:
     *
     * @param base TODO:
     */
    public void setBase(ArrayList base)
    {
        this.base = base;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public ArrayList getBase()
    {
        return this.base;
    }

    /**
     * TODO:
     *
     * @param end TODO:
     */
    public void setEnd(ArrayList end)
    {
        this.end = end;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public ArrayList getEnd()
    {
        return this.end;
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
            return (Card) this.end.get(this.end.size() - 1);
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
            return (Card) this.end.get(this.end.size() - 2);
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
        return this.base.size() + this.end.size();
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
     * TODO:
     *
     * @param card TODO:
     *
     * @return TODO:
     */
    public boolean forceAdd(Card card)
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
     * TODO:
     *
     * @return TODO:
     */
    public boolean remove()
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
     * TODO:
     *
     * @param cards TODO:
     */
    public void set(ArrayList cards)
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
