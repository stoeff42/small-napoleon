package ch.cs.time;

/**
 * TODO:
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class UsedTimeHandler
{
    //~ Static variables/initializers --------------------------------

    /** TODO: */
    private static int timeUsed;

    /** TODO: */
    private static long startTime;

    /** TODO: */
    private static boolean suspended = false;

    //~ Methods ------------------------------------------------------

    /**
     * TODO:
     *
     * @param tU TODO:
     */
    public static void setTimeUsed(int tU)
    {
        timeUsed = tU;
        startTime = System.currentTimeMillis();
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public static int getTimeUsed()
    {
        return suspended ? timeUsed
                         : (int) ((timeUsed
        + System.currentTimeMillis()) - startTime);
    }

    /**
     * TODO:
     */
    public static void resume()
    {
        if (suspended)
        {
            suspended = false;
            startTime = System.currentTimeMillis();
        }
    }

    /**
     * TODO:
     */
    public static void suspend()
    {
        if (!suspended)
        {
            updateTimeUsed();
            suspended = true;
        }
    }

    /**
     * TODO:
     */
    public static void updateTimeUsed()
    {
        setTimeUsed((int) ((timeUsed + System.currentTimeMillis())
            - startTime));
    }
}
