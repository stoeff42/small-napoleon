package ch.cs.solitaire.gui;

import ch.cs.gui.MessageProperties;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;


/**
 * TODO:
 *
 * @author author
 * @version $Revision$
 */
public class DeckSet
{
    //~ Instance variables -------------------------------------------

    /** TODO: */
    private Dimension cardSize;

    /** TODO: */
    private Font font;

    /** TODO: */
    private String fontFace;

    /** TODO: */
    private String id;

    /** TODO: */
    private String name;

    /** TODO: */
    private Image[][] cardSuits;

    /** TODO: */
    private int cardSlotOffset;

    /** TODO: */
    private int fontSize;

    /** TODO: */
    private int maxPileSize;

    /** TODO: */
    private int pileOffset;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new DeckSet object.
     *
     * @param id TODO:
     */
    public DeckSet(String id)
    {
        this.id = id;
        this.cardSize =
            new Dimension(
                this.getInt("cardsizex"), //$NON-NLS-1$
                this.getInt("cardsizey")); //$NON-NLS-1$
        this.maxPileSize = this.getInt("maxpilesize"); //$NON-NLS-1$
        this.pileOffset = this.getInt("pileoffset"); //$NON-NLS-1$
        this.cardSlotOffset = this.getInt("cardslotoffset"); //$NON-NLS-1$
        this.fontSize = this.getInt("fontsize"); //$NON-NLS-1$
        this.fontFace = this.getString("fontface"); //$NON-NLS-1$
        this.name = this.getString("name"); //$NON-NLS-1$
        this.font =
            new Font(
                this.fontFace,
                Font.BOLD,
                this.getFontSize());
    }

    //~ Methods ------------------------------------------------------

    /**
     * Returns the cardSize.
     *
     * @return Dimension
     */
    public Dimension getCardSize()
    {
        return this.cardSize;
    }

    /**
     * Returns the cardSlotOffset.
     *
     * @return int
     */
    public int getCardSlotOffset()
    {
        return this.cardSlotOffset;
    }

    /**
     * Returns the cardSuits.
     *
     * @return Image[][]
     */
    public Image[][] getCardSuits()
    {
        if (this.cardSuits == null)
        {
            this.createCardSuits();
        }
        return this.cardSuits;
    }

    /**
     * Returns the font.
     *
     * @return Font
     */
    public Font getFont()
    {
        return this.font;
    }

    /**
     * Returns the fontSize.
     *
     * @return int
     */
    public int getFontSize()
    {
        return this.fontSize;
    }

    /**
     * Returns the id.
     *
     * @return String
     */
    public String getId()
    {
        return this.id;
    }

    /**
     * Returns the maxPileSize.
     *
     * @return int
     */
    public int getMaxPileSize()
    {
        return this.maxPileSize;
    }

    /**
     * Returns the name.
     *
     * @return String
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the pileOffset.
     *
     * @return int
     */
    public int getPileOffset()
    {
        return this.pileOffset;
    }

    /**
     * TODO:
     *
     * @param subProperty TODO:
     *
     * @return TODO:
     */
    private int getInt(String subProperty)
    {
        return MessageProperties.getInt(
            "deckset", //$NON-NLS-1$
            this.getId(),
            subProperty);
    }

    /**
     * TODO:
     *
     * @param property TODO:
     *
     * @return TODO:
     */
    private String getString(String property)
    {
        return MessageProperties.getString(
            "deckset", //$NON-NLS-1$
            this.getId(),
            property);
    }

    /**
     * TODO:
     */
    private void createCardSuits()
    {
        ImageIcon deckImage =
            MessageProperties.getImage(
                this.getString("deckimagefilename")); //$NON-NLS-1$
        Image image = deckImage.getImage();
        this.cardSuits = new Image[5][];
        for (int suit = 0; suit < 4; suit++)
        {
            this.cardSuits[suit] = new Image[13];
            for (int rank = 0; rank < 13; rank++)
            {
                this.cardSuits[suit][rank] =
                    this.extractCardSuit(suit, rank, image);
            }
        }
        this.cardSuits[4] = new Image[2];
        this.cardSuits[4][0] = this.extractCardSuit(0, 4, image);
        this.cardSuits[4][1] = this.extractCardSuit(1, 4, image);
    }

    /**
     * TODO:
     *
     * @param suit TODO:
     * @param rank TODO:
     * @param deckImage TODO:
     *
     * @return TODO:
     */
    private Image extractCardSuit(int suit, int rank, Image deckImage)
    {
        Image image =
            new BufferedImage((int) this.getCardSize().getWidth(),
                (int) this.getCardSize().getHeight(),
                BufferedImage.TYPE_INT_RGB);
        image.getGraphics().drawImage(deckImage, 0, 0,
            (int) this.getCardSize().getWidth(),
            (int) this.getCardSize().getHeight(),
            (int) (rank * this.getCardSize().getWidth()),
            (int) (suit * this.getCardSize().getHeight()),
            (int) ((rank + 1) * this.getCardSize().getWidth()),
            (int) ((suit + 1) * this.getCardSize().getHeight()), null);
        return image;
    }
}
