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
 * TODO:
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class DnDHandler implements DragSourceListener,
    DropTargetListener,
    DragGestureListener
{
    //~ Instance variables -------------------------------------------

    /** TODO: */
    private DragSource dragSource;

    /** TODO: */
    private DropTarget dropTarget;

    /** TODO: */
    private NapoleonTableauPanel napoleonTableauPanel;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new DnDHandler object.
     *
     * @param napoleonTableauPanel TODO:
     */
    public DnDHandler(NapoleonTableauPanel napoleonTableauPanel)
    {
        this.napoleonTableauPanel = napoleonTableauPanel;
        this.dropTarget =
            new DropTarget(this.napoleonTableauPanel, this);
        this.dragSource = new DragSource();
        this.dragSource.createDefaultDragGestureRecognizer(this.napoleonTableauPanel,
            DnDConstants.ACTION_MOVE, this);
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public void dragDropEnd(DragSourceDropEvent event)
    {
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public void dragEnter(DropTargetDragEvent event)
    {
        event.acceptDrag(DnDConstants.ACTION_MOVE);
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public void dragEnter(DragSourceDragEvent event)
    {
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public void dragExit(DropTargetEvent event)
    {
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public void dragExit(DragSourceEvent event)
    {
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public void dragGestureRecognized(DragGestureEvent event)
    {
        Point location = event.getDragOrigin();
        this.napoleonTableauPanel.processSelection(
            this.napoleonTableauPanel.getGraphics(),
            (int) location.getX(),
            (int) location.getY(),
            1);

        if (this.napoleonTableauPanel.getSelectedCard() != null)
        {
            dragSource.startDrag(
                event,
                DragSource.DefaultMoveDrop,
                this.napoleonTableauPanel.getCardSuit(
                    this.napoleonTableauPanel.getSelectedCard()
                                                 .getCard()),
                this.napoleonTableauPanel.calculateSlotOffset(
                    location),
                new Transferable()
                {
                    public Object getTransferData(DataFlavor flavor)
                    {
                        return ""; //$NON-NLS-1$
                    }

                    public DataFlavor[] getTransferDataFlavors()
                    {
                        return new DataFlavor[]
                        {
                            DataFlavor.stringFlavor
                        };
                    }

                    public boolean isDataFlavorSupported(
                        DataFlavor flavor)
                    {
                        return true;
                    }
                },
                this);
        }
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public void dragOver(DropTargetDragEvent event)
    {
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public void dragOver(DragSourceDragEvent event)
    {
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public void drop(DropTargetDropEvent event)
    {
        Point location = event.getLocation();
        this.napoleonTableauPanel.processSelection(
            this.napoleonTableauPanel.getGraphics(),
            (int) location.getX(),
            (int) location.getY(),
            1);
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
     * TODO:
     *
     * @param event TODO:
     */
    public void dropActionChanged(DragSourceDragEvent event)
    {
    }

    /**
     * TODO:
     *
     * @param event TODO:
     */
    public void dropActionChanged(DropTargetDragEvent event)
    {
    }
}
