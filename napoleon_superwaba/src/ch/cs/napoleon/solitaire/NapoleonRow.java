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
public class NapoleonRow
{
    //~ Instance variables -------------------------------------------

    /** TODO: javadoc */
    private Vector base;

    /** TODO: javadoc */
    private Vector end;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new NapoleonRow object.
     */
    public NapoleonRow()
    {
        this.base = new Vector(5);
        this.end = new Vector(13);
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public Vector getBase()
    {
        return this.base;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public Vector getEnd()
    {
        return this.end;
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
            return (Card) this.end.get(this.end.size() - 1);
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
        return this.base.size() + this.end.size();
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
     * TODO: javadoc
     *
     * @param card TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public boolean forceAdd(Card card)
    {
        Card last = this.getLast();
        if (last != null)
        {
            if (this.end.size() == 1)
            {
                for (int i = 0; i < this.end.size(); i++)
                {
                    this.base.add(this.end.get(i));
                }
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
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void load(DataStream stream)
    {
        this.base.clear();
        int size = stream.readByte();
        for (int i = 0; i < size; i++)
        {
            Card card = new Card();
            card.load(stream);
            this.base.add(card);
        }
        this.end.clear();
        size = stream.readByte();
        for (int i = 0; i < size; i++)
        {
            Card card = new Card();
            card.load(stream);
            this.end.add(card);
        }
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public boolean remove()
    {
        if (this.end.size() > 0)
        {
            this.end.removeElementAt(this.end.size() - 1);
            if ((this.end.size() == 0) && (this.base.size() > 0))
            {
                Card card =
                    (Card) this.base.elementAt(this.base.size() - 1);
                this.base.removeElementAt(this.base.size() - 1);
                this.end.add(card);
            }
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
        int size = this.base.size();
        stream.writeByte(size);
        for (int i = 0; i < size; i++)
        {
            ((Card) this.base.get(i)).save(stream);
        }
        size = this.end.size();
        stream.writeByte(size);
        for (int i = 0; i < size; i++)
        {
            ((Card) this.end.get(i)).save(stream);
        }
    }

    /**
     * TODO: javadoc
     *
     * @param cards TODO: javadoc
     */
    public void set(Vector cards)
    {
        this.base.clear();
        this.end.clear();
        for (int i = 0; i < (cards.size() - 1); i++)
        {
            this.base.add(cards.get(i));
        }
        this.end.add(cards.get(cards.size() - 1));
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
            return (Card) this.end.get(this.end.size() - 2);
        }
        catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }
}
