package ch.cs.napoleon.solitaire.gui;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;


/**
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public class DnDHandler implements DragSourceListener,
    DropTargetListener,
    DragGestureListener
{
    //~ Instance fields --------------------------------------------------------

    /** @TODO: javadoc! */
    private DragSource dragSource;

    /** @TODO: javadoc! */
    private DropTarget dropTarget;

    /** @TODO: javadoc! */
    private NapoleonTableauPanel napoleonTableauPanel;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new DnDHandler object.
     *
     * @param napoTabPanel javadoc!
     */
    public DnDHandler(final NapoleonTableauPanel napoTabPanel)
    {
        this.napoleonTableauPanel = napoTabPanel;
        this.dropTarget = new DropTarget(this.napoleonTableauPanel, this);
        this.dragSource = new DragSource();
        this.dragSource.createDefaultDragGestureRecognizer(this.napoleonTableauPanel,
            DnDConstants.ACTION_MOVE, this);
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * @TODO: javadoc!
     *
     * @param event javadoc!
     */
    public void dragDropEnd(final DragSourceDropEvent event)
    {
        /**
         * Not implemented
         */
    }

    /**
     * @TODO: javadoc!
     *
     * @param event javadoc!
     */
    public final void dragEnter(final DropTargetDragEvent event)
    {
        event.acceptDrag(DnDConstants.ACTION_MOVE);
    }

    /**
     * @TODO: javadoc!
     *
     * @param event javadoc!
     */
    public void dragEnter(final DragSourceDragEvent event)
    {
        /**
         * Not implemented
         */
    }

    /**
     * @TODO: javadoc!
     *
     * @param event javadoc!
     */
    public void dragExit(final DropTargetEvent event)
    {
        /**
         * Not implemented
         */
    }

    /**
     * @TODO: javadoc!
     *
     * @param event javadoc!
     */
    public void dragExit(final DragSourceEvent event)
    {
        /**
         * Not implemented
         */
    }

    /**
     * @TODO: javadoc!
     *
     * @param event javadoc!
     */
    public final void dragGestureRecognized(final DragGestureEvent event)
    {
        Point location = event.getDragOrigin();
        this.napoleonTableauPanel.processSelection(this.napoleonTableauPanel
            .getGraphics(), (int) location.getX(), (int) location.getY(), 1);

        if (this.napoleonTableauPanel.getSelectedCard() != null)
        {
            this.dragSource.startDrag(event, DragSource.DefaultMoveDrop,
                this.napoleonTableauPanel.getCardSuit(
                    this.napoleonTableauPanel.getSelectedCard().getCard()),
                this.napoleonTableauPanel.calculateSlotOffset(location),
                new Transferable()
                {
                    public final Object getTransferData(final DataFlavor flavor)
                    {
                        return ""; //$NON-NLS-1$
                    }

                    public final DataFlavor[] getTransferDataFlavors()
                    {
                        return new DataFlavor[] { DataFlavor.stringFlavor };
                    }

                    public final boolean isDataFlavorSupported(
                        final DataFlavor flavor)
                    {
                        return true;
                    }
                }, this);
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @param event javadoc!
     */
    public void dragOver(final DropTargetDragEvent event)
    {
        /**
         * Not implemented
         */
    }

    /**
     * @TODO: javadoc!
     *
     * @param event javadoc!
     */
    public void dragOver(final DragSourceDragEvent event)
    {
        /**
         * Not implemented
         */
    }

    /**
     * @TODO: javadoc!
     *
     * @param event javadoc!
     */
    public final void drop(final DropTargetDropEvent event)
    {
        Point location = event.getLocation();
        this.napoleonTableauPanel.processSelection(this.napoleonTableauPanel
            .getGraphics(), (int) location.getX(), (int) location.getY(), 1);

        if (this.napoleonTableauPanel.getSelectedCard() == null)
        {
            event.acceptDrop(DnDConstants.ACTION_MOVE);
            event.getDropTargetContext().dropComplete(true);
        }
        else
        {
            event.rejectDrop();
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @param event javadoc!
     */
    public void dropActionChanged(final DragSourceDragEvent event)
    {
        /**
         * Not implemented
         */
    }

    /**
     * @TODO: javadoc!
     *
     * @param event javadoc!
     */
    public void dropActionChanged(final DropTargetDragEvent event)
    {
        /**
         * Not implemented
         */
    }
}
