package ch.cs.napoleon.solitaire;

import ch.cs.napoleon.solitaire.gui.CardSelection;

import java.io.Serializable;

import javax.swing.undo.AbstractUndoableEdit;


/**
 * TODO:
 *
 * @author <a href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class UndoMove extends AbstractUndoableEdit implements Serializable
{
    //~ Instance fields --------------------------------------------------------

    /** TODO: */
    private CardSelection dest;

    /** TODO: */
    private CardSelection source;

    /** TODO: */
    private transient NapoleonTableau napoleonTableau;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new UndoMove object.
     */
    public UndoMove()
    {
        /**
         * Empty constructor
         */
    }

    /**
     * Creates a new UndoMove object.
     *
     * @param napoTabl TODO:
     * @param src TODO:
     * @param dst TODO:
     */
    public UndoMove(final NapoleonTableau napoTabl, final CardSelection src,
        final CardSelection dst)
    {
        this.napoleonTableau = napoTabl;
        this.source = src;
        this.dest = dst;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * TODO:
     *
     * @param dst TODO:
     */
    public final void setDest(final CardSelection dst)
    {
        this.dest = dst;
    }

    /**
     * Returns the dst.
     *
     * @return CardSelection
     */
    public final CardSelection getDest()
    {
        return this.dest;
    }

    /**
     * TODO:
     *
     * @param src TODO:
     */
    public final void setSource(final CardSelection src)
    {
        this.source = src;
    }

    /**
     * Returns the src.
     *
     * @return CardSelection
     */
    public final CardSelection getSource()
    {
        return this.source;
    }

    /**
     * TODO:
     */
    public final void redo()
    {
        super.redo();
        this.napoleonTableau.redo(this);
    }

    /**
     * TODO:
     */
    public final void undo()
    {
        super.undo();
        this.napoleonTableau.undo(this);
    }
}
