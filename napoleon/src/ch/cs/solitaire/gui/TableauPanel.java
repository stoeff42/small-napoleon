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
 * TODO:
 *
 * @author <a href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public abstract class TableauPanel extends JPanel
{
    //~ Static fields/initializers ---------------------------------------------

    /** TODO: */
    public static final int LEFT_PILE = SwingConstants.LEFT;

    /** TODO: */
    public static final int UP_PILE = SwingConstants.TOP;

    /** TODO: */
    public static final int RIGHT_PILE = SwingConstants.RIGHT;

    /** TODO: */
    public static final int DOWN_PILE = SwingConstants.BOTTOM;

    //~ Instance fields --------------------------------------------------------

    /** TODO: */
    private Map sets;

    /** TODO: */
    private String currentSetId;

    //~ Methods ----------------------------------------------------------------

    /**
     * TODO:
     *
     * @param card TODO:
     *
     * @return TODO:
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
     * TODO:
     *
     * @return TODO:
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
     * TODO:
     *
     * @param graphics TODO:
     */
    public final void paintComponent(final Graphics graphics)
    {
        super.paintComponent(graphics);
        this.drawDeck(graphics);
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    protected final Image getBackSuit()
    {
        return this.getCardSuits()[4][0];
    }

    /**
     * TODO:
     *
     * @return TODO:
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
     * TODO:
     *
     * @return TODO:
     */
    protected final Image[][] getCardSuits()
    {
        return this.getCurrentSet().getCardSuits();
    }

    /**
     * TODO:
     *
     * @return TODO:
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
     * TODO:
     *
     * @return TODO:
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
     * TODO:
     *
     * @return TODO:
     */
    protected final int getPileOffset()
    {
        return this.getCurrentSet().getPileOffset();
    }

    /**
     * TODO:
     *
     * @param pileSize TODO:
     *
     * @return TODO:
     */
    protected final int getPileSize(final int pileSize)
    {
        int pS = 0;

        if (pileSize > 0)
        {
            Math.min(pileSize, this.getMaxPileSize());
        }

        return pS;
    }

    /**
     * TODO:
     *
     * @param graphics TODO:
     * @param x TODO:
     * @param y TODO:
     */
    protected final void drawBack(final Graphics graphics, final int x,
        final int y)
    {
        graphics.drawImage(this.getBackSuit(), x, y, null);
    }

    /**
     * TODO:
     *
     * @param graphics TODO:
     * @param x TODO:
     * @param y TODO:
     * @param card TODO:
     * @param front TODO:
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
     * TODO:
     *
     * @param graphics TODO:
     */
    protected abstract void drawDeck(final Graphics graphics);

    /**
     * TODO: DOWN_PILE and UP_PILE implementation
     *
     * @param graphics TODO:
     * @param cards TODO:
     * @param front TODO:
     * @param pileFront TODO:
     * @param slotLocation TODO:
     * @param dir TODO:
     * @param clear TODO:
     */
    protected final void drawCardPile(final Graphics graphics,
        final ArrayList cards, final boolean front, final boolean pileFront,
        final Point slotLocation, final int dir, final boolean clear)
    {
        int pileSize = this.getPileSize(cards.size());
        int clearOffset = (pileSize - 1) * this.getPileOffset();

        if (dir == LEFT_PILE)
        {
            clearCardPile(graphics, slotLocation, clear, clearOffset);

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
                    pileFront);
            }

            this.drawCard(graphics, (int) slotLocation.getX() - clearOffset,
                (int) slotLocation.getY(), (Card) cards.get(cards.size() - 1),
                front);
        }
        else if (dir == RIGHT_PILE)
        {
            if (clear)
            {
                graphics.clearRect((int) slotLocation.getX()
                    + this.getCardSize().width + 1, (int) slotLocation.getY(),
                    clearOffset + this.getPileOffset(),
                    this.getCardSize().height + 1);
            }

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
                    pileFront);
            }

            this.drawCard(graphics, (int) slotLocation.getX() + clearOffset,
                (int) slotLocation.getY(), (Card) cards.get(cards.size() - 1),
                front);
        }
    }

    /**
     * TODO:
     *
     * @param graphics TODO:
     * @param x TODO:
     * @param y TODO:
     * @param card TODO:
     */
    protected final void drawFront(final Graphics graphics, final int x,
        final int y, final Card card)
    {
        graphics.drawImage(this.getCardSuit(card), x, y, null);
    }

    /**
     * TODO:
     *
     * @param graphics TODO:
     * @param x TODO:
     * @param y TODO:
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
     * TODO:
     *
     * @param id TODO:
     *
     * @return TODO:
     */
    private DeckSet getSet(final String id)
    {
        return (DeckSet) this.getSets().get(id);
    }

    /**
     * TODO:
     *
     * @param property TODO:
     *
     * @return TODO:
     */
    private static String getString(final String property)
    {
        return MessageProperties.getString("tableaupanel", //$NON-NLS-1$
            property);
    }

    /**
     * @TODO: javadoc!
     *
     * @param graphics @TODO: javadoc!
     * @param slotLocation @TODO: javadoc!
     * @param clear @TODO: javadoc!
     * @param clearOffset @TODO: javadoc!
     */
    private void clearCardPile(final Graphics graphics,
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
     * TODO:
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
