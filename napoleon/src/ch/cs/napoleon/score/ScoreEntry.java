package ch.cs.napoleon.score;

import ch.cs.gui.MessageProperties;

import ch.cs.napoleon.gui.NapoleonMessage;

import java.io.Serializable;

import java.util.Date;

import javax.swing.SwingConstants;


/**
 * TODO:
 *
 * @author <a href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class ScoreEntry implements Comparable, Serializable
{
    //~ Instance fields --------------------------------------------------------

    /** TODO: */
    private Date finishedOn;

    /** TODO: */
    private ScoreTable scoreTable;

    /** TODO: */
    private String name;

    /** TODO: */
    private int nrOfMoves;

    /** TODO: */
    private int timeUsed;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new ScoreEntry object.
     *
     * @param scorTab TODO:
     * @param nm TODO:
     * @param nOMovs TODO:
     * @param tU TODO:
     * @param finOn TODO:
     */
    public ScoreEntry(final ScoreTable scorTab, final String nm,
        final int nOMovs, final int tU, final Date finOn)
    {
        this.name = nm;
        this.nrOfMoves = nOMovs;
        this.timeUsed = tU;
        this.finishedOn = finOn;
        this.scoreTable = scorTab;
    }

    /**
     * Creates a new ScoreEntry object.
     */
    private ScoreEntry()
    {
        /**
         * Make constructor private in order to implement singleton correctly
         */
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * TODO:
     *
     * @return TODO:
     */
    public static int[] getAlignments()
    {
        return new int[]
        {
            SwingConstants.RIGHT, SwingConstants.LEFT, SwingConstants.RIGHT,
            SwingConstants.RIGHT, SwingConstants.LEFT
        };
    }

    /**
     * TODO:
     *
     * @param finOn TODO:
     */
    public final void setFinishedOn(final Date finOn)
    {
        this.finishedOn = finOn;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final Date getFinishedOn()
    {
        return this.finishedOn;
    }

    /**
     * TODO:
     *
     * @param nm TODO:
     */
    public final void setName(final String nm)
    {
        this.name = nm;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final String getName()
    {
        return this.name;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public static Object[] getNames()
    {
        return new Object[]
        {
            getString("ranktext"), //$NON-NLS-1$
            getString("nametext"), //$NON-NLS-1$
            getString("nrofmovestext"), //$NON-NLS-1$
            getString("usedtimetext"), //$NON-NLS-1$
            getString("datetext") //$NON-NLS-1$
        };
    }

    /**
     * TODO:
     *
     * @param nOMovs TODO:
     */
    public final void setNrOfMoves(final int nOMovs)
    {
        this.nrOfMoves = nOMovs;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final int getNrOfMoves()
    {
        return this.nrOfMoves;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final int getRank()
    {
        return this.scoreTable.getRank(this);
    }

    /**
     * TODO:
     *
     * @param scorTab TODO:
     */
    public final void setScoreTable(final ScoreTable scorTab)
    {
        this.scoreTable = scorTab;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final ScoreTable getScoreTable()
    {
        return this.scoreTable;
    }

    /**
     * TODO:
     *
     * @param tU TODO:
     */
    public final void setTimeUsed(final int tU)
    {
        this.timeUsed = tU;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final int getTimeUsed()
    {
        return this.timeUsed;
    }

    /**
     * TODO:
     *
     * @param obj TODO:
     *
     * @return TODO:
     */
    public final int compareTo(final Object obj)
    {
        if (!(obj instanceof ScoreEntry))
        {
            return 1;
        }

        ScoreEntry other = (ScoreEntry) obj;

        if (this.getNrOfMoves() < other.getNrOfMoves())
        {
            return -1;
        }

        if (this.getNrOfMoves() > other.getNrOfMoves())
        {
            return 1;
        }

        if (this.getTimeUsed() < other.getTimeUsed())
        {
            return -1;
        }

        if (this.getTimeUsed() > other.getTimeUsed())
        {
            return 1;
        }

        if (this.getFinishedOn().getTime() > other.getFinishedOn().getTime())
        {
            return -1;
        }

        if (this.getFinishedOn().getTime() < other.getFinishedOn().getTime())
        {
            return 1;
        }

        return this.getName().compareTo(other.getName());
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final Object[] toArray()
    {
        return new Object[]
        {
            new StringBuffer().append(this.getRank()).append('.').toString(),
            this.getName(), new Integer(this.getNrOfMoves()),
            NapoleonMessage.makeDurationString(this.getTimeUsed(), 2),
            this.getFinishedOn()
        };
    }

    /**
     * TODO:
     *
     * @param property TODO:
     *
     * @return TODO:
     */
    private static String getString(final String property)
    {
        return MessageProperties.getString("scoreentry", //$NON-NLS-1$
            property);
    }
}
