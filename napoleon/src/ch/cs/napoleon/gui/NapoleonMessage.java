package ch.cs.napoleon.gui;

import ch.cs.gui.MessageProperties;

import ch.cs.napoleon.score.ScoreEntry;
import ch.cs.napoleon.score.ScoreTable;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;


/**
 * TODO:
 *
 * @author <a href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public final class NapoleonMessage
{
    //~ Static fields/initializers ---------------------------------------------

    /** @TODO: javadoc! */
    private static final int DEFAULT_MESSAGE_WIDTH = 50;

    /** @TODO: javadoc! */
    private static final int SECS_IN_MIN = 60;

    /** @TODO: javadoc! */
    private static final int MILLIS_IN_SEC = 1000;

    /** @TODO: javadoc! */
    private static final int MINS_IN_HOUR = 60;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new NapoleonMessage object.
     */
    private NapoleonMessage()
    {
        /**
         * Make constructor private in order to implement singleton correctly
         */
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * TODO:
     *
     * @param component TODO:
     *
     * @return TODO:
     */
    public static String askScoreText(final Component component)
    {
        JOptionPane.showMessageDialog(component, getString("finishedtext"), //$NON-NLS-1$
            getString("finishedtitle"), //$NON-NLS-1$
            JOptionPane.WARNING_MESSAGE);

        return JOptionPane.showInputDialog(component,
            getString("highscoretext"), //$NON-NLS-1$
            getString("highscoretitle"), //$NON-NLS-1$
            JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * TODO:
     *
     * @param component TODO:
     * @param scoreTable TODO:
     */
    public static void clearScoreTable(final Component component,
        final ScoreTable scoreTable)
    {
        if (scoreTable.getNrOfEntries() == 0)
        {
            JOptionPane.showMessageDialog(component,
                getString("noscoretableyet"), //$NON-NLS-1$
                getString("scoretabletitle"), //$NON-NLS-1$
                JOptionPane.INFORMATION_MESSAGE);

            return;
        }

        if (JOptionPane.showConfirmDialog(component,
                getString("deletescoretabletext"), //$NON-NLS-1$
                getString("deletescoretabletitle"), //$NON-NLS-1$
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
        {
            scoreTable.clear();
        }
    }

    /**
     * TODO:
     *
     * @param time TODO:
     * @param size TODO:
     *
     * @return TODO:
     */
    public static String makeDurationString(final int time, final int size)
    {
        int temp = time / MILLIS_IN_SEC;
        int secs = temp % SECS_IN_MIN;
        temp = (temp - secs) / SECS_IN_MIN;

        int mins = temp % MINS_IN_HOUR;
        int hours = (temp - mins) / MINS_IN_HOUR;
        StringBuffer buffer = new StringBuffer();

        if (hours > 0)
        {
            buffer.append(minimumSize(hours, size)).append(getString(
                    "hoursymbol")); //$NON-NLS-1$
        }

        buffer.append(minimumSize(mins, size)).append(getString("minutesymbol")); //$NON-NLS-1$
        buffer.append(minimumSize(secs, size)).append(getString("secondsymbol")); //$NON-NLS-1$

        return buffer.toString();
    }

    /**
     * TODO:
     *
     * @param component TODO:
     */
    public static void showAbout(final Component component)
    {
        JOptionPane.showMessageDialog(component, getString("copyright"), //$NON-NLS-1$
            getString("abouttitle"), //$NON-NLS-1$
            JOptionPane.PLAIN_MESSAGE,
            MessageProperties.getImage(getString("aboutimagefilename"))); //$NON-NLS-1$
    }

    /**
     * TODO:
     *
     * @param component TODO:
     * @param fileName TODO:
     */
    public static void showLoadingImageError(final Component component,
        final String fileName)
    {
        JOptionPane.showMessageDialog(component,
            new StringBuffer(getString("errorloadingimagetext")) //$NON-NLS-1$
        .append(fileName).toString(), getString("errortitle"), //$NON-NLS-1$
            JOptionPane.ERROR_MESSAGE);
    }

    /**
     * TODO:
     *
     * @param component TODO:
     */
    public static void showRules(final Component component)
    {
        JTextArea textArea =
            new JTextArea(getString("gamerules"), //$NON-NLS-1$
                0, DEFAULT_MESSAGE_WIDTH);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder(getString(
                    "gamerulestitle"))); //$NON-NLS-1$
        JOptionPane.showMessageDialog(component, scrollPane,
            getString("gamerulestitle"), //$NON-NLS-1$
            JOptionPane.PLAIN_MESSAGE,
            MessageProperties.getImage(getString("iconimagefilename"))); //$NON-NLS-1$
    }

    /**
     * TODO:
     *
     * @param component TODO:
     * @param scoreTable TODO:
     */
    public static void showScoreTable(final Component component,
        final ScoreTable scoreTable)
    {
        if (scoreTable.getNrOfEntries() == 0)
        {
            JOptionPane.showMessageDialog(component,
                getString("noscoretableyet"), //$NON-NLS-1$
                getString("scoretabletitle"), //$NON-NLS-1$
                JOptionPane.INFORMATION_MESSAGE);

            return;
        }

        JOptionPane.showMessageDialog(component, getScoreTable(scoreTable),
            getString("scoretabletitle"), //$NON-NLS-1$
            JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * TODO:
     *
     * @param scoreTable TODO:
     *
     * @return TODO:
     */
    private static JComponent getScoreTable(final ScoreTable scoreTable)
    {
        Object[][] data = scoreTable.toArray();
        Object[] columnNames = ScoreEntry.getNames();
        final int[] alignments = ScoreEntry.getAlignments();
        JTable table =
            new AlignedAndOptimizedColumnsTable(data, columnNames, alignments)
            {
                public final boolean isCellEditable(final int row,
                    final int column)
                {
                    return false;
                }
            };

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        JScrollPane pane = new JScrollPane(table);
        pane.setBorder(BorderFactory.createTitledBorder(getString(
                    "scoretabletitle"))); //$NON-NLS-1$

        return pane;
    }

    /**
     * TODO:
     *
     * @param property TODO:
     *
     * @return TODO:
     */
    private static String getString(final String property)
    {
        return MessageProperties.getString("message", property); //$NON-NLS-1$
    }

    /**
     * TODO:
     *
     * @param integer TODO:
     * @param size TODO:
     *
     * @return TODO:
     */
    private static String minimumSize(final int integer, final int size)
    {
        String string = Integer.toString(integer);

        if (string.length() < size)
        {
            StringBuffer buffer = new StringBuffer();

            for (int i = 0; i < (size - string.length()); i++)
            {
                buffer.append('0');
            }

            return buffer.append(string).toString();
        }
        else
        {
            return string;
        }
    }
}
