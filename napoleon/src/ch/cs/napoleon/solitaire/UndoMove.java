package ch.cs.napoleon.solitaire;

import ch.cs.napoleon.solitaire.gui.CardSelection;

import java.io.Serializable;

import javax.swing.undo.AbstractUndoableEdit;


/**
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public class UndoMove extends AbstractUndoableEdit implements Serializable
{
    //~ Instance fields --------------------------------------------------------

    /** @TODO: javadoc! */
    private CardSelection dest;

    /** @TODO: javadoc! */
    private CardSelection source;

    /** @TODO: javadoc! */
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
     * @param napoTabl javadoc!
     * @param src javadoc!
     * @param dst javadoc!
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
     * @TODO: javadoc!
     *
     * @param dst javadoc!
     */
    public final void setDest(final CardSelection dst)
    {
        this.dest = dst;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final CardSelection getDest()
    {
        return this.dest;
    }

    /**
     * @TODO: javadoc!
     *
     * @param src javadoc!
     */
    public final void setSource(final CardSelection src)
    {
        this.source = src;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final CardSelection getSource()
    {
        return this.source;
    }

    /**
     * @TODO: javadoc!
     */
    public final void redo()
    {
        super.redo();
        this.napoleonTableau.redo(this);
    }

    /**
     * @TODO: javadoc!
     */
    public final void undo()
    {
        super.undo();
        this.napoleonTableau.undo(this);
    }
}
