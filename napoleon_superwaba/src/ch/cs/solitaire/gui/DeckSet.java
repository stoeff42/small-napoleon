package ch.cs.solitaire.gui;

import ch.cs.solitaire.Card;

import waba.fx.Color;
import waba.fx.Coord;
import waba.fx.Font;
import waba.fx.FontMetrics;
import waba.fx.Image;

import waba.ui.Control;


/**
 * TODO: javadoc
 *
 * @author author
 * @version $Revision$
 */
public class DeckSet {
    /** TODO:  javadoc */
    private Control control;

    /** TODO: javadoc */
    private Coord cardSize;

    /** TODO:  javadoc */
    private Coord suitSize;

    /** TODO: javadoc */
    private Font font;

    /** TODO:  javadoc */
    private FontMetrics fontMetrics;

    /** TODO: javadoc */
    private Image[][] cardSuits;

    /**
     * Creates a new DeckSet object.
     *
     * @param control TODO: javadoc
     */
    public DeckSet(Control cntrl) {
        this.control = cntrl;
        this.cardSize = new Coord(13, 19);
        this.suitSize = new Coord(9, 9);
        this.font = new Font("TinySmall", Font.PLAIN, 5);
        this.fontMetrics = this.control.getFontMetrics(this.getFont());
        this.createCardSuits();
    }

    /**
     * Returns the cardSize.
     *
     * @return Coord
     */
    public Coord getCardSize() {
        return this.cardSize;
    }

    /**
     * Returns the cardSlotOffset.
     *
     * @return int
     */
    public int getCardSlotOffset() {
        return 1;
    }

    /**
     * Returns the cardSuits.
     *
     * @return Image[][]
     */
    public Image[][] getCardSuits() {
        return this.cardSuits;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public Font getFont() {
        return this.font;
    }

    /**
     * Returns the maxPileSize.
     *
     * @return int
     */
    public int getMaxPileSize() {
        return 2;
    }

    /**
     * Returns the pileOffset.
     *
     * @return int
     */
    public int getPileOffset() {
        return 2;
    }

    /**
     * Returns the fontHeight.
     *
     * @return int
     */
    protected int getFontHeight() {
        return this.fontMetrics.getHeight();
    }

    /**
     * Returns the fontWidth.
     *
     * @param text TODO: javadoc
     *
     * @return int
     */
    protected int getTextWidth(String text) {
        return this.fontMetrics.getTextWidth(text);
    }

    /**
     * TODO: javadoc
     *
     * @param suit TODO: javadoc
     * @param rank TODO: javadoc
     * @param suitsImage TODO: javadoc
     *
     * @return TODO: javadoc
     */
    private Image createCardSuit(int suit, int rank, Image suitsImage) {
        Image image = this.createEmptyCardSuit();
        int offset = ((this.getCardSize().x - this.suitSize.x - 2) / 2) + 1;
        image.getGraphics().copyRect(suitsImage, suit * this.suitSize.x, 0,
            this.suitSize.x, this.suitSize.y, offset, offset);

        String rankString = Card.getRankString((byte) rank);
        offset = ((this.getCardSize().x - this.getTextWidth(rankString) - 2) / 2) +
            2;
        image.getGraphics().drawText(rankString, offset,
            this.getCardSize().y - this.getFontHeight() - 1);

        return image;
    }

    /**
     * TODO: javadoc
     */
    private void createCardSuits() {
        Image image = new Image("suits.bmp");
        this.cardSuits = new Image[5][];

        for (int suit = 0; suit < 4; suit++) {
            this.cardSuits[suit] = new Image[13];

            for (int rank = 0; rank < 13; rank++) {
                this.cardSuits[suit][rank] = this.createCardSuit(suit, rank,
                        image);
            }
        }

        this.cardSuits[4] = new Image[1];
        this.cardSuits[4][0] = this.createEmptyCardSuit();
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    private Image createEmptyCardSuit() {
        Image image = new Image(this.getCardSize().x, this.getCardSize().y);
        image.getGraphics().setBackColor(Color.WHITE);
        image.getGraphics().setForeColor(Color.BLACK);
        image.getGraphics().setFont(this.getFont());
        image.getGraphics().fillRect(0, 0, this.getCardSize().x - 1,
            this.getCardSize().y - 1);
        image.getGraphics().drawLine(1, 0, this.getCardSize().x - 2, 0);
        image.getGraphics().drawLine(this.getCardSize().x - 1, 1,
            this.getCardSize().x - 1, this.getCardSize().y - 2);
        image.getGraphics().drawLine(1, this.getCardSize().y - 1,
            this.getCardSize().x - 2, this.getCardSize().y - 1);
        image.getGraphics().drawLine(0, 1, 0, this.getCardSize().y - 2);

        return image;
    }
}
