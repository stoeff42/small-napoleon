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
    //~ Static fields/initializers ---------------------------------------------

    /** @TODO: javadoc! */
    private static final int NR_OF_SUITS = 4;

    /** @TODO: javadoc! */
    private static final int CARDS_PER_SUIT = 13;

    //~ Instance fields --------------------------------------------------------

    /** TODO: */
    private Dimension cardSize;

    /** TODO: */
    private Font font;

    /** TODO: */
    private String deckId;

    /** TODO: */
    private String fontFace;

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

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new DeckSet object.
     *
     * @param id TODO:
     */
    public DeckSet(final String id)
    {
        this.deckId = id;
        this.cardSize =
            new Dimension(this.getInt("cardsizex"), //$NON-NLS-1$
                this.getInt("cardsizey")); //$NON-NLS-1$
        this.maxPileSize = this.getInt("maxpilesize"); //$NON-NLS-1$
        this.pileOffset = this.getInt("pileoffset"); //$NON-NLS-1$
        this.cardSlotOffset = this.getInt("cardslotoffset"); //$NON-NLS-1$
        this.fontSize = this.getInt("fontsize"); //$NON-NLS-1$
        this.fontFace = this.getString("fontface"); //$NON-NLS-1$
        this.name = this.getString("name"); //$NON-NLS-1$
        this.font = new Font(this.fontFace, Font.BOLD, this.getFontSize());
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * Returns the cardSize.
     *
     * @return Dimension
     */
    public final Dimension getCardSize()
    {
        return this.cardSize;
    }

    /**
     * Returns the cardSlotOffset.
     *
     * @return int
     */
    public final int getCardSlotOffset()
    {
        return this.cardSlotOffset;
    }

    /**
     * Returns the cardSuits.
     *
     * @return Image[][]
     */
    public final Image[][] getCardSuits()
    {
        if (this.cardSuits == null)
        {
            this.createCardSuits();
        }

        return this.cardSuits;
    }

    /**
     * Returns the id.
     *
     * @return String
     */
    public final String getDeckId()
    {
        return this.deckId;
    }

    /**
     * Returns the font.
     *
     * @return Font
     */
    public final Font getFont()
    {
        return this.font;
    }

    /**
     * Returns the fontSize.
     *
     * @return int
     */
    public final int getFontSize()
    {
        return this.fontSize;
    }

    /**
     * Returns the maxPileSize.
     *
     * @return int
     */
    public final int getMaxPileSize()
    {
        return this.maxPileSize;
    }

    /**
     * Returns the name.
     *
     * @return String
     */
    public final String getName()
    {
        return this.name;
    }

    /**
     * Returns the pileOffset.
     *
     * @return int
     */
    public final int getPileOffset()
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
    private int getInt(final String subProperty)
    {
        return MessageProperties.getInt("deckset", //$NON-NLS-1$
            this.getDeckId(), subProperty);
    }

    /**
     * TODO:
     *
     * @param property TODO:
     *
     * @return TODO:
     */
    private String getString(final String property)
    {
        return MessageProperties.getString("deckset", //$NON-NLS-1$
            this.getDeckId(), property);
    }

    /**
     * TODO:
     */
    private void createCardSuits()
    {
        ImageIcon deckImage =
            MessageProperties.getImage(this.getString("deckimagefilename")); //$NON-NLS-1$
        Image image = deckImage.getImage();
        this.cardSuits = new Image[NR_OF_SUITS + 1][];

        for (int suit = 0; suit < NR_OF_SUITS; suit++)
        {
            this.cardSuits[suit] = new Image[CARDS_PER_SUIT];

            for (int rank = 0; rank < CARDS_PER_SUIT; rank++)
            {
                this.cardSuits[suit][rank] =
                    this.extractCardSuit(suit, rank, image);
            }
        }

        this.cardSuits[NR_OF_SUITS] = new Image[2];
        this.cardSuits[NR_OF_SUITS][0] =
            this.extractCardSuit(0, NR_OF_SUITS, image);
        this.cardSuits[NR_OF_SUITS][1] =
            this.extractCardSuit(1, NR_OF_SUITS, image);
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
    private Image extractCardSuit(final int suit, final int rank,
        final Image deckImage)
    {
        Image image =
            new BufferedImage((int) this.getCardSize().getWidth(),
                (int) this.getCardSize().getHeight(), BufferedImage.TYPE_INT_RGB);
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
