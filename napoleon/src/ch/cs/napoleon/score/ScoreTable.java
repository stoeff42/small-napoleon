package ch.cs.napoleon.score;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


/**
 * TODO:
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class ScoreTable implements Serializable
{
    //~ Static variables/initializers --------------------------------

    /** TODO: */
    private static final int MAX_ENTRIES = 10;

    //~ Instance variables -------------------------------------------

    /** TODO: */
    private ArrayList entries;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new ScoreTable object.
     */
    public ScoreTable()
    {
        this.entries = new ArrayList(MAX_ENTRIES + 1);
    }

    //~ Methods ------------------------------------------------------

    /**
     * Sets the entries.
     *
     * @param entries The entries to set
     */
    public void setEntries(ArrayList entries)
    {
        this.entries = entries;
    }

    /**
     * Returns the entries.
     *
     * @return ArrayList
     */
    public ArrayList getEntries()
    {
        return this.entries;
    }

    /**
     * TODO:
     *
     * @param i TODO:
     *
     * @return TODO:
     */
    public ScoreEntry getEntry(int i)
    {
        return (ScoreEntry) this.entries.get(i);
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public int getNrOfEntries()
    {
        return this.entries.size();
    }

    /**
     * TODO:
     *
     * @param scoreEntry TODO:
     *
     * @return TODO:
     */
    public int getRank(ScoreEntry scoreEntry)
    {
        return this.entries.indexOf(scoreEntry) + 1;
    }

    /**
     * TODO:
     *
     * @param name TODO:
     * @param nrOfMoves TODO:
     * @param timeUsed TODO:
     * @param finishedOn TODO:
     */
    public void addEntry(String name, int nrOfMoves, int timeUsed,
        Date finishedOn)
    {
        this.entries.add(
            new ScoreEntry(this, name, nrOfMoves, timeUsed, finishedOn));
        Collections.sort(this.entries);
        while (this.entries.size() > MAX_ENTRIES)
        {
            this.entries.remove(this.entries.size() - 1);
        }
    }

    /**
     * TODO:
     */
    public void clear()
    {
        this.entries.clear();
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public Object[][] toArray()
    {
        Object[][] array = new Object[this.entries.size()][];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = this.getEntry(i).toArray();
        }
        return array;
    }
}
