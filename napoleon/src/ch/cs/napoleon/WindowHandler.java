package ch.cs.napoleon;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public class WindowHandler extends WindowAdapter
{
    //~ Instance fields --------------------------------------------------------

    /** @TODO: javadoc! */
    private Napoleon napoleon;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new WindowHandler object.
     *
     * @param napo javadoc!
     */
    public WindowHandler(final Napoleon napo)
    {
        this.napoleon = napo;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * @TODO: javadoc!
     *
     * @param event javadoc!
     */
    public final void windowActivated(final WindowEvent event)
    {
        this.napoleon.resume();
    }

    /**
     * @TODO: javadoc!
     *
     * @param event javadoc!
     */
    public final void windowClosing(final WindowEvent event)
    {
        this.napoleon.save();
    }

    /**
     * @TODO: javadoc!
     *
     * @param event javadoc!
     */
    public final void windowDeactivated(final WindowEvent event)
    {
        this.napoleon.suspend();
    }

    /**
     * @TODO: javadoc!
     *
     * @param event javadoc!
     */
    public final void windowDeiconified(final WindowEvent event)
    {
        this.napoleon.resume();
    }

    /**
     * @TODO: javadoc!
     *
     * @param event javadoc!
     */
    public final void windowGainedFocus(final WindowEvent event)
    {
        this.napoleon.resume();
    }

    /**
     * @TODO: javadoc!
     *
     * @param event javadoc!
     */
    public final void windowIconified(final WindowEvent event)
    {
        this.napoleon.suspend();
    }

    /**
     * @TODO: javadoc!
     *
     * @param event javadoc!
     */
    public final void windowLostFocus(final WindowEvent event)
    {
        this.napoleon.suspend();
    }
}
