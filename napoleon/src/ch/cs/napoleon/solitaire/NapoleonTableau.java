package ch.cs.napoleon.solitaire;

import ch.cs.napoleon.solitaire.gui.CardSelection;
import ch.cs.solitaire.Card;
import ch.cs.solitaire.Tableau;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.undo.UndoManager;


/**
 * TODO:
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class NapoleonTableau extends Tableau
{
    //~ Static variables/initializers --------------------------------

    /** TODO: */
    private static final String TABLEAU = "napoleontableau.tableau"; //$NON-NLS-1$

    /** TODO: */
    private static final String FOUNDATIONS =
        "napoleontableau.foundations"; //$NON-NLS-1$

    /** TODO: */
    private static final String NAPOLEON_ROWS =
        "napoleontableau.napoleonrows"; //$NON-NLS-1$

    /** TODO: */
    private static final String HELPS = "napoleontableau.helps"; //$NON-NLS-1$

    /** TODO: */
    private static final String CELLAR = "napoleontableau.cellar"; //$NON-NLS-1$

    /** TODO: */
    private static final String UNDO_MANAGER =
        "napoleontableau.undomanager"; //$NON-NLS-1$

    //~ Instance variables -------------------------------------------

    /** TODO: */
    private ArrayList foundations;

    /** TODO: */
    private ArrayList helps;

    /** TODO: */
    private ArrayList napoleonRows;

    /** TODO: */
    private Cellar cellar;

    /** TODO: */
    private UndoManager undoManager;

    /** TODO: */
    private UndoMove currentUndo;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new NapoleonTableau object.
     *
     * @throws Exception TODO:
     */
    public NapoleonTableau()
            throws Exception
    {
        super(Tableau.PACK_OF_52, 1);
        this.createTableau();
        this.fillTableau();
        this.undoManager = new UndoManager();
    }

    //~ Methods ------------------------------------------------------

    /**
     * Returns the cellar.
     *
     * @return Cellar
     */
    public Cellar getCellar()
    {
        return this.cellar;
    }

    /**
     * TODO:
     *
     * @param map TODO:
     */
    public void setData(Map map)
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
     * TODO:
     *
     * @return TODO:
     */
    public Map getData()
    {
        Map map = new HashMap();
        map.put(
            TABLEAU,
            this.getTableau());
        map.put(FOUNDATIONS, this.foundations);
        map.put(NAPOLEON_ROWS, this.napoleonRows);
        map.put(HELPS, this.helps);
        map.put(
            CELLAR,
            this.getCellar());
        map.put(UNDO_MANAGER, this.undoManager);
        return map;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public boolean isFinished()
    {
        return this.cardsFinished() == this.getSize();
    }

    /**
     * Returns the foundations.
     *
     * @param i TODO:
     *
     * @return Foundation[]
     */
    public Foundation getFoundation(int i)
    {
        return (Foundation) this.foundations.get(i);
    }

    /**
     * Returns the helps.
     *
     * @param i TODO:
     *
     * @return Help[]
     */
    public Help getHelp(int i)
    {
        return (Help) this.helps.get(i);
    }

    /**
     * Returns the napoleonRows.
     *
     * @param i TODO:
     *
     * @return NapoleonRow[]
     */
    public NapoleonRow getNapoleonRow(int i)
    {
        return (NapoleonRow) this.napoleonRows.get(i);
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public boolean canRedo()
    {
        return this.undoManager.canRedo();
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public boolean canUndo()
    {
        return this.undoManager.canUndo();
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public int cardsFinished()
    {
        int cardsFinished = 0;
        for (int i = 0; i < this.foundations.size(); i++)
        {
            cardsFinished += this.getFoundation(i).getSize();
        }
        return cardsFinished;
    }

    /**
     * TODO:
     *
     * @param src TODO:
     * @param dst TODO:
     *
     * @return TODO:
     */
    public boolean move(CardSelection src, CardSelection dst)
    {
        return this.move(src, dst, false);
    }

    /**
     * TODO:
     */
    public void newGame()
    {
        this.shuffle();
        this.init();
    }

    /**
     * TODO:
     *
     * @param undoMove TODO:
     */
    public void redo(UndoMove undoMove)
    {
        if (this.move(
                    undoMove.getSrc(),
                    undoMove.getDst(),
                    true))
        {
            this.currentUndo = undoMove;
        }
        else
        {
            this.currentUndo = null;
        }
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public UndoMove redo()
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
     * TODO:
     */
    public void restartGame()
    {
        this.init();
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public UndoMove undo()
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
     * TODO:
     *
     * @param undoMove TODO:
     */
    public void undo(UndoMove undoMove)
    {
        CardSelection src = undoMove.getSrc();
        CardSelection dst = undoMove.getDst();
        Card card = src.getCard();
        if (src.getType() == CardSelection.FOUNDATION)
        {
            this.getFoundation(src.getInfo1()).add(card);
        }
        else if (src.getType() == CardSelection.ROW)
        {
            NapoleonRow napoleonRow =
                this.getNapoleonRow(src.getInfo1());
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
            this.getHelp(src.getInfo1()).set(
                src.getInfo2(),
                card);
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
     * TODO:
     */
    private void createTableau()
    {
        this.foundations = new ArrayList(4);
        for (int i = 0; i < 4; i++)
        {
            this.foundations.add(new Foundation());
        }
        this.napoleonRows = new ArrayList(8);
        for (int i = 0; i < 8; i++)
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
     * TODO:
     */
    private void fillTableau()
    {
        int foundationNr = 0;
        int rowNr = 0;
        int helpNr = 0;
        int foundationRankStart = this.get(0).getRank();
        int rowColNr = 0;
        int helpColNr = 0;
        ArrayList helpCards = new ArrayList(4);
        ArrayList rowCards = new ArrayList(5);
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
                if (rowNr == 8)
                {
                    helpCards.add(card);
                    helpColNr++;
                    if (helpColNr == 4)
                    {
                        this.getHelp(helpNr).set(helpCards);
                        helpNr++;
                        helpColNr = 0;
                        helpCards = new ArrayList(4);
                    }
                }
                else
                {
                    rowCards.add(card);
                    rowColNr++;
                    if (rowColNr == 5)
                    {
                        this.getNapoleonRow(rowNr).set(rowCards);
                        rowNr++;
                        rowColNr = 0;
                        rowCards = new ArrayList(5);
                    }
                }
            }
        }
        this.getCellar().clear();
    }

    /**
     * TODO:
     */
    private void init()
    {
        this.fillTableau();
        this.undoManager.discardAllEdits();
    }

    /**
     * TODO:
     *
     * @param src TODO:
     * @param dst TODO:
     * @param redoing TODO:
     *
     * @return TODO:
     */
    private boolean move(CardSelection src, CardSelection dst,
        boolean redoing)
    {
        if ((src != null) && (dst != null))
        {
            Card card = src.getCard();
            boolean movePossible = false;
            if (dst.getType() == CardSelection.FOUNDATION)
            {
                movePossible =
                    this.getFoundation(dst.getInfo1()).add(card);
            }
            else if (dst.getType() == CardSelection.ROW)
            {
                movePossible =
                    this.getNapoleonRow(dst.getInfo1()).add(card);
            }
            else if (dst.getType() == CardSelection.HELP)
            {
            }
            else if (dst.getType() == CardSelection.CELLAR)
            {
                movePossible = this.getCellar().set(card);
            }
            boolean moveSucceeded = movePossible;
            if (movePossible)
            {
                if (src.getType() == CardSelection.FOUNDATION)
                {
                    moveSucceeded =
                        this.getFoundation(src.getInfo1()).remove();
                }
                else if (src.getType() == CardSelection.ROW)
                {
                    moveSucceeded =
                        this.getNapoleonRow(src.getInfo1()).remove();
                }
                else if (src.getType() == CardSelection.HELP)
                {
                    this.getHelp(src.getInfo1()).remove(
                        src.getInfo2());
                }
                else if (src.getType() == CardSelection.CELLAR)
                {
                    this.getCellar().clear();
                }
            }
            if (moveSucceeded && !redoing)
            {
                this.undoManager.addEdit(
                    new UndoMove(this, src, dst));
            }
            return moveSucceeded;
        }
        return false;
    }
}
