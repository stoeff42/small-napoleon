package ch.cs.napoleon;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * TODO:
 *
 * @author Christoph
 * @version $Revision$
 */
public class WindowHandler extends WindowAdapter
{
    //~ Instance variables -------------------------------------------

    /** TODO: */
    private Napoleon napoleon;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new MouseHandler object.
     *
     * @param napoleon TODO:
     */
    public WindowHandler(Napoleon napoleon)
    {
        this.napoleon = napoleon;
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public void windowActivated(WindowEvent event)
    {
        this.napoleon.resume();
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public void windowClosing(WindowEvent event)
    {
        this.napoleon.save();
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public void windowDeactivated(WindowEvent event)
    {
        this.napoleon.suspend();
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public void windowDeiconified(WindowEvent event)
    {
        this.napoleon.resume();
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public void windowGainedFocus(WindowEvent event)
    {
        this.napoleon.resume();
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public void windowIconified(WindowEvent event)
    {
        this.napoleon.suspend();
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public void windowLostFocus(WindowEvent event)
    {
        this.napoleon.suspend();
    }
}
