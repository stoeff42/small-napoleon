package ch.cs.solitaire.gui;


/**
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public class PileInfo
{
    //~ Instance fields --------------------------------------------------------

    /** @TODO: javadoc! */
    private boolean clear;

    /** @TODO: javadoc! */
    private boolean front;

    /** @TODO: javadoc! */
    private boolean pileFront;

    /** @TODO: javadoc! */
    private int dir;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new PileInfo object.
     */
    public PileInfo()
    {
        /**
         * Empty constructor
         */
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * @TODO: javadoc!
     *
     * @param clr javadoc!
     */
    public final void setClear(final boolean clr)
    {
        this.clear = clr;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final boolean isClear()
    {
        return this.clear;
    }

    /**
     * @TODO: javadoc!
     *
     * @param dr javadoc!
     */
    public final void setDir(final int dr)
    {
        this.dir = dr;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final int getDir()
    {
        return this.dir;
    }

    /**
     * @TODO: javadoc!
     *
     * @param frnt javadoc!
     */
    public final void setFront(final boolean frnt)
    {
        this.front = frnt;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final boolean isFront()
    {
        return this.front;
    }

    /**
     * @TODO: javadoc!
     *
     * @param plFrnt javadoc!
     */
    public final void setPileFront(final boolean plFrnt)
    {
        this.pileFront = plFrnt;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final boolean isPileFront()
    {
        return this.pileFront;
    }
}
