package ch.cs.napoleon.solitaire;

import ch.cs.solitaire.Card;

import waba.io.DataStream;

import waba.util.Vector;


/**
 * TODO: javadoc
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class Foundation
{
    //~ Instance variables -------------------------------------------

    /** TODO: javadoc */
    private Vector foundation;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new Foundation object.
     */
    public Foundation()
    {
        this.foundation = new Vector(13);
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public Card getFirst()
    {
        return (Card) this.foundation.get(0);
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public Vector getFoundation()
    {
        return foundation;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
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
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public int getSize()
    {
        return this.foundation.size();
    }

    /**
     * TODO: javadoc
     *
     * @param card TODO: javadoc
     *
     * @return TODO: javadoc
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
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void load(DataStream stream)
    {
        int size = stream.readByte();
        for (int i = 0; i < size; i++)
        {
            Card card = new Card();
            card.load(stream);
            this.foundation.add(card);
        }
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public boolean remove()
    {
        if (this.foundation.size() > 1)
        {
            this.foundation.removeElementAt(this.foundation.size()
                - 1);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void save(DataStream stream)
    {
        int size = this.foundation.size();
        stream.writeByte(size);
        for (int i = 0; i < size; i++)
        {
            ((Card) this.foundation.get(i)).save(stream);
        }
    }

    /**
     * TODO: javadoc
     *
     * @param card TODO: javadoc
     */
    public void set(Card card)
    {
        this.foundation.clear();
        this.foundation.add(card);
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    private Card getSecondLast()
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
}
