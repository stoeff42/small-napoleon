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
import javax.swing.ImageIcon;
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
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public class Napoleon extends JFrame
{
    //~ Instance fields --------------------------------------------------------

    /** @TODO: javadoc! */
    private JButton suspendResumeGameButton;

    /** @TODO: javadoc! */
    private JMenu settings;

    /** @TODO: javadoc! */
    private JMenuItem suspendResumeGameMenuItem;

    /** @TODO: javadoc! */
    private NapoleonTableauPanel tableauPanel;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new Napoleon object.
     *
     * @throws Exception javadoc!
     */
    public Napoleon() throws Exception
    {
        super();
        this.setTitle(getString("napoleontitle")); //$NON-NLS-1$

        GridBagLayout layout = new GridBagLayout();
        this.getContentPane().setLayout(layout);
        this.setJMenuBar(getMenu());
        this.addToolBar(layout);
        this.addNapoleonPanel(layout);
        this.setIconImage(MessageProperties.getImage(getString(
                    "iconimagefilename")) //$NON-NLS-1$
        .getImage());
        this.addWindowListener(new WindowHandler(this));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.load();
        this.init();
        this.pack();
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    public final NapoleonTableauPanel getTableauPanel()
    {
        return this.tableauPanel;
    }

    /**
     * @TODO: javadoc!
     */
    public final void load()
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
     * @TODO: javadoc!
     *
     * @param args javadoc!
     */
    public static void main(final String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(getString("lookandfeel")); //$NON-NLS-1$
        }
        catch (Exception e)
        {
            /**
             * Silently ignoring...
             */
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
     * @TODO: javadoc!
     */
    public final void save()
    {
        MapPersistence.write(getString("filename"), //$NON-NLS-1$
            this.tableauPanel.getData());
    }

    /**
     * @TODO: javadoc!
     */
    public final void showAbout()
    {
        NapoleonMessage.showAbout(this);
    }

    /**
     * @TODO: javadoc!
     */
    public final void showRules()
    {
        NapoleonMessage.showRules(this);
    }

    /**
     * @TODO: javadoc!
     */
    protected final void resume()
    {
        this.tableauPanel.resume();
        this.updateSuspendResume();
    }

    /**
     * @TODO: javadoc!
     */
    protected final void suspend()
    {
        this.tableauPanel.suspend();
        this.updateSuspendResume();
    }

    /**
     * @TODO: javadoc!
     */
    protected final void toggleSuspendResume()
    {
        this.tableauPanel.toggleSuspendResume();
        this.updateSuspendResume();
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private ActionListener getAboutAction()
    {
        return new ActionListener()
            {
                public final void actionPerformed(final ActionEvent event)
                {
                    showAbout();
                }
            };
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private JMenuItem getAboutMenuItem()
    {
        JMenuItem about = new JMenuItem(getString("about")); //$NON-NLS-1$
        about.addActionListener(this.getAboutAction());
        about.setAccelerator(KeyStroke.getKeyStroke(new Character('a'),
                java.awt.event.InputEvent.CTRL_MASK));

        return about;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private ActionListener getClearScoreTableAction()
    {
        return new ActionListener()
            {
                public final void actionPerformed(final ActionEvent event)
                {
                    Napoleon.this.getTableauPanel().clearScoreTable();
                }
            };
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private JMenuItem getClearScoreTableMenuItem()
    {
        JMenuItem clearScoreTable = new JMenuItem(getString("clearscoretable")); //$NON-NLS-1$
        clearScoreTable.addActionListener(this.getClearScoreTableAction());
        clearScoreTable.setAccelerator(KeyStroke.getKeyStroke(
                new Character('c'), java.awt.event.InputEvent.CTRL_MASK));

        return clearScoreTable;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private ActionListener getExitAction()
    {
        return new ActionListener()
            {
                public final void actionPerformed(final ActionEvent event)
                {
                    save();
                    dispose();
                }
            };
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private JMenuItem getExitMenuItem()
    {
        JMenuItem exit = new JMenuItem(getString("exit")); //$NON-NLS-1$
        exit.addActionListener(this.getExitAction());
        exit.setAccelerator(KeyStroke.getKeyStroke(new Character('x'),
                java.awt.event.InputEvent.CTRL_MASK));

        return exit;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
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
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private ActionListener getGameRulesAction()
    {
        return new ActionListener()
            {
                public final void actionPerformed(final ActionEvent event)
                {
                    showRules();
                }
            };
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private JButton getGameRulesButton()
    {
        JButton gameRules =
            new JButton(getString("gamerules"), //$NON-NLS-1$
                MessageProperties.getImage(getString("rulesbuttonimagefilename"))); //$NON-NLS-1$
        gameRules.addActionListener(this.getGameRulesAction());

        return gameRules;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private JMenuItem getGameRulesMenuItem()
    {
        JMenuItem gameRules = new JMenuItem(getString("gamerules")); //$NON-NLS-1$
        gameRules.addActionListener(this.getGameRulesAction());
        gameRules.setAccelerator(KeyStroke.getKeyStroke(new Character('r'),
                java.awt.event.InputEvent.CTRL_MASK));

        return gameRules;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private JMenu getHelpMenu()
    {
        JMenu help = new JMenu(getString("help")); //$NON-NLS-1$
        help.add(getGameRulesMenuItem());
        help.add(getAboutMenuItem());

        return help;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
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
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private ActionListener getNewGameAction()
    {
        return new ActionListener()
            {
                public final void actionPerformed(final ActionEvent event)
                {
                    Napoleon.this.getTableauPanel().newGame();
                }
            };
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private JButton getNewGameButton()
    {
        JButton newGame =
            new JButton(getString("newgame"), //$NON-NLS-1$
                MessageProperties.getImage(getString("newbuttonimagefilename"))); //$NON-NLS-1$
        newGame.addActionListener(this.getNewGameAction());

        return newGame;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private JMenuItem getNewGameMenuItem()
    {
        JMenuItem newGame = new JMenuItem(getString("newgame")); //$NON-NLS-1$
        newGame.addActionListener(this.getNewGameAction());
        newGame.setAccelerator(KeyStroke.getKeyStroke(new Character('n'),
                java.awt.event.InputEvent.CTRL_MASK));

        return newGame;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
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
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private ActionListener getRedoAction()
    {
        return new ActionListener()
            {
                public final void actionPerformed(final ActionEvent event)
                {
                    Napoleon.this.getTableauPanel().redo();
                }
            };
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private JButton getRedoMoveButton()
    {
        JButton redoMove =
            new JButton(getString("redomove"), //$NON-NLS-1$
                MessageProperties.getImage(getString("redobuttonimagefilename"))); //$NON-NLS-1$
        redoMove.addActionListener(this.getRedoAction());

        return redoMove;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private JMenuItem getRedoMoveMenuItem()
    {
        JMenuItem redoMove = new JMenuItem(getString("redomove")); //$NON-NLS-1$
        redoMove.addActionListener(this.getRedoAction());
        redoMove.setAccelerator(KeyStroke.getKeyStroke(new Character('y'),
                java.awt.event.InputEvent.CTRL_MASK));

        return redoMove;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private ActionListener getRestartGameAction()
    {
        return new ActionListener()
            {
                public final void actionPerformed(final ActionEvent event)
                {
                    Napoleon.this.getTableauPanel().restartGame();
                }
            };
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private JButton getRestartGameButton()
    {
        JButton restartGame =
            new JButton(getString("restartgame"), //$NON-NLS-1$
                MessageProperties.getImage(getString(
                        "restartbuttonimagefilename"))); //$NON-NLS-1$
        restartGame.addActionListener(this.getRestartGameAction());

        return restartGame;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private JMenuItem getRestartGameMenuItem()
    {
        JMenuItem restartGame = new JMenuItem(getString("restartgame")); //$NON-NLS-1$
        restartGame.addActionListener(this.getRestartGameAction());
        restartGame.setAccelerator(KeyStroke.getKeyStroke(new Character('r'),
                java.awt.event.InputEvent.CTRL_MASK));

        return restartGame;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private JMenu getScoreTableMenu()
    {
        JMenu scoreTable = new JMenu(getString("scoretable")); //$NON-NLS-1$
        scoreTable.add(this.getShowScoreTableMenuItem());
        scoreTable.add(this.getClearScoreTableMenuItem());

        return scoreTable;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private JMenu getSettingsMenu()
    {
        this.settings = new JMenu();
        this.settings.setText(getString("settings")); //$NON-NLS-1$

        return this.settings;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private ActionListener getShowScoreTableAction()
    {
        return new ActionListener()
            {
                public final void actionPerformed(final ActionEvent event)
                {
                    Napoleon.this.getTableauPanel().showScoreTable();
                }
            };
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private JMenuItem getShowScoreTableMenuItem()
    {
        JMenuItem showScoreTable = new JMenuItem(getString("showscoretable")); //$NON-NLS-1$
        showScoreTable.addActionListener(this.getShowScoreTableAction());
        showScoreTable.setAccelerator(KeyStroke.getKeyStroke(
                new Character('s'), java.awt.event.InputEvent.CTRL_MASK));

        return showScoreTable;
    }

    /**
     * @TODO: javadoc!
     *
     * @param property javadoc!
     *
     * @return javadoc!
     */
    private static String getString(final String property)
    {
        return MessageProperties.getString("napoleon", property); //$NON-NLS-1$
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private JButton getSuspendResumeGameButton()
    {
        this.suspendResumeGameButton = new JButton();
        this.suspendResumeGameButton.addActionListener(new ActionListener()
            {
                public final void actionPerformed(final ActionEvent event)
                {
                    Napoleon.this.toggleSuspendResume();
                }
            });

        return this.suspendResumeGameButton;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private JMenuItem getSuspendResumeGameMenuItem()
    {
        this.suspendResumeGameMenuItem = new JMenuItem();
        this.suspendResumeGameMenuItem.addActionListener(new ActionListener()
            {
                public final void actionPerformed(final ActionEvent event)
                {
                    Napoleon.this.toggleSuspendResume();
                }
            });
        this.suspendResumeGameMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                new Character('p'), java.awt.event.InputEvent.CTRL_MASK));

        return this.suspendResumeGameMenuItem;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
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
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private ActionListener getUndoAction()
    {
        return new ActionListener()
            {
                public final void actionPerformed(final ActionEvent event)
                {
                    if (Napoleon.this.getTableauPanel().canUndo())
                    {
                        Napoleon.this.getTableauPanel().undo();
                    }
                }
            };
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private JButton getUndoMoveButton()
    {
        JButton undoMove =
            new JButton(getString("undomove"), //$NON-NLS-1$
                MessageProperties.getImage(getString("undobuttonimagefilename"))); //$NON-NLS-1$
        undoMove.addActionListener(this.getUndoAction());

        return undoMove;
    }

    /**
     * @TODO: javadoc!
     *
     * @return javadoc!
     */
    private JMenuItem getUndoMoveMenuItem()
    {
        JMenuItem undoMove = new JMenuItem(getString("undomove")); //$NON-NLS-1$
        undoMove.addActionListener(this.getUndoAction());
        undoMove.setAccelerator(KeyStroke.getKeyStroke(new Character('z'),
                java.awt.event.InputEvent.CTRL_MASK));

        return undoMove;
    }

    /**
     * @TODO: javadoc!
     *
     * @param layout javadoc!
     *
     * @throws Exception javadoc!
     */
    private void addNapoleonPanel(final GridBagLayout layout)
        throws Exception
    {
        this.tableauPanel = new NapoleonTableauPanel();
        this.getContentPane().add(this.tableauPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 1;
        layout.setConstraints(Napoleon.this.tableauPanel, gbc);
    }

    /**
     * @TODO: javadoc!
     *
     * @param layout javadoc!
     */
    private void addToolBar(final GridBagLayout layout)
    {
        JToolBar toolbar = this.getToolBar();
        this.getContentPane().add(toolbar);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        layout.setConstraints(toolbar, gbc);
    }

    /**
     * @TODO: javadoc!
     */
    private void init()
    {
        this.initSettingsMenu();
        this.updateSuspendResume();
    }

    /**
     * @TODO: javadoc!
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

            if (setting.getDeckId().equals(this.tableauPanel.getCurrentSetId()))
            {
                settingItem.setState(true);
            }

            settingItem.addActionListener(new ActionListener()
                {
                    public final void actionPerformed(final ActionEvent event)
                    {
                        Napoleon.this.getTableauPanel().setSet(setting
                            .getDeckId());
                    }
                });
            this.settings.add(settingItem);
        }
    }

    /**
     * @TODO: javadoc!
     */
    private void updateSuspendResume()
    {
        String suspendedString;
        ImageIcon buttonImage;

        if (this.tableauPanel.getSuspended())
        {
            suspendedString = getString("resumegame"); //$NON-NLS-1$
            buttonImage =
                MessageProperties.getImage(getString(
                        "resumebuttonimagefilename")); //$NON-NLS-1$
        }
        else
        {
            suspendedString = getString("suspendgame"); //$NON-NLS-1$
            buttonImage =
                MessageProperties.getImage(getString(
                        "suspendbuttonimagefilename")); //$NON-NLS-1$
        }

        this.suspendResumeGameMenuItem.setText(suspendedString);
        this.suspendResumeGameButton.setText(suspendedString);

        this.suspendResumeGameButton.setIcon(buttonImage);
    }
}
