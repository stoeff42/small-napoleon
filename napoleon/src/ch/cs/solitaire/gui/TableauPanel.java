package ch.cs.solitaire.gui;

import ch.cs.gui.MessageProperties;

import ch.cs.solitaire.Card;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


/**
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public abstract class TableauPanel extends JPanel
{
    //~ Static fields/initializers ---------------------------------------------

    /** @TODO: javadoc! */
    public static final int LEFT_PILE = SwingConstants.LEFT;

    /** @TODO: javadoc! */
    public static final int UP_PILE = SwingConstants.TOP;

    /** @TODO: javadoc! */
    public static final int RIGHT_PILE = SwingConstants.RIGHT;

    /** @TODO: javadoc! */
    public static final int DOWN_PILE = SwingConstants.BOTTOM;

    //~ Instance fields --------------------------------------------------------

    /** @TODO: javadoc! */
    private Map sets;

    /** @TODO: javadoc! */
    private String currentSetId;

    //~ Methods ----------------------------------------------------------------

    /**
     * @TODO: javadoc!
     *
     * @param card javadoc!
     *
     * @return javadoc!
     */
    public final Image getCardSuit(final Card card)
    {
        return this.getCardSuits()[card.getSuit() - 1][card.getRank() - 1];
    }

    /**
     * Returns the currentSetId.
     *
     * @return String
     */
    public final String getCurrentSetId()
    {
        String id = this.currentSetId;

        if (this.currentSetId == null)
        {
            id = (String) this.getSets().keySet().iterator().next();
        }

        return id;
    }

    /**
     * Returns the font.
     *
     * @return int
     */
    public final Font getFont()
    {
        return this.getCurrentSet().getFont();
    }

    /**
     * Sets the tableauSize.
     *
     * @param setId The tableauSize to set
     */
    public final void setSet(final String setId)
    {
        if (this.getSets().containsKey(setId))
        {
            this.currentSetId = setId;
            this.invalidate();
            this.revalidate();
            SwingUtilities.windowForComponent(this).pack();
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final Map getSets()
    {
        if (this.sets == null)
        {
            this.createSets();
        }

        return this.sets;
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     */
    public final void paintComponent(final Graphics graphics)
    {
        super.paintComponent(graphics);
        this.drawDeck(graphics);
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    protected final Image getBackSuit()
    {
        return this.getCardSuits()[4][0];
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    protected final Dimension getCardSize()
    {
        return this.getCurrentSet().getCardSize();
    }

    /**
     * Returns the cardSlotOffset.
     *
     * @return int
     */
    protected final int getCardSlotOffset()
    {
        return this.getCurrentSet().getCardSlotOffset();
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    protected final Image[][] getCardSuits()
    {
        return this.getCurrentSet().getCardSuits();
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    protected final DeckSet getCurrentSet()
    {
        return this.getSet(this.getCurrentSetId());
    }

    /**
     * Returns the sizeNames.
     *
     * @return String[]
     */
    protected final String getCurrentSetName()
    {
        return this.getCurrentSet().getName();
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    protected final Image getEmptySuit()
    {
        return this.getCardSuits()[4][1];
    }

    /**
     * Returns the fontSize.
     *
     * @return int
     */
    protected final int getFontSize()
    {
        return this.getCurrentSet().getFontSize();
    }

    /**
     * Returns the maxPileSize.
     *
     * @return int
     */
    protected final int getMaxPileSize()
    {
        return this.getCurrentSet().getMaxPileSize();
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    protected final int getPileOffset()
    {
        return this.getCurrentSet().getPileOffset();
    }

    /**
     * @TODO: javadoc!
     *
     * @param pileSize javadoc!
     *
     * @return javadoc!
     */
    protected final int getPileSize(final int pileSize)
    {
        int pS = 0;

        if (pileSize > 0)
        {
            pS = Math.min(pileSize, this.getMaxPileSize());
        }

        return pS;
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     * @param x javadoc!
     * @param y javadoc!
     */
    protected final void drawBack(final Graphics graphics, final int x,
        final int y)
    {
        graphics.drawImage(this.getBackSuit(), x, y, null);
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     * @param x javadoc!
     * @param y javadoc!
     * @param card javadoc!
     * @param front javadoc!
     */
    protected final void drawCard(final Graphics graphics, final int x,
        final int y, final Card card, final boolean front)
    {
        if (front)
        {
            this.drawFront(graphics, x, y, card);
        }
        else
        {
            this.drawBack(graphics, x, y);
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     */
    protected abstract void drawDeck(final Graphics graphics);

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     * @param cards javadoc!
     * @param slotLocation javadoc!
     * @param pileInfo javadoc!
     */
    protected final void drawCardPile(final Graphics graphics,
        final ArrayList cards, final Point slotLocation, final PileInfo pileInfo)
    {
        int pileSize = this.getPileSize(cards.size());
        int clearOffset = (pileSize - 1) * this.getPileOffset();

        if (pileInfo.getDir() == LEFT_PILE)
        {
            clearLeftPile(graphics, slotLocation, pileInfo.isClear(),
                clearOffset);

            for (int i = 0; i < (pileSize - 1); i++)
            {
                int index = 0;

                if (i != 0)
                {
                    index = cards.size() - pileSize + i;
                }

                this.drawCard(graphics,
                    (int) slotLocation.getX() - (i * getPileOffset()),
                    (int) slotLocation.getY(), (Card) cards.get(index),
                    pileInfo.isPileFront());
            }

            this.drawCard(graphics, (int) slotLocation.getX() - clearOffset,
                (int) slotLocation.getY(), (Card) cards.get(cards.size() - 1),
                pileInfo.isFront());
        }
        else if (pileInfo.getDir() == RIGHT_PILE)
        {
            clearRightPile(graphics, slotLocation, pileInfo.isClear(),
                clearOffset);

            for (int i = 0; i < (pileSize - 1); i++)
            {
                int index = 0;

                if (i != 0)
                {
                    index = cards.size() - pileSize + i;
                }

                this.drawCard(graphics,
                    (int) slotLocation.getX() + (i * getPileOffset()),
                    (int) slotLocation.getY(), (Card) cards.get(index),
                    pileInfo.isPileFront());
            }

            this.drawCard(graphics, (int) slotLocation.getX() + clearOffset,
                (int) slotLocation.getY(), (Card) cards.get(cards.size() - 1),
                pileInfo.isFront());
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     * @param x javadoc!
     * @param y javadoc!
     * @param card javadoc!
     */
    protected final void drawFront(final Graphics graphics, final int x,
        final int y, final Card card)
    {
        graphics.drawImage(this.getCardSuit(card), x, y, null);
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     * @param x javadoc!
     * @param y javadoc!
     */
    protected final void drawSelection(final Graphics graphics, final int x,
        final int y)
    {
        graphics.setXORMode(Color.WHITE);
        graphics.fillRect(x + 1, y + 1, this.getCardSize().width - 2,
            this.getCardSize().height - 2);
        graphics.setPaintMode();
    }

    /**
     * @TODO: javadoc!
     *
     * @param id javadoc!
     *
     * @return javadoc!
     */
    private DeckSet getSet(final String id)
    {
        return (DeckSet) this.getSets().get(id);
    }

    /**
     * @TODO: javadoc!
     *
     * @param property javadoc!
     *
     * @return javadoc!
     */
    private static String getString(final String property)
    {
        return MessageProperties.getString("tableaupanel", //$NON-NLS-1$
            property);
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     * @param slotLocation javadoc!
     * @param clear javadoc!
     * @param clearOffset javadoc!
     */
    private void clearLeftPile(final Graphics graphics,
        final Point slotLocation, final boolean clear, final int clearOffset)
    {
        if (clear)
        {
            graphics.clearRect((int) slotLocation.getX() - clearOffset
                - this.getPileOffset(), (int) slotLocation.getY(),
                clearOffset + this.getPileOffset(),
                this.getCardSize().height + 1);
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics javadoc!
     * @param slotLocation javadoc!
     * @param clear javadoc!
     * @param clearOffset javadoc!
     */
    private void clearRightPile(final Graphics graphics,
        final Point slotLocation, final boolean clear, final int clearOffset)
    {
        if (clear)
        {
            graphics.clearRect((int) slotLocation.getX()
                + this.getCardSize().width + 1, (int) slotLocation.getY(),
                clearOffset + this.getPileOffset(),
                this.getCardSize().height + 1);
        }
    }

    /**
     * @TODO: javadoc!
     */
    private void createSets()
    {
        String[] settingsIds = getString("decksets").split(","); //$NON-NLS-1$ //$NON-NLS-2$
        this.sets = new TreeMap();

        for (int i = 0; i < settingsIds.length; i++)
        {
            this.sets.put(settingsIds[i], new DeckSet(settingsIds[i]));
        }
    }
}
