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
    //~ Instance variables -------------------------------------------

    /** TODO: */
    private NapoleonTableauPanel napoleonTableauPanel;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new MouseHandler object.
     *
     * @param napoleonTableauPanel TODO:
     */
    public MouseHandler(NapoleonTableauPanel napoleonTableauPanel)
    {
        this.napoleonTableauPanel = napoleonTableauPanel;
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public void mouseClicked(MouseEvent event)
    {
        this.napoleonTableauPanel.processSelection(
            this.napoleonTableauPanel.getGraphics(),
            event.getX(),
            event.getY(),
            event.getClickCount());
    }
}
