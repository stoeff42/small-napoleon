package ch.cs.napoleon.score;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


/**
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public class ScoreTable implements Serializable
{
    //~ Static fields/initializers ---------------------------------------------

    /** @TODO: javadoc! */
    private static final int MAX_ENTRIES = 10;

    //~ Instance fields --------------------------------------------------------

    /** @TODO: javadoc! */
    private ArrayList entries;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new ScoreTable object.
     */
    public ScoreTable()
    {
        this.entries = new ArrayList(MAX_ENTRIES + 1);
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * Sets the entries.
     *
     * @param ntries The entries to set
     */
    public final void setEntries(final ArrayList ntries)
    {
        this.entries = ntries;
    }

    /**
     * Returns the entries.
     *
     * @return ArrayList
     */
    public final ArrayList getEntries()
    {
        return this.entries;
    }

    /**
     * @TODO: javadoc!
     *
     * @param i javadoc!
     *
     * @return javadoc!
     */
    public final ScoreEntry getEntry(final int i)
    {
        return (ScoreEntry) this.entries.get(i);
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final int getNrOfEntries()
    {
        return this.entries.size();
    }

    /**
     * @TODO: javadoc!
     *
     * @param scoreEntry javadoc!
     *
     * @return javadoc!
     */
    public final int getRank(final ScoreEntry scoreEntry)
    {
        return this.entries.indexOf(scoreEntry) + 1;
    }

    /**
     * @TODO: javadoc!
     *
     * @param name javadoc!
     * @param nrOfMoves javadoc!
     * @param timeUsed javadoc!
     * @param finishedOn javadoc!
     */
    public final void addEntry(final String name, final int nrOfMoves,
        final int timeUsed, final Date finishedOn)
    {
        this.entries.add(new ScoreEntry(this, name, nrOfMoves, timeUsed,
                finishedOn));
        Collections.sort(this.entries);

        while (this.entries.size() > MAX_ENTRIES)
        {
            this.entries.remove(this.entries.size() - 1);
        }
    }

    /**
     * @TODO: javadoc!
     */
    public final void clear()
    {
        this.entries.clear();
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final Object[][] toArray()
    {
        Object[][] array = new Object[this.entries.size()][];

        for (int i = 0; i < array.length; i++)
        {
            array[i] = this.getEntry(i).toArray();
        }

        return array;
    }
}
