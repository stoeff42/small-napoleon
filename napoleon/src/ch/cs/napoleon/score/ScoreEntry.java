package ch.cs.napoleon.score;

import ch.cs.gui.MessageProperties;
import ch.cs.napoleon.gui.NapoleonMessage;

import java.io.Serializable;

import java.util.Date;

import javax.swing.SwingConstants;


/**
 * TODO:
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class ScoreEntry implements Comparable, Serializable
{
    //~ Instance variables -------------------------------------------

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

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new ScoreEntry object.
     */
    public ScoreEntry()
    {
    }

    /**
     * Creates a new ScoreEntry object.
     *
     * @param scoreTable TODO:
     * @param name TODO:
     * @param nrOfMoves TODO:
     * @param timeUsed TODO:
     * @param finishedOn TODO:
     */
    public ScoreEntry(ScoreTable scoreTable, String name,
        int nrOfMoves, int timeUsed, Date finishedOn)
    {
        this.name = name;
        this.nrOfMoves = nrOfMoves;
        this.timeUsed = timeUsed;
        this.finishedOn = finishedOn;
        this.scoreTable = scoreTable;
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO:
     *
     * @return TODO:
     */
    public static int[] getAlignments()
    {
        return new int[]
        {
            SwingConstants.RIGHT, SwingConstants.LEFT,
            SwingConstants.RIGHT, SwingConstants.RIGHT,
            SwingConstants.LEFT
        };
    }

    /**
     * TODO:
     *
     * @param finishedOn TODO:
     */
    public void setFinishedOn(Date finishedOn)
    {
        this.finishedOn = finishedOn;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public Date getFinishedOn()
    {
        return this.finishedOn;
    }

    /**
     * TODO:
     *
     * @param name TODO:
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public String getName()
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
     * @param nrOfMoves TODO:
     */
    public void setNrOfMoves(int nrOfMoves)
    {
        this.nrOfMoves = nrOfMoves;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public int getNrOfMoves()
    {
        return this.nrOfMoves;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public int getRank()
    {
        return this.scoreTable.getRank(this);
    }

    /**
     * TODO:
     *
     * @param scoreTable TODO:
     */
    public void setScoreTable(ScoreTable scoreTable)
    {
        this.scoreTable = scoreTable;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public ScoreTable getScoreTable()
    {
        return scoreTable;
    }

    /**
     * TODO:
     *
     * @param timeUsed TODO:
     */
    public void setTimeUsed(int timeUsed)
    {
        this.timeUsed = timeUsed;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public int getTimeUsed()
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
    public int compareTo(Object obj)
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
        if (this.getFinishedOn().getTime() > other.getFinishedOn()
                                                      .getTime())
        {
            return -1;
        }
        if (this.getFinishedOn().getTime() < other.getFinishedOn()
                                                      .getTime())
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
    public Object[] toArray()
    {
        return new Object[]
        {
            new StringBuffer().append(this.getRank()).append('.')
                                  .toString(), this.getName(),
            new Integer(this.getNrOfMoves()),
            NapoleonMessage.makeDurationString(
                this.getTimeUsed(),
                2), this.getFinishedOn()
        };
    }

    /**
     * TODO:
     *
     * @param property TODO:
     *
     * @return TODO:
     */
    private static String getString(String property)
    {
        return MessageProperties.getString("scoreentry", //$NON-NLS-1$
            property);
    }
}
