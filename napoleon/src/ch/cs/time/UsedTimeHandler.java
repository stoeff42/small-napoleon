package ch.cs.time;

/**
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public final class UsedTimeHandler
{
    //~ Static fields/initializers ---------------------------------------------

    /** @TODO: javadoc! */
    private static int timeUsed;

    /** @TODO: javadoc! */
    private static long startTime;

    /** @TODO: javadoc! */
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
     * @TODO: javadoc!
     *
     * @param tU javadoc!
     */
    public static void setTimeUsed(final int tU)
    {
        timeUsed = tU;
        startTime = System.currentTimeMillis();
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
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
     * @TODO: javadoc!
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
     * @TODO: javadoc!
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
     * @TODO: javadoc!
     */
    public static void updateTimeUsed()
    {
        setTimeUsed((int) (
                (timeUsed + System.currentTimeMillis()) - startTime
            ));
    }
}
