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
import javax.swing.SwingUtilities;


/**
 * TODO:
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public abstract class TableauPanel extends JPanel
{
    //~ Static variables/initializers --------------------------------

    /** TODO: */
    public static final int LEFT_PILE = SwingUtilities.LEFT;

    /** TODO: */
    public static final int UP_PILE = SwingUtilities.TOP;

    /** TODO: */
    public static final int RIGHT_PILE = SwingUtilities.RIGHT;

    /** TODO: */
    public static final int DOWN_PILE = SwingUtilities.BOTTOM;

    //~ Instance variables -------------------------------------------

    /** TODO: */
    private Map sets;

    /** TODO: */
    private String currentSetId;

    //~ Methods ------------------------------------------------------

    /**
     * TODO:
     *
     * @param card TODO:
     *
     * @return TODO:
     */
    public Image getCardSuit(Card card)
    {
        return this.getCardSuits()[card.getSuit() - 1][card.getRank()
        - 1];
    }

    /**
     * Returns the currentSetId.
     *
     * @return String
     */
    public String getCurrentSetId()
    {
        return (currentSetId == null)
        ? (String) this.getSets().keySet().iterator().next()
        : this.currentSetId;
    }

    /**
     * Returns the font.
     *
     * @return int
     */
    public Font getFont()
    {
        return this.getCurrentSet().getFont();
    }

    /**
     * Sets the tableauSize.
     *
     * @param setId The tableauSize to set
     */
    public void setSet(String setId)
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
    public Map getSets()
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
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        this.drawDeck(graphics);
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    protected Image getBackSuit()
    {
        return this.getCardSuits()[4][0];
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    protected Dimension getCardSize()
    {
        return this.getCurrentSet().getCardSize();
    }

    /**
     * Returns the cardSlotOffset.
     *
     * @return int
     */
    protected int getCardSlotOffset()
    {
        return this.getCurrentSet().getCardSlotOffset();
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    protected Image[][] getCardSuits()
    {
        return this.getCurrentSet().getCardSuits();
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    protected DeckSet getCurrentSet()
    {
        return this.getSet(this.getCurrentSetId());
    }

    /**
     * Returns the sizeNames.
     *
     * @return String[]
     */
    protected String getCurrentSetName()
    {
        return this.getCurrentSet().getName();
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    protected Image getEmptySuit()
    {
        return this.getCardSuits()[4][1];
    }

    /**
     * Returns the fontSize.
     *
     * @return int
     */
    protected int getFontSize()
    {
        return this.getCurrentSet().getFontSize();
    }

    /**
     * Returns the maxPileSize.
     *
     * @return int
     */
    protected int getMaxPileSize()
    {
        return this.getCurrentSet().getMaxPileSize();
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    protected int getPileOffset()
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
    protected int getPileSize(int pileSize)
    {
        return (pileSize > 0)
        ? Math.min(
            pileSize,
            this.getMaxPileSize()) : 0;
    }

    /**
     * TODO:
     *
     * @param graphics TODO:
     * @param x TODO:
     * @param y TODO:
     */
    protected void drawBack(Graphics graphics, int x, int y)
    {
        graphics.drawImage(
            this.getBackSuit(),
            x,
            y,
            null);
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
    protected void drawCard(Graphics graphics, int x, int y,
        Card card, boolean front)
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
    protected abstract void drawDeck(Graphics graphics);

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
    protected void drawCardPile(Graphics graphics, ArrayList cards,
        boolean front, boolean pileFront, Point slotLocation,
        int dir, boolean clear)
    {
        int pileSize = this.getPileSize(cards.size());
        int clearOffset = (pileSize - 1) * this.getPileOffset();
        if (dir == LEFT_PILE)
        {
            if (clear)
            {
                graphics.clearRect((int) slotLocation.getX()
                    - clearOffset - this.getPileOffset(),
                    (int) slotLocation.getY(),
                    clearOffset + this.getPileOffset(),
                    this.getCardSize().height + 1);
            }
            for (int i = 0; i < (pileSize - 1); i++)
            {
                this.drawCard(graphics,
                    (int) slotLocation.getX() - (i * getPileOffset()),
                    (int) slotLocation.getY(),
                    (Card) cards.get((i == 0) ? 0
                                              : (cards.size()
                        - pileSize + i)), pileFront);
            }
            this.drawCard(graphics,
                (int) slotLocation.getX() - clearOffset,
                (int) slotLocation.getY(),
                (Card) cards.get(cards.size() - 1), front);
        }
        else if (dir == RIGHT_PILE)
        {
            if (clear)
            {
                graphics.clearRect((int) slotLocation.getX()
                    + this.getCardSize().width + 1,
                    (int) slotLocation.getY(),
                    clearOffset + this.getPileOffset(),
                    this.getCardSize().height + 1);
            }
            for (int i = 0; i < (pileSize - 1); i++)
            {
                this.drawCard(graphics,
                    (int) slotLocation.getX() + (i * getPileOffset()),
                    (int) slotLocation.getY(),
                    (Card) cards.get((i == 0) ? 0
                                              : (cards.size()
                        - pileSize + i)), pileFront);
            }
            this.drawCard(graphics,
                (int) slotLocation.getX() + clearOffset,
                (int) slotLocation.getY(),
                (Card) cards.get(cards.size() - 1), front);
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
    protected void drawFront(Graphics graphics, int x, int y,
        Card card)
    {
        graphics.drawImage(
            this.getCardSuit(card),
            x,
            y,
            null);
    }

    /**
     * TODO:
     *
     * @param graphics TODO:
     * @param x TODO:
     * @param y TODO:
     */
    protected void drawSelection(Graphics graphics, int x, int y)
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
    private DeckSet getSet(String id)
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
    private static String getString(String property)
    {
        return MessageProperties.getString("tableaupanel", //$NON-NLS-1$
            property);
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
            this.sets.put(
                settingsIds[i],
                new DeckSet(settingsIds[i]));
        }
    }
}
