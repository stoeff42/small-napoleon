package ch.cs.napoleon.solitaire.undo;

import waba.io.DataStream;

import waba.util.Vector;


/**
 * TODO: javadoc
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class UndoManager
{
    //~ Instance variables -------------------------------------------

    /** TODO: javadoc */
    protected Vector edits;

    /** TODO: javadoc */
    int indexOfNextAdd;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new UndoManager object.
     */
    public UndoManager()
    {
        indexOfNextAdd = 0;
        edits = new Vector(10);
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO: javadoc
     *
     * @param anEdit TODO: javadoc
     */
    public synchronized void addEdit(UndoMove anEdit)
    {
        int to = edits.size() - 1;
        if (indexOfNextAdd <= to)
        {
            for (int i = to; indexOfNextAdd <= i; i--)
            {
                edits.removeElementAt(i);
            }

            if (indexOfNextAdd > to)
            {
                indexOfNextAdd -= (to - indexOfNextAdd + 1);
            }
        }
        edits.addElement(anEdit);
        indexOfNextAdd = edits.size();
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public boolean canRedo()
    {
        if ((indexOfNextAdd > 0) && (indexOfNextAdd < edits.size()))
        {
            UndoMove edit =
                (UndoMove) edits.elementAt(indexOfNextAdd);
            return edit.canRedo();
        }
        else
        {
            return false;
        }
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public synchronized boolean canUndo()
    {
        if ((indexOfNextAdd > 0) && (indexOfNextAdd < edits.size()))
        {
            UndoMove edit =
                (UndoMove) edits.elementAt(indexOfNextAdd);
            return edit.canUndo();
        }
        else
        {
            return false;
        }
    }

    /**
     * TODO: javadoc
     */
    public synchronized void discardAllEdits()
    {
        edits.removeAllElements();
        indexOfNextAdd = 0;
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void load(DataStream stream)
    {
        int size = stream.readInt();
        this.edits = new Vector(size);
        for (int i = 0; i < size; i++)
        {
            ((UndoMove) this.edits.get(i)).load(stream);
        }
        indexOfNextAdd = stream.readInt();
    }

    /**
     * TODO: javadoc
     *
     * @throws RuntimeException TODO: javadoc
     */
    public void redo()
    {
        UndoMove edit = (UndoMove) edits.elementAt(indexOfNextAdd);
        if (edit == null)
        {
            throw new RuntimeException();
        }
        boolean done = false;
        while (!done)
        {
            UndoMove next =
                (UndoMove) edits.elementAt(indexOfNextAdd++);
            next.redo();
            done = next == edit;
        }
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void save(DataStream stream)
    {
        int size = this.edits.size();
        stream.writeInt(size);
        for (int i = 0; i < size; i++)
        {
            ((UndoMove) this.edits.get(i)).save(stream);
        }
        stream.writeInt(indexOfNextAdd);
    }

    /**
     * TODO: javadoc
     *
     * @throws RuntimeException TODO: javadoc
     */
    public void undo()
    {
        UndoMove edit = (UndoMove) edits.elementAt(indexOfNextAdd);
        if (edit == null)
        {
            throw new RuntimeException();
        }
        boolean done = false;
        while (!done)
        {
            UndoMove next =
                (UndoMove) edits.elementAt(indexOfNextAdd++);
            next.redo();
            done = next == edit;
        }
    }
}
