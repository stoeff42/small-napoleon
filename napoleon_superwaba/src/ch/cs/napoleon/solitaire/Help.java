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
public class Help
{
    //~ Instance variables -------------------------------------------

    /** TODO: javadoc */
    private Vector help;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new Help object.
     */
    public Help()
    {
        this.help = new Vector(4);
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public Vector getHelp()
    {
        return this.help;
    }

    /**
     * TODO: javadoc
     *
     * @param i TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public Card get(int i)
    {
        return (Card) this.help.get(i);
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void load(DataStream stream)
    {
        Vector cards = new Vector(4);
        for (int i = 0; i < 4; i++)
        {
            if(stream.readByte() != 0)
            {
                Card card = new Card();
                card.load(stream);
                cards.add(card);                
            }
            else
            {
                cards.add(null);
            }
        }
        this.set(cards);
    }

    /**
     * TODO: javadoc
     *
     * @param i TODO: javadoc
     */
    public void remove(int i)
    {
        this.help.set(i, null);
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void save(DataStream stream)
    {
        for (int i = 0; i < 4; i++)
        {
            Card card = (Card) this.help.get(i);
            if (card != null)
            {
                stream.writeByte(1);
                card.save(stream);
            }
            else
            {
                stream.writeByte(0);
            }
        }
    }

    /**
     * TODO: javadoc
     *
     * @param cards TODO: javadoc
     */
    public void set(Vector cards)
    {
        if (cards.size() <= 4)
        {
            this.help.clear();
            for (int i = 0; i < cards.size(); i++)
            {
                this.help.add(cards.get(i));
            }
        }
    }

}
