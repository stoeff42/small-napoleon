package ch.cs.napoleon.solitaire;

import ch.cs.napoleon.solitaire.gui.CardSelection;

import ch.cs.solitaire.Card;
import ch.cs.solitaire.Tableau;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.undo.UndoManager;


/**
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public class NapoleonTableau extends Tableau
{
    //~ Static fields/initializers ---------------------------------------------

    /** @TODO: javadoc! */
    private static final int NR_OF_CARDS_IN_ROW = 5;

    /** @TODO: javadoc! */
    private static final int NR_OF_NAPOLEON_ROWS = 8;

    /** @TODO: javadoc! */
    private static final int NR_OF_FOUNDATIONS = 4;

    /** @TODO: javadoc! */
    private static final String TABLEAU = "napoleontableau.tableau"; //$NON-NLS-1$

    /** @TODO: javadoc! */
    private static final String FOUNDATIONS = "napoleontableau.foundations"; //$NON-NLS-1$

    /** @TODO: javadoc! */
    private static final String NAPOLEON_ROWS = "napoleontableau.napoleonrows"; //$NON-NLS-1$

    /** @TODO: javadoc! */
    private static final String HELPS = "napoleontableau.helps"; //$NON-NLS-1$

    /** @TODO: javadoc! */
    private static final String CELLAR = "napoleontableau.cellar"; //$NON-NLS-1$

    /** @TODO: javadoc! */
    private static final String UNDO_MANAGER = "napoleontableau.undomanager"; //$NON-NLS-1$

    //~ Instance fields --------------------------------------------------------

    /** @TODO: javadoc! */
    private ArrayList foundations;

    /** @TODO: javadoc! */
    private ArrayList helps;

    /** @TODO: javadoc! */
    private ArrayList napoleonRows;

    /** @TODO: javadoc! */
    private Cellar cellar;

    /** @TODO: javadoc! */
    private UndoManager undoManager;

    /** @TODO: javadoc! */
    private UndoMove currentUndo;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new NapoleonTableau object.
     *
     * @throws Exception javadoc!
     */
    public NapoleonTableau() throws Exception
    {
        super(Tableau.PACK_OF_52, 1);
        this.createTableau();
        this.fillTableau();
        this.undoManager = new UndoManager();
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * Returns the cellar.
     *
     * @return Cellar
     */
    public final Cellar getCellar()
    {
        return this.cellar;
    }

    /**
     * @TODO: javadoc!
     *
     * @param map javadoc!
     */
    public final void setData(final Map map)
    {
        try
        {
            this.setTableau((Card[]) map.get(TABLEAU));
            this.foundations = (ArrayList) map.get(FOUNDATIONS);
            this.napoleonRows = (ArrayList) map.get(NAPOLEON_ROWS);
            this.helps = (ArrayList) map.get(HELPS);
            this.cellar = (Cellar) map.get(CELLAR);
            this.undoManager = (UndoManager) map.get(UNDO_MANAGER);
        }
        catch (Throwable e)
        {
            this.newGame();
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final Map getData()
    {
        Map map = new HashMap();
        map.put(TABLEAU, this.getTableau());
        map.put(FOUNDATIONS, this.foundations);
        map.put(NAPOLEON_ROWS, this.napoleonRows);
        map.put(HELPS, this.helps);
        map.put(CELLAR, this.getCellar());
        map.put(UNDO_MANAGER, this.undoManager);

        return map;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final boolean isFinished()
    {
        return this.cardsFinished() == this.getSize();
    }

    /**
     * @TODO: javadoc!
     *
     * @param i javadoc!
     *
     * @return javadoc!
     */
    public final Foundation getFoundation(final int i)
    {
        return (Foundation) this.foundations.get(i);
    }

    /**
     * @TODO: javadoc!
     *
     * @param i javadoc!
     *
     * @return javadoc!
     */
    public final Help getHelp(final int i)
    {
        return (Help) this.helps.get(i);
    }

    /**
     * @TODO: javadoc!
     *
     * @param i javadoc!
     *
     * @return javadoc!
     */
    public final NapoleonRow getNapoleonRow(final int i)
    {
        return (NapoleonRow) this.napoleonRows.get(i);
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final boolean canRedo()
    {
        return this.undoManager.canRedo();
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final boolean canUndo()
    {
        return this.undoManager.canUndo();
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final int cardsFinished()
    {
        int cardsFinished = 0;

        for (int i = 0; i < this.foundations.size(); i++)
        {
            cardsFinished += this.getFoundation(i).getSize();
        }

        return cardsFinished;
    }

    /**
     * @TODO: javadoc!
     *
     * @param src javadoc!
     * @param dst javadoc!
     *
     * @return javadoc!
     */
    public final boolean move(final CardSelection src, final CardSelection dst)
    {
        return this.move(src, dst, false);
    }

    /**
     * @TODO: javadoc!
     */
    public final void newGame()
    {
        this.shuffle();
        this.init();
    }

    /**
     * @TODO: javadoc!
     *
     * @param undoMove javadoc!
     */
    public final void redo(final UndoMove undoMove)
    {
        if (this.move(undoMove.getSource(), undoMove.getDest(), true))
        {
            this.currentUndo = undoMove;
        }
        else
        {
            this.currentUndo = null;
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final UndoMove redo()
    {
        if (this.canRedo())
        {
            this.undoManager.redo();

            return this.currentUndo;
        }
        else
        {
            return null;
        }
    }

    /**
     * @TODO: javadoc!
     */
    public final void restartGame()
    {
        this.init();
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final UndoMove undo()
    {
        if (this.canUndo())
        {
            this.undoManager.undo();

            return this.currentUndo;
        }
        else
        {
            return null;
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @param undoMove javadoc!
     */
    public final void undo(final UndoMove undoMove)
    {
        CardSelection src = undoMove.getSource();
        CardSelection dst = undoMove.getDest();
        Card card = src.getCard();

        if (src.getType() == CardSelection.FOUNDATION)
        {
            this.getFoundation(src.getInfo1()).add(card);
        }
        else if (src.getType() == CardSelection.ROW)
        {
            NapoleonRow napoleonRow = this.getNapoleonRow(src.getInfo1());

            if ((napoleonRow.getSize() + 1) == src.getInfo2())
            {
                napoleonRow.forceAdd(card);
            }
            else
            {
                napoleonRow.add(card);
            }
        }
        else if (src.getType() == CardSelection.HELP)
        {
            this.getHelp(src.getInfo1()).set(src.getInfo2(), card);
        }
        else if (src.getType() == CardSelection.CELLAR)
        {
            this.getCellar().set(card);
        }

        if (dst.getType() == CardSelection.FOUNDATION)
        {
            this.getFoundation(dst.getInfo1()).remove();
        }
        else if (dst.getType() == CardSelection.ROW)
        {
            this.getNapoleonRow(dst.getInfo1()).remove();
        }
        else if (dst.getType() == CardSelection.HELP)
        {
            this.getHelp(dst.getInfo1()).remove(dst.getInfo2());
        }
        else if (dst.getType() == CardSelection.CELLAR)
        {
            this.getCellar().clear();
        }

        this.currentUndo = undoMove;
    }

    /**
     * @TODO: javadoc!
     */
    private void createTableau()
    {
        this.foundations = new ArrayList(NR_OF_FOUNDATIONS);

        for (int i = 0; i < NR_OF_FOUNDATIONS; i++)
        {
            this.foundations.add(new Foundation());
        }

        this.napoleonRows = new ArrayList(NR_OF_NAPOLEON_ROWS);

        for (int i = 0; i < NR_OF_NAPOLEON_ROWS; i++)
        {
            this.napoleonRows.add(new NapoleonRow());
        }

        this.helps = new ArrayList(2);

        for (int i = 0; i < 2; i++)
        {
            this.helps.add(new Help());
        }

        this.cellar = new Cellar();
    }

    /**
     * @TODO: javadoc!
     */
    private void fillTableau()
    {
        int foundationNr = 0;
        int rowNr = 0;
        int helpNr = 0;
        int foundationRankStart = this.get(0).getRank();
        int rowColNr = 0;
        int helpColNr = 0;
        ArrayList helpCards = new ArrayList(NR_OF_FOUNDATIONS);
        ArrayList rowCards = new ArrayList(NR_OF_CARDS_IN_ROW);

        for (int i = 0; i < this.getSize(); i++)
        {
            Card card = this.get(i);

            if (card.getRank() == foundationRankStart)
            {
                this.getFoundation(foundationNr).set(card);
                foundationNr++;
            }
            else
            {
                if (rowNr == NR_OF_NAPOLEON_ROWS)
                {
                    helpCards.add(card);
                    helpColNr++;

                    if (helpColNr == NR_OF_FOUNDATIONS)
                    {
                        this.getHelp(helpNr).set(helpCards);
                        helpNr++;
                        helpColNr = 0;
                        helpCards = new ArrayList(NR_OF_FOUNDATIONS);
                    }
                }
                else
                {
                    rowCards.add(card);
                    rowColNr++;

                    if (rowColNr == NR_OF_CARDS_IN_ROW)
                    {
                        this.getNapoleonRow(rowNr).set(rowCards);
                        rowNr++;
                        rowColNr = 0;
                        rowCards = new ArrayList(NR_OF_CARDS_IN_ROW);
                    }
                }
            }
        }

        this.getCellar().clear();
    }

    /**
     * @TODO: javadoc!
     *
     * @param src javadoc!
     *
     * @return javadoc!
     */
    private boolean fromSource(final CardSelection src)
    {
        boolean moveSucceeded = false;

        if (src.getType() == CardSelection.FOUNDATION)
        {
            moveSucceeded = this.getFoundation(src.getInfo1()).remove();
        }
        else if (src.getType() == CardSelection.ROW)
        {
            moveSucceeded = this.getNapoleonRow(src.getInfo1()).remove();
        }
        else if (src.getType() == CardSelection.HELP)
        {
            moveSucceeded = this.getHelp(src.getInfo1()).remove(src.getInfo2());
        }
        else if (src.getType() == CardSelection.CELLAR)
        {
            moveSucceeded = this.getCellar().clear();
        }

        return moveSucceeded;
    }

    /**
     * @TODO: javadoc!
     */
    private void init()
    {
        this.fillTableau();
        this.undoManager.discardAllEdits();
    }

    /**
     * @TODO: javadoc!
     *
     * @param src javadoc!
     * @param dst javadoc!
     * @param redoing javadoc!
     *
     * @return javadoc!
     */
    private boolean move(final CardSelection src, final CardSelection dst,
        final boolean redoing)
    {
        if ((src != null) && (dst != null))
        {
            boolean moveSucceeded;

            if (toDest(src, dst))
            {
                moveSucceeded = fromSource(src);
            }
            else
            {
                moveSucceeded = false;
            }

            if (moveSucceeded && !redoing)
            {
                this.undoManager.addEdit(new UndoMove(this, src, dst));
            }

            return moveSucceeded;
        }

        return false;
    }

    /**
     * @TODO: javadoc!
     *
     * @param src javadoc!
     * @param dst javadoc!
     *
     * @return javadoc!
     */
    private boolean toDest(final CardSelection src, final CardSelection dst)
    {
        Card card = src.getCard();
        boolean movePossible = false;

        if (dst.getType() == CardSelection.FOUNDATION)
        {
            movePossible = this.getFoundation(dst.getInfo1()).add(card);
        }
        else if (dst.getType() == CardSelection.ROW)
        {
            movePossible = this.getNapoleonRow(dst.getInfo1()).add(card);
        }
        else if (dst.getType() == CardSelection.HELP)
        {
            movePossible = false;
        }
        else if (dst.getType() == CardSelection.CELLAR)
        {
            movePossible = this.getCellar().set(card);
        }

        return movePossible;
    }
}
