package ch.cs.napoleon.solitaire;

import ch.cs.napoleon.solitaire.gui.CardSelection;

import java.io.Serializable;

import javax.swing.undo.AbstractUndoableEdit;


/**
 * TODO:
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class UndoMove extends AbstractUndoableEdit
    implements Serializable
{
    //~ Instance variables -------------------------------------------

    /** TODO: */
    private CardSelection dst;

    /** TODO: */
    private CardSelection src;

    /** TODO: */
    private transient NapoleonTableau napoleonTableau;

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
     * @param napoleonTableau TODO:
     * @param src TODO:
     * @param dst TODO:
     */
    public UndoMove(NapoleonTableau napoleonTableau,
        CardSelection src, CardSelection dst)
    {
        this.napoleonTableau = napoleonTableau;
        this.src = src;
        this.dst = dst;
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO:
     *
     * @param dst TODO:
     */
    public void setDst(CardSelection dst)
    {
        this.dst = dst;
    }

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
     * TODO:
     *
     * @param src TODO:
     */
    public void setSrc(CardSelection src)
    {
        this.src = src;
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
     * TODO:
     */
    public void redo()
    {
        super.redo();
        this.napoleonTableau.redo(this);
    }

    /**
     * TODO:
     */
    public void undo()
    {
        super.undo();
        this.napoleonTableau.undo(this);
    }
}
