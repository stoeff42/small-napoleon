package ch.cs.napoleon.solitaire.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * TODO:
 *
 * @author Christoph
 * @version $Revision$
 */
public class MouseHandler extends MouseAdapter
{
    //~ Instance fields --------------------------------------------------------

    /** TODO: */
    private NapoleonTableauPanel napoleonTableauPanel;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new MouseHandler object.
     *
     * @param napoTabPanel TODO:
     */
    public MouseHandler(final NapoleonTableauPanel napoTabPanel)
    {
        this.napoleonTableauPanel = napoTabPanel;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public final void mouseClicked(final MouseEvent event)
    {
        this.napoleonTableauPanel.processSelection(this.napoleonTableauPanel
            .getGraphics(), event.getX(), event.getY(), event.getClickCount());
    }
}
