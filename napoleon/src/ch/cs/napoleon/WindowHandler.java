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
    //~ Instance fields --------------------------------------------------------

    /** TODO: */
    private Napoleon napoleon;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new MouseHandler object.
     *
     * @param napo TODO:
     */
    public WindowHandler(final Napoleon napo)
    {
        this.napoleon = napo;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public final void windowActivated(final WindowEvent event)
    {
        this.napoleon.resume();
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public final void windowClosing(final WindowEvent event)
    {
        this.napoleon.save();
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public final void windowDeactivated(final WindowEvent event)
    {
        this.napoleon.suspend();
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public final void windowDeiconified(final WindowEvent event)
    {
        this.napoleon.resume();
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public final void windowGainedFocus(final WindowEvent event)
    {
        this.napoleon.resume();
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public final void windowIconified(final WindowEvent event)
    {
        this.napoleon.suspend();
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public final void windowLostFocus(final WindowEvent event)
    {
        this.napoleon.suspend();
    }
}
