package ch.cs.napoleon.score;

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
public class ScoreEntries
{
    //~ Static variables/initializers --------------------------------

    /** TODO: javadoc */
    private static final int MAX_ENTRIES = 5;

    //~ Instance variables -------------------------------------------

    /** TODO: javadoc */
    private Vector entries;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new ScoreEntries object.
     */
    public ScoreEntries()
    {
        this.entries = new Vector(MAX_ENTRIES + 1);
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO: javadoc
     *
     * @param i TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public ScoreEntry getEntry(int i)
    {
        return (ScoreEntry) this.entries.get(i);
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public int getNrOfEntries()
    {
        return this.entries.size();
    }

    /**
     * TODO: javadoc
     *
     * @param name TODO: javadoc
     * @param nrOfMoves TODO: javadoc
     * @param time TODO: javadoc
     */
    public void addEntry(String name, int nrOfMoves, int time)
    {
        this.addEntry(new ScoreEntry(name, nrOfMoves, time));
    }

    /**
     * TODO: javadoc
     */
    public void clear()
    {
        this.entries.clear();
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void load(DataStream stream)
    {
        int size = stream.readByte();
        this.entries = new Vector(size);
        for (int i = 0; i < size; i++)
        {
            ScoreEntry entry = new ScoreEntry();
            entry.load(stream);
            this.addEntry(entry);
        }
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void save(DataStream stream)
    {
        int size = this.entries.size();
        stream.writeByte(size);
        for (int i = 0; i < size; i++)
        {
            ((ScoreEntry) this.entries.get(i)).save(stream);
        }
    }

    /**
     * TODO: javadoc
     *
     * @param newEntry TODO: javadoc
     */
    private void addEntry(ScoreEntry newEntry)
    {
        boolean inserted = false;
        for (int i = 0; (i < this.entries.size()) && !inserted;
                i++)
        {
            if (((ScoreEntry) this.entries.elementAt(i)).compareTo(
                        newEntry) > 0)
            {
                this.entries.insertElementAt(newEntry, i);
                inserted = true;
            }
        }
        if (!inserted)
        {
            this.entries.addElement(newEntry);
        }
        while (this.entries.size() > MAX_ENTRIES)
        {
            this.entries.removeElementAt(this.entries.size() - 1);
        }
    }
}
