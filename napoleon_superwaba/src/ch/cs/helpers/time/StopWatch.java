package ch.cs.helpers.time;

import waba.sys.Vm;


/**
 * TODO: javadoc
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class StopWatch {
    /** TODO:  javadoc */
    private static StopWatch stopWatch;

    /** TODO: javadoc */
    private boolean stopped = false;

    /** TODO: javadoc */
    private int start;

    /** TODO: javadoc */
    private int time;

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public static StopWatch getStopWatch() {
        if (stopWatch == null) {
            stopWatch = new StopWatch();
        }

        return stopWatch;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public int get() {
        return stopped ? time : ((time + Vm.getTimeStamp()) - start);
    }

    /**
     * TODO: javadoc
     */
    public void reset() {
        this.set(0);
    }

    /**
     * TODO: javadoc
     *
     * @param tU TODO: javadoc
     */
    public void set(int tU) {
        time = tU;
        start = Vm.getTimeStamp();
    }

    /**
     * TODO: javadoc
     */
    public void start() {
        if (stopped) {
            stopped = false;
            start = Vm.getTimeStamp();
        }
    }

    /**
     * TODO: javadoc
     */
    public void stop() {
        if (!stopped) {
            update();
            stopped = true;
        }
    }

    /**
     * TODO: javadoc
     */
    public void update() {
        set((time + Vm.getTimeStamp()) - start);
    }
}
