package ch.cs.solitaire.gui;

import ch.cs.gui.MessageProperties;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;


/**
 * @TODO: javadoc!
 *
 * @author $author$
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

    /** @TODO: javadoc! */
    private Dimension cardSize;

    /** @TODO: javadoc! */
    private Font font;

    /** @TODO: javadoc! */
    private String deckId;

    /** @TODO: javadoc! */
    private String fontFace;

    /** @TODO: javadoc! */
    private String name;

    /** @TODO: javadoc! */
    private Image[][] cardSuits;

    /** @TODO: javadoc! */
    private int cardSlotOffset;

    /** @TODO: javadoc! */
    private int fontSize;

    /** @TODO: javadoc! */
    private int maxPileSize;

    /** @TODO: javadoc! */
    private int pileOffset;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new DeckSet object.
     *
     * @param id javadoc!
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
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final Dimension getCardSize()
    {
        return this.cardSize;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final int getCardSlotOffset()
    {
        return this.cardSlotOffset;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
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
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final String getDeckId()
    {
        return this.deckId;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final Font getFont()
    {
        return this.font;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final int getFontSize()
    {
        return this.fontSize;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final int getMaxPileSize()
    {
        return this.maxPileSize;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final String getName()
    {
        return this.name;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final int getPileOffset()
    {
        return this.pileOffset;
    }

    /**
     * @TODO: javadoc!
     *
     * @param subProperty javadoc!
     *
     * @return javadoc!
     */
    private int getInt(final String subProperty)
    {
        return MessageProperties.getInt("deckset", //$NON-NLS-1$
            this.getDeckId(), subProperty);
    }

    /**
     * @TODO: javadoc!
     *
     * @param property javadoc!
     *
     * @return javadoc!
     */
    private String getString(final String property)
    {
        return MessageProperties.getString("deckset", //$NON-NLS-1$
            this.getDeckId(), property);
    }

    /**
     * @TODO: javadoc!
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
     * @TODO: javadoc!
     *
     * @param suit javadoc!
     * @param rank javadoc!
     * @param deckImage javadoc!
     *
     * @return javadoc!
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
