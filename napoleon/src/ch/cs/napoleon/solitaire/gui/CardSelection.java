package ch.cs.napoleon.solitaire.gui;

import ch.cs.solitaire.Card;

import java.awt.Point;

import java.io.Serializable;


/**
 * TODO:
 *
 * @author <a href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class CardSelection implements Serializable
{
    //~ Static fields/initializers ---------------------------------------------

    /** TODO: */
    public static final int FOUNDATION = 0;

    /** TODO: */
    public static final int ROW = 1;

    /** TODO: */
    public static final int HELP = 2;

    /** TODO: */
    public static final int CELLAR = 3;

    //~ Instance fields --------------------------------------------------------

    /** TODO: */
    private Card card;

    /** TODO: */
    private Point slot;

    /** TODO: */
    private int info1;

    /** TODO: */
    private int info2;

    /** TODO: */
    private int type;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new CardSelection object.
     */
    public CardSelection()
    {
        /**
         * Empty constructor
         */
    }

    /**
     * Creates a new CardSelection object.
     *
     * @param crd TODO:
     * @param slt TODO:
     * @param tpe TODO:
     * @param inf1 TODO:
     * @param inf2 TODO:
     */
    public CardSelection(final Card crd, final Point slt, final int tpe,
        final int inf1, final int inf2)
    {
        this.card = crd;
        this.slot = slt;
        this.type = tpe;
        this.info1 = inf1;
        this.info2 = inf2;
    }

    /**
     * Creates a new CardSelection object.
     *
     * @param crd TODO:
     * @param slt TODO:
     * @param tpe TODO:
     * @param inf1 TODO:
     */
    public CardSelection(final Card crd, final Point slt, final int tpe,
        final int inf1)
    {
        this.card = crd;
        this.slot = slt;
        this.type = tpe;
        this.info1 = inf1;
    }

    /**
     * Creates a new CardSelection object.
     *
     * @param crd TODO:
     * @param slt TODO:
     * @param tpe TODO:
     */
    public CardSelection(final Card crd, final Point slt, final int tpe)
    {
        this.card = crd;
        this.slot = slt;
        this.type = tpe;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * TODO:
     *
     * @param crd TODO:
     */
    public final void setCard(final Card crd)
    {
        this.card = crd;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final Card getCard()
    {
        return this.card;
    }

    /**
     * TODO:
     *
     * @param inf1 TODO:
     */
    public final void setInfo1(final int inf1)
    {
        this.info1 = inf1;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final int getInfo1()
    {
        return this.info1;
    }

    /**
     * TODO:
     *
     * @param inf2 TODO:
     */
    public final void setInfo2(final int inf2)
    {
        this.info2 = inf2;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final int getInfo2()
    {
        return this.info2;
    }

    /**
     * TODO:
     *
     * @param slt TODO:
     */
    public final void setSlot(final Point slt)
    {
        this.slot = slt;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final Point getSlot()
    {
        return this.slot;
    }

    /**
     * @TODO: javadoc!
     *
     * @param tpe @TODO: javadoc!
     */
    public final void setType(final int tpe)
    {
        this.type = tpe;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public final int getType()
    {
        return this.type;
    }
}
