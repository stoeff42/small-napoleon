package ch.cs.napoleon.score;

import ch.cs.gui.MessageProperties;

import ch.cs.napoleon.gui.NapoleonMessage;

import java.io.Serializable;

import java.util.Date;

import javax.swing.SwingConstants;


/**
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public class ScoreEntry implements Comparable, Serializable
{
    //~ Instance fields --------------------------------------------------------

    /** @TODO: javadoc! */
    private Date finishedOn;

    /** @TODO: javadoc! */
    private ScoreTable scoreTable;

    /** @TODO: javadoc! */
    private String name;

    /** @TODO: javadoc! */
    private int nrOfMoves;

    /** @TODO: javadoc! */
    private int timeUsed;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new ScoreEntry object.
     *
     * @param scorTab javadoc!
     * @param nm javadoc!
     * @param nOMovs javadoc!
     * @param tU javadoc!
     * @param finOn javadoc!
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

    //~ Methods ----------------------------------------------------------------

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
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
     * @TODO: javadoc!
     *
     * @param finOn javadoc!
     */
    public final void setFinishedOn(final Date finOn)
    {
        this.finishedOn = finOn;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final Date getFinishedOn()
    {
        return this.finishedOn;
    }

    /**
     * @TODO: javadoc!
     *
     * @param nm javadoc!
     */
    public final void setName(final String nm)
    {
        this.name = nm;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final String getName()
    {
        return this.name;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
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
     * @TODO: javadoc!
     *
     * @param nOMovs javadoc!
     */
    public final void setNrOfMoves(final int nOMovs)
    {
        this.nrOfMoves = nOMovs;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final int getNrOfMoves()
    {
        return this.nrOfMoves;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final int getRank()
    {
        return this.scoreTable.getRank(this);
    }

    /**
     * @TODO: javadoc!
     *
     * @param scorTab javadoc!
     */
    public final void setScoreTable(final ScoreTable scorTab)
    {
        this.scoreTable = scorTab;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final ScoreTable getScoreTable()
    {
        return this.scoreTable;
    }

    /**
     * @TODO: javadoc!
     *
     * @param tU javadoc!
     */
    public final void setTimeUsed(final int tU)
    {
        this.timeUsed = tU;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final int getTimeUsed()
    {
        return this.timeUsed;
    }

    /**
     * @TODO: javadoc!
     *
     * @param obj javadoc!
     *
     * @return javadoc!
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
     * @TODO: javadoc!
     *
     * @return javadoc!
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
     * @TODO: javadoc!
     *
     * @param property javadoc!
     *
     * @return javadoc!
     */
    private static String getString(final String property)
    {
        return MessageProperties.getString("scoreentry", //$NON-NLS-1$
            property);
    }
}
