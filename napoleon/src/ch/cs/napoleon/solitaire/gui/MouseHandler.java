package ch.cs.napoleon.solitaire.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public class MouseHandler extends MouseAdapter
{
    //~ Instance fields --------------------------------------------------------

    /** @TODO: javadoc! */
    private NapoleonTableauPanel napoleonTableauPanel;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new MouseHandler object.
     *
     * @param napoTabPanel javadoc!
     */
    public MouseHandler(final NapoleonTableauPanel napoTabPanel)
    {
        this.napoleonTableauPanel = napoTabPanel;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * @TODO: javadoc!
     *
     * @param event javadoc!
     */
    public final void mouseClicked(final MouseEvent event)
    {
        this.napoleonTableauPanel.processSelection(this.napoleonTableauPanel
            .getGraphics(), event.getX(), event.getY(), event.getClickCount());
    }
}
