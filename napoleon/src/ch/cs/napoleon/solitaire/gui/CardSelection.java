package ch.cs.napoleon.solitaire.gui;

import ch.cs.solitaire.Card;

import java.awt.Point;

import java.io.Serializable;


/**
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public class CardSelection implements Serializable
{
    //~ Static fields/initializers ---------------------------------------------

    /** @TODO: javadoc! */
    public static final int FOUNDATION = 0;

    /** @TODO: javadoc! */
    public static final int ROW = 1;

    /** @TODO: javadoc! */
    public static final int HELP = 2;

    /** @TODO: javadoc! */
    public static final int CELLAR = 3;

    //~ Instance fields --------------------------------------------------------

    /** @TODO: javadoc! */
    private Card card;

    /** @TODO: javadoc! */
    private Point slot;

    /** @TODO: javadoc! */
    private int info1;

    /** @TODO: javadoc! */
    private int info2;

    /** @TODO: javadoc! */
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
     * @param crd javadoc!
     * @param slt javadoc!
     * @param tpe javadoc!
     * @param inf1 javadoc!
     * @param inf2 javadoc!
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
     * @param crd javadoc!
     * @param slt javadoc!
     * @param tpe javadoc!
     * @param inf1 javadoc!
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
     * @param crd javadoc!
     * @param slt javadoc!
     * @param tpe javadoc!
     */
    public CardSelection(final Card crd, final Point slt, final int tpe)
    {
        this.card = crd;
        this.slot = slt;
        this.type = tpe;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * @TODO: javadoc!
     *
     * @param crd javadoc!
     */
    public final void setCard(final Card crd)
    {
        this.card = crd;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final Card getCard()
    {
        return this.card;
    }

    /**
     * @TODO: javadoc!
     *
     * @param inf1 javadoc!
     */
    public final void setInfo1(final int inf1)
    {
        this.info1 = inf1;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final int getInfo1()
    {
        return this.info1;
    }

    /**
     * @TODO: javadoc!
     *
     * @param inf2 javadoc!
     */
    public final void setInfo2(final int inf2)
    {
        this.info2 = inf2;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final int getInfo2()
    {
        return this.info2;
    }

    /**
     * @TODO: javadoc!
     *
     * @param slt javadoc!
     */
    public final void setSlot(final Point slt)
    {
        this.slot = slt;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final Point getSlot()
    {
        return this.slot;
    }

    /**
     * @TODO: javadoc!
     *
     * @param tpe javadoc!
     */
    public final void setType(final int tpe)
    {
        this.type = tpe;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final int getType()
    {
        return this.type;
    }
}
