package ch.cs.napoleon.solitaire.undo;

import ch.cs.napoleon.solitaire.NapoleonTableau;
import ch.cs.napoleon.solitaire.gui.CardSelection;

import waba.io.DataStream;


/**
 * TODO: javadoc
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class UndoMove
{
    //~ Instance variables -------------------------------------------

    /** TODO: javadoc */
    private CardSelection dst;

    /** TODO: javadoc */
    private CardSelection src;

    /** TODO: javadoc */
    private transient NapoleonTableau napoleonTableau;

    /** TODO: javadoc */
    private boolean hasBeenDone;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new UndoMove object.
     */
    public UndoMove()
    {
    }

    /**
     * Creates a new UndoMove object.
     *
     * @param napoleonTableau TODO: javadoc
     * @param src TODO: javadoc
     * @param dst TODO: javadoc
     */
    public UndoMove(NapoleonTableau napoleonTableau,
        CardSelection src, CardSelection dst)
    {
        this.napoleonTableau = napoleonTableau;
        this.src = src;
        this.dst = dst;
        this.hasBeenDone = true;
    }

    //~ Methods ------------------------------------------------------

    /**
     * Returns the dst.
     *
     * @return CardSelection
     */
    public CardSelection getDst()
    {
        return dst;
    }

    /**
     * Returns the src.
     *
     * @return CardSelection
     */
    public CardSelection getSrc()
    {
        return src;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public boolean canRedo()
    {
        return !hasBeenDone;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public boolean canUndo()
    {
        return hasBeenDone;
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void load(DataStream stream)
    {
        this.dst = new CardSelection();
        this.dst.load(stream);
        this.src = new CardSelection();
        this.src.load(stream);
        this.hasBeenDone = stream.readBoolean();
    }

    /**
     * TODO: javadoc
     *
     * @throws RuntimeException TODO: javadoc
     */
    public void redo()
    {
        if (!canRedo())
        {
            throw new RuntimeException();
        }
        hasBeenDone = true;

        /* this.napoleonTableau.redo(this); */
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void save(DataStream stream)
    {
        this.dst.save(stream);
        this.src.save(stream);
        stream.writeBoolean(this.hasBeenDone);
    }

    /**
     * TODO: javadoc
     *
     * @throws RuntimeException TODO: javadoc
     */
    public void undo()
    {
        if (!canUndo())
        {
            throw new RuntimeException();
        }
        hasBeenDone = false;

        /* this.napoleonTableau.undo(this); */
    }
}
