package ch.cs.napoleon;

import ch.cs.napoleon.gui.Dialogs;
import ch.cs.napoleon.solitaire.gui.NapoleonTableauPanel;

import waba.fx.Graphics;

import waba.io.ByteArrayStream;
import waba.io.Catalog;
import waba.io.DataStream;

import waba.sys.Settings;

import waba.ui.ControlEvent;
import waba.ui.Event;
import waba.ui.MainWindow;
import waba.ui.MenuBar;
import waba.ui.PenEvent;


/**
 * TODO: javadoc
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class Napoleon extends MainWindow
{
    //~ Static variables/initializers --------------------------------

    /** TODO:  javadoc */
    private static final String palmDbName = "NapoleonData.Napo.Data";

    //~ Instance variables -------------------------------------------

    /** TODO: javadoc */
    private MenuBar menuBar;

    /** TODO: javadoc */
    private NapoleonTableauPanel tableauPanel;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new Napoleon object.
     */
    public Napoleon()
    {
        super();
        Settings.setPalmOSStyle(true);
        this.setDoubleBuffer(true);
        this.menuBar = new MenuBar(getMenu());
        this.setMenuBar(this.menuBar);
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO: javadoc
     */
    public void load()
    {
        Catalog catalog = new Catalog(palmDbName, Catalog.READ_ONLY);
        if (catalog.isOpen() && (catalog.getRecordCount() > 0))
        {
            catalog.setRecordPos(0);
            if (catalog.getRecordSize() > 0)
            {
                byte[] bytes = new byte[catalog.getRecordSize()];
                catalog.readBytes(
                    bytes,
                    0,
                    catalog.getRecordSize());
                this.tableauPanel.load(
                    new DataStream(new ByteArrayStream(bytes)));
                catalog.close();
            }
            else
            {
                this.tableauPanel.newGame();
            }
        }
        else
        {
            this.tableauPanel.newGame();
        }
    }

    /**
     * TODO: javadoc
     *
     * @param event TODO: javadoc
     */
    public void onEvent(Event event)
    {
        if (event.type == PenEvent.PEN_DOWN)
        {
            Graphics graphics = this.tableauPanel.createGraphics();
            this.tableauPanel.processSelection(graphics,
                ((PenEvent) event).x, ((PenEvent) event).y);
        }
        else if (event.type == ControlEvent.WINDOW_CLOSED)
        {
            if (event.target == this.menuBar)
            {
                switch (menuBar.getSelectedMenuItem())
                {
                    case (1) :
                        this.tableauPanel.newGame();
                        break;
                    case (2) :
                        this.tableauPanel.restartGame();
                        break;
                    case (3) :
                        this.tableauPanel.showScores();
                        break;
                    case (5) :
                        this.exit(0);
                        break;

                    /* case (101) :
                       if (this.tableauPanel.canUndo())
                       {
                           this.tableauPanel.undo();
                       }
                       break;
                       case (102) :
                           if (this.tableauPanel.canRedo())
                           {
                               this.tableauPanel.redo();
                           }
                           break; */
                    case (101) :
                        this.showRules();
                        break;
                    case (103) :
                        this.showAbout();
                        break;
                }
            }
        }
    }

    /**
     * TODO: javadoc
     */
    public void onExit()
    {
        this.save();
    }

    /**
     * TODO: javadoc
     */
    public void onStart()
    {
        this.tableauPanel = new NapoleonTableauPanel();
        this.tableauPanel.setRect(this.getClientRect());
        this.add(this.tableauPanel);
        this.load();
    }

    /**
     * TODO: javadoc
     */
    public void save()
    {
        Catalog catalog = new Catalog(palmDbName, Catalog.CREATE);
        catalog.setAttributes(Catalog.DB_ATTR_BACKUP);
        if (catalog.isOpen())
        {
            ByteArrayStream stream = new ByteArrayStream(512);
            this.tableauPanel.save(new DataStream(stream));
            stream.close();
            if (catalog.getRecordCount() == 0)
            {
                catalog.addRecord(
                    stream.count(),
                    0);
            }
            else
            {
                catalog.setRecordPos(0);
                catalog.resizeRecord(stream.count());
            }
            catalog.writeBytes(
                stream.getBuffer(),
                0,
                stream.count());
            catalog.setRecordAttributes(Catalog.REC_ATTR_DIRTY);
            catalog.close();
        }
    }

    /**
     * TODO: javadoc
     */
    public void showAbout()
    {
        Dialogs.showAbout(this);
    }

    /**
     * TODO: javadoc
     */
    public void showRules()
    {
        Dialogs.showRules(this);
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    private String[][] getMenu()
    {
        return new String[][]
        {
            new String[]
            {
                "Game", "New", "Restart", "Scores...", "-", "Exit"
            }, /*new String[] {"Play", "*Undo", "*Redo"},*/
            new String[] {"Help", "Rules...", "-", "About..."}
        };
    }
}
