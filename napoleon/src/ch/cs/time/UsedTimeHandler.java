package ch.cs.time;

/**
 * TODO:
 *
 * @author <a href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public final class UsedTimeHandler
{
    //~ Static fields/initializers ---------------------------------------------

    /** TODO: */
    private static int timeUsed;

    /** TODO: */
    private static long startTime;

    /** TODO: */
    private static boolean suspended = false;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new UsedTimeHandler object.
     */
    private UsedTimeHandler()
    {
        /**
         * Make constructor private in order to implement singleton correctly
         */
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * TODO:
     *
     * @param tU TODO:
     */
    public static void setTimeUsed(final int tU)
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
        int tU = timeUsed;

        if (!suspended)
        {
            tU = (int) ((timeUsed + System.currentTimeMillis()) - startTime);
        }

        return tU;
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
        setTimeUsed((int) (
                (timeUsed + System.currentTimeMillis()) - startTime
            ));
    }
}
