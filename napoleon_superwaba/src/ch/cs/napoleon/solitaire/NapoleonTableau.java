package ch.cs.napoleon.solitaire;

import ch.cs.napoleon.solitaire.gui.CardSelection;
import ch.cs.solitaire.Card;
import ch.cs.solitaire.Tableau;

import waba.io.DataStream;

import waba.util.Vector;


/**
 * TODO: javadoc
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class NapoleonTableau extends Tableau
{
    //~ Instance variables -------------------------------------------

    /** TODO: javadoc */
    private Cellar cellar;

    /** TODO: javadoc */

    /* private UndoManager undoManager; */

    /** TODO: javadoc */

    /* rivate UndoMove currentUndo; */

    /** TODO: javadoc */
    private Vector foundations;

    /** TODO: javadoc */
    private Vector helps;

    /** TODO: javadoc */
    private Vector napoleonRows;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new NapoleonTableau object.
     */
    public NapoleonTableau()
    {
        super(Tableau.PACK_OF_52, 1);
        this.createTableau();
        this.fillTableau();

        /* this.undoManager = new UndoManager(); */
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
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public boolean isFinished()
    {
        return this.cardsFinished() == this.getSize();
    }

    /**
     * Returns the foundations.
     *
     * @param i TODO: javadoc
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
     * @param i TODO: javadoc
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
     * @param i TODO: javadoc
     *
     * @return NapoleonRow[]
     */
    public NapoleonRow getNapoleonRow(int i)
    {
        return (NapoleonRow) this.napoleonRows.get(i);
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */

    /* public boolean canRedo()
       {
           return this.undoManager.canRedo();
       } */

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */

    /* public boolean canUndo()
       {
           return this.undoManager.canUndo();
       } */

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
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
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void load(DataStream stream)
    {
        super.load(stream);
        for (int i = 0; i < this.foundations.size(); i++)
        {
            ((Foundation) this.foundations.get(i)).load(stream);
        }
        for (int i = 0; i < this.napoleonRows.size(); i++)
        {
            ((NapoleonRow) this.napoleonRows.get(i)).load(stream);
        }
        for (int i = 0; i < this.helps.size(); i++)
        {
            ((Help) this.helps.get(i)).load(stream);
        }
        this.cellar.load(stream);

        /* this.undoManager = new UndoManager();
           this.undoManager.load(stream); */
    }

    /**
     * TODO: javadoc
     *
     * @param src TODO: javadoc
     * @param dst TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public boolean move(CardSelection src,
        CardSelection dst /* ,
       boolean redoing */)
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

            /* if (moveSucceeded && !redoing)
               {
                   this.undoManager.addEdit(
                       new UndoMove(this, src, dst));
               } */
            return moveSucceeded;
        }
        return false;
    }

    /**
     * TODO: javadoc
     */

    /* public boolean move(CardSelection src, CardSelection dst)
       {
           return this.move(src, dst, false);
       } */

    /**
     * TODO: javadoc
     */
    public void newGame()
    {
        this.shuffle();
        this.init();
    }

    /**
     * TODO: javadoc
     */

    /* public void redo(UndoMove undoMove)
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
       } */

    /**
     * TODO: javadoc
     */

    /* public UndoMove redo()
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
       } */

    /**
     * TODO: javadoc
     */
    public void restartGame()
    {
        this.init();
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void save(DataStream stream)
    {
        super.save(stream);
        for (int i = 0; i < this.foundations.size(); i++)
        {
            ((Foundation) this.foundations.get(i)).save(stream);
        }
        for (int i = 0; i < this.napoleonRows.size(); i++)
        {
            ((NapoleonRow) this.napoleonRows.get(i)).save(stream);
        }
        for (int i = 0; i < this.helps.size(); i++)
        {
            ((Help) this.helps.get(i)).save(stream);
        }
        this.cellar.save(stream);

        /* this.undoManager.save(stream); */
    }

    /**
     * TODO: javadoc
     */

    /* public UndoMove undo()
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
       } */

    /**
     * TODO: javadoc
     */

    /* public void undo(UndoMove undoMove)
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
       } */

    /**
     * TODO: javadoc
     */
    private void createTableau()
    {
        this.foundations = new Vector(4);
        for (int i = 0; i < 4; i++)
        {
            this.foundations.add(new Foundation());
        }
        this.napoleonRows = new Vector(8);
        for (int i = 0; i < 8; i++)
        {
            this.napoleonRows.add(new NapoleonRow());
        }
        this.helps = new Vector(2);
        for (int i = 0; i < 2; i++)
        {
            this.helps.add(new Help());
        }
        this.cellar = new Cellar();
    }

    /**
     * TODO: javadoc
     */
    private void fillTableau()
    {
        int foundationNr = 0;
        int rowNr = 0;
        int helpNr = 0;
        int foundationRankStart = this.get(0).getRank();
        int rowColNr = 0;
        int helpColNr = 0;
        Vector helpCards = new Vector(4);
        Vector rowCards = new Vector(5);
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
                        helpCards = new Vector(4);
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
                        rowCards = new Vector(5);
                    }
                }
            }
        }
        this.getCellar().clear();
    }

    /**
     * TODO: javadoc
     */
    private void init()
    {
        this.fillTableau();

        /* this.undoManager.discardAllEdits(); */
    }
}
