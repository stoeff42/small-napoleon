package ch.cs.napoleon;

import ch.cs.gui.MessageProperties;
import ch.cs.napoleon.gui.NapoleonMessage;
import ch.cs.napoleon.solitaire.gui.NapoleonTableauPanel;
import ch.cs.persistence.MapPersistence;
import ch.cs.solitaire.gui.DeckSet;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Iterator;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


/**
 * TODO:
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class Napoleon extends JFrame
{
    //~ Instance variables -------------------------------------------

    /** TODO: */
    private JButton suspendResumeGameButton;

    /** TODO: */
    private JMenu settings;

    /** TODO: */
    private JMenuItem suspendResumeGameMenuItem;

    /** TODO: */
    private NapoleonTableauPanel tableauPanel;

    //~ Constructors -------------------------------------------------

    /**
     * Creates a new Napoleon object.
     *
     * @throws Exception TODO:
     */
    public Napoleon()
            throws Exception
    {
        super();
        this.setTitle(getString("napoleontitle")); //$NON-NLS-1$
        GridBagLayout layout = new GridBagLayout();
        this.getContentPane().setLayout(layout);
        this.setJMenuBar(getMenu());
        this.addToolBar(layout);
        this.addNapoleonPanel(layout);
        this.setIconImage(
            MessageProperties.getImage(
                getString("iconimagefilename")) //$NON-NLS-1$
        .getImage());
        this.addWindowListener(new WindowHandler(this));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.load();
        this.init();
        this.pack();
    }

    //~ Methods ------------------------------------------------------

    /**
     * TODO:
     */
    public void load()
    {
        Map data = MapPersistence.read(getString("filename")); //$NON-NLS-1$
        if (!data.isEmpty())
        {
            this.tableauPanel.setData(data);
        }
        else
        {
            this.tableauPanel.newGame();
        }
    }

    /**
     * TODO:
     *
     * @param args TODO:
     */
    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(getString("lookandfeel")); //$NON-NLS-1$
        }
        catch (Exception e)
        {
            // Silently ignoring...
        }
        try
        {
            new Napoleon().setVisible(true);
        }
        catch (Throwable e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * TODO:
     */
    public void save()
    {
        MapPersistence.write(
            getString("filename"), //$NON-NLS-1$
            this.tableauPanel.getData());
    }

    /**
     * TODO:
     */
    public void showAbout()
    {
        NapoleonMessage.showAbout(this);
    }

    /**
     * TODO:
     */
    public void showRules()
    {
        NapoleonMessage.showRules(this);
    }

    /**
     * TODO:
     */
    protected void resume()
    {
        this.tableauPanel.resume();
        this.updateSuspendResume();
    }

    /**
     * TODO:
     */
    protected void suspend()
    {
        this.tableauPanel.suspend();
        this.updateSuspendResume();
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private ActionListener getAboutAction()
    {
        return new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    showAbout();
                }
            };
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JMenuItem getAboutMenuItem()
    {
        JMenuItem about = new JMenuItem(getString("about")); //$NON-NLS-1$
        about.addActionListener(this.getAboutAction());
        about.setAccelerator(
            KeyStroke.getKeyStroke(
                new Character('a'),
                java.awt.event.InputEvent.CTRL_MASK));
        return about;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private ActionListener getClearScoreTableAction()
    {
        return new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    tableauPanel.clearScoreTable();
                }
            };
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JMenuItem getClearScoreTableMenuItem()
    {
        JMenuItem clearScoreTable =
            new JMenuItem(getString("clearscoretable")); //$NON-NLS-1$
        clearScoreTable.addActionListener(
            this.getClearScoreTableAction());
        clearScoreTable.setAccelerator(
            KeyStroke.getKeyStroke(
                new Character('c'),
                java.awt.event.InputEvent.CTRL_MASK));
        return clearScoreTable;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private ActionListener getExitAction()
    {
        return new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    save();
                    dispose();
                }
            };
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JMenuItem getExitMenuItem()
    {
        JMenuItem exit = new JMenuItem(getString("exit")); //$NON-NLS-1$
        exit.addActionListener(this.getExitAction());
        exit.setAccelerator(
            KeyStroke.getKeyStroke(
                new Character('x'),
                java.awt.event.InputEvent.CTRL_MASK));
        return exit;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JMenu getGameMenu()
    {
        JMenu game = new JMenu(getString("game")); //$NON-NLS-1$
        game.add(this.getNewGameMenuItem());
        game.add(this.getRestartGameMenuItem());
        game.addSeparator();
        game.add(this.getSettingsMenu());
        game.addSeparator();
        game.add(this.getExitMenuItem());
        return game;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private ActionListener getGameRulesAction()
    {
        return new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    showRules();
                }
            };
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JButton getGameRulesButton()
    {
        JButton gameRules =
            new JButton(
                getString("gamerules"), //$NON-NLS-1$
                MessageProperties.getImage(
                    getString("rulesbuttonimagefilename"))); //$NON-NLS-1$
        gameRules.addActionListener(this.getGameRulesAction());
        return gameRules;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JMenuItem getGameRulesMenuItem()
    {
        JMenuItem gameRules = new JMenuItem(getString("gamerules")); //$NON-NLS-1$
        gameRules.addActionListener(this.getGameRulesAction());
        gameRules.setAccelerator(
            KeyStroke.getKeyStroke(
                new Character('r'),
                java.awt.event.InputEvent.CTRL_MASK));
        return gameRules;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JMenu getHelpMenu()
    {
        JMenu help = new JMenu(getString("help")); //$NON-NLS-1$
        help.add(getGameRulesMenuItem());
        help.add(getAboutMenuItem());
        return help;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JMenuBar getMenu()
    {
        JMenuBar menu = new JMenuBar();
        menu.add(this.getGameMenu());
        menu.add(this.getPlayMenu());
        menu.add(this.getScoreTableMenu());
        menu.add(this.getHelpMenu());
        return menu;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private ActionListener getNewGameAction()
    {
        return new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    tableauPanel.newGame();
                }
            };
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JButton getNewGameButton()
    {
        JButton newGame =
            new JButton(
                getString("newgame"), //$NON-NLS-1$
                MessageProperties.getImage(
                    getString("newbuttonimagefilename"))); //$NON-NLS-1$
        newGame.addActionListener(this.getNewGameAction());
        return newGame;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JMenuItem getNewGameMenuItem()
    {
        JMenuItem newGame = new JMenuItem(getString("newgame")); //$NON-NLS-1$
        newGame.addActionListener(this.getNewGameAction());
        newGame.setAccelerator(
            KeyStroke.getKeyStroke(
                new Character('n'),
                java.awt.event.InputEvent.CTRL_MASK));
        return newGame;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JMenu getPlayMenu()
    {
        JMenu play = new JMenu(getString("play")); //$NON-NLS-1$
        play.add(this.getUndoMoveMenuItem());
        play.add(this.getRedoMoveMenuItem());
        play.addSeparator();
        play.add(this.getSuspendResumeGameMenuItem());
        return play;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private ActionListener getRedoAction()
    {
        return new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    tableauPanel.redo();
                }
            };
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JButton getRedoMoveButton()
    {
        JButton redoMove =
            new JButton(
                getString("redomove"), //$NON-NLS-1$
                MessageProperties.getImage(
                    getString("redobuttonimagefilename"))); //$NON-NLS-1$
        redoMove.addActionListener(this.getRedoAction());
        return redoMove;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JMenuItem getRedoMoveMenuItem()
    {
        JMenuItem redoMove = new JMenuItem(getString("redomove")); //$NON-NLS-1$
        redoMove.addActionListener(this.getRedoAction());
        redoMove.setAccelerator(
            KeyStroke.getKeyStroke(
                new Character('y'),
                java.awt.event.InputEvent.CTRL_MASK));
        return redoMove;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private ActionListener getRestartGameAction()
    {
        return new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    tableauPanel.restartGame();
                }
            };
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JButton getRestartGameButton()
    {
        JButton restartGame =
            new JButton(
                getString("restartgame"), //$NON-NLS-1$
                MessageProperties.getImage(
                    getString("restartbuttonimagefilename"))); //$NON-NLS-1$
        restartGame.addActionListener(this.getRestartGameAction());
        return restartGame;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JMenuItem getRestartGameMenuItem()
    {
        JMenuItem restartGame =
            new JMenuItem(getString("restartgame")); //$NON-NLS-1$
        restartGame.addActionListener(this.getRestartGameAction());
        restartGame.setAccelerator(
            KeyStroke.getKeyStroke(
                new Character('r'),
                java.awt.event.InputEvent.CTRL_MASK));
        return restartGame;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JMenu getScoreTableMenu()
    {
        JMenu scoreTable = new JMenu(getString("scoretable")); //$NON-NLS-1$
        scoreTable.add(this.getShowScoreTableMenuItem());
        scoreTable.add(this.getClearScoreTableMenuItem());
        return scoreTable;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JMenu getSettingsMenu()
    {
        this.settings = new JMenu();
        this.settings.setText(getString("settings")); //$NON-NLS-1$
        return this.settings;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private ActionListener getShowScoreTableAction()
    {
        return new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    tableauPanel.showScoreTable();
                }
            };
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JMenuItem getShowScoreTableMenuItem()
    {
        JMenuItem showScoreTable =
            new JMenuItem(getString("showscoretable")); //$NON-NLS-1$
        showScoreTable.addActionListener(
            this.getShowScoreTableAction());
        showScoreTable.setAccelerator(
            KeyStroke.getKeyStroke(
                new Character('s'),
                java.awt.event.InputEvent.CTRL_MASK));
        return showScoreTable;
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
        return MessageProperties.getString("napoleon", property); //$NON-NLS-1$
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JButton getSuspendResumeGameButton()
    {
        this.suspendResumeGameButton = new JButton();
        this.suspendResumeGameButton.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    toggleSuspendResume();
                }
            });
        return this.suspendResumeGameButton;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JMenuItem getSuspendResumeGameMenuItem()
    {
        this.suspendResumeGameMenuItem = new JMenuItem();
        this.suspendResumeGameMenuItem.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    toggleSuspendResume();
                }
            });
        this.suspendResumeGameMenuItem.setAccelerator(
            KeyStroke.getKeyStroke(
                new Character('p'),
                java.awt.event.InputEvent.CTRL_MASK));
        return this.suspendResumeGameMenuItem;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JToolBar getToolBar()
    {
        JToolBar toolbar = new JToolBar();
        toolbar.setBorder(BorderFactory.createEtchedBorder());
        toolbar.setRollover(true);
        toolbar.setFloatable(false);
        toolbar.add(this.getNewGameButton());
        toolbar.add(this.getRestartGameButton());

        // toolbar.addSeparator();
        toolbar.add(new JSeparator(SwingConstants.VERTICAL));
        toolbar.add(this.getUndoMoveButton());
        toolbar.add(this.getRedoMoveButton());

        // toolbar.addSeparator();
        toolbar.add(new JSeparator(SwingConstants.VERTICAL));
        toolbar.add(this.getSuspendResumeGameButton());

        // toolbar.addSeparator();
        toolbar.add(new JSeparator(SwingConstants.VERTICAL));
        toolbar.add(this.getGameRulesButton());
        return toolbar;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private ActionListener getUndoAction()
    {
        return new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    if (tableauPanel.canUndo())
                    {
                        tableauPanel.undo();
                    }
                }
            };
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JButton getUndoMoveButton()
    {
        JButton undoMove =
            new JButton(
                getString("undomove"), //$NON-NLS-1$
                MessageProperties.getImage(
                    getString("undobuttonimagefilename"))); //$NON-NLS-1$
        undoMove.addActionListener(this.getUndoAction());
        return undoMove;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    private JMenuItem getUndoMoveMenuItem()
    {
        JMenuItem undoMove = new JMenuItem(getString("undomove")); //$NON-NLS-1$
        undoMove.addActionListener(this.getUndoAction());
        undoMove.setAccelerator(
            KeyStroke.getKeyStroke(
                new Character('z'),
                java.awt.event.InputEvent.CTRL_MASK));
        return undoMove;
    }

    /**
     * TODO:
     *
     * @param layout TODO:
     *
     * @throws Exception TODO:
     */
    private void addNapoleonPanel(GridBagLayout layout)
            throws Exception
    {
        this.tableauPanel = new NapoleonTableauPanel();
        this.getContentPane().add(this.tableauPanel);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 1;
        layout.setConstraints(tableauPanel, gbc);
    }

    /**
     * TODO:
     *
     * @param layout TODO:
     */
    private void addToolBar(GridBagLayout layout)
    {
        JToolBar toolbar = this.getToolBar();
        this.getContentPane().add(toolbar);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        layout.setConstraints(toolbar, gbc);
    }

    /**
     * TODO:
     */
    private void init()
    {
        this.initSettingsMenu();
        this.updateSuspendResume();
    }

    /**
     * TODO:
     */
    private void initSettingsMenu()
    {
        ButtonGroup group = new ButtonGroup();
        for (Iterator iterator =
                this.tableauPanel.getSets().values().iterator();
                iterator.hasNext();)
        {
            final DeckSet setting = (DeckSet) iterator.next();
            JCheckBoxMenuItem settingItem =
                new JCheckBoxMenuItem(setting.getName());
            group.add(settingItem);
            if (setting.getId().equals(
                        this.tableauPanel.getCurrentSetId()))
            {
                settingItem.setState(true);
            }
            settingItem.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        tableauPanel.setSet(setting.getId());
                    }
                });
            this.settings.add(settingItem);
        }
    }

    /**
     * TODO:
     */
    private void toggleSuspendResume()
    {
        this.tableauPanel.toggleSuspendResume();
        this.updateSuspendResume();
    }

    /**
     * TODO:
     */
    private void updateSuspendResume()
    {
        this.suspendResumeGameMenuItem.setText(this.tableauPanel
                .getSuspended()
            ? getString("resumegame") //$NON-NLS-1$
            : getString("suspendgame")); //$NON-NLS-1$
        this.suspendResumeGameButton.setText(this.tableauPanel
                .getSuspended()
            ? getString("resumegame") //$NON-NLS-1$
            : getString("suspendgame")); //$NON-NLS-1$
        this.suspendResumeGameButton.setIcon(this.tableauPanel
                .getSuspended()
            ? MessageProperties.getImage(
                getString("resumebuttonimagefilename")) //$NON-NLS-1$
            : MessageProperties.getImage(
                getString("suspendbuttonimagefilename"))); //$NON-NLS-1$
    }
}
