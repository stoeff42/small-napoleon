package ch.cs.napoleon.gui;

import ch.cs.napoleon.score.ScoreEntries;
import ch.cs.napoleon.score.ScoreEntry;

import waba.fx.FontMetrics;

import waba.sys.Convert;
import waba.sys.Settings;

import waba.ui.Button;
import waba.ui.Container;
import waba.ui.Control;
import waba.ui.ControlEvent;
import waba.ui.Event;
import waba.ui.InputDialog;
import waba.ui.Label;
import waba.ui.MessageBox;
import waba.ui.Window;


/**
 * TODO: javadoc
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class Dialogs
{
    //~ Methods ------------------------------------------------------

    /**
     * TODO: javadoc
     *
     * @param window TODO: javadoc
     * @param defaultName TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public static String askName(Window window, String defaultName)
    {
        showMessageBox(window, "Finished", "Got it!", MessageBox.LEFT);
        InputDialog inputDialog =
            new InputDialog("High score!",
                wrap("Enter your name : ", window), defaultName);
        window.popupBlockingModal(inputDialog);
        if (inputDialog.getPressedButtonIndex() == 0)
        {
            return inputDialog.getValue();
        }
        else
        {
            return null;
        }
    }

    /**
     * TODO: javadoc
     *
     * @param time TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public static String makeDurationString(int time)
    {
        time = time / 1000;
        int secs = time % 60;
        time = (time - secs) / 60;
        int mins = time % 60;
        int hours = (time - mins) / 60;
        StringBuffer buffer = new StringBuffer();
        if (hours > 0)
        {
            buffer.append(zeroPad(hours, 2)).append(':');
        }
        buffer.append(zeroPad(mins, 2)).append(':');
        buffer.append(zeroPad(secs, 2));
        return buffer.toString();
    }

    /**
     * TODO: javadoc
     *
     * @param window TODO: javadoc
     */
    public static void showAbout(Window window)
    {
        showMessageBox(window, "About",
            "Little Napoleon|by Christoph Schilling",
            MessageBox.CENTER);
    }

    /**
     * TODO: javadoc
     *
     * @param window TODO: javadoc
     */
    public static void showRules(Window window)
    {
        showMessageBox(window, "Rules",
            "Little Napoleon||Build four 13-card foundations in suit on the four cards in the middle. For each, you can decide whether you want to build upwards or downwards.|The tableau is made up of the four cards you start the foundations from, two five-card rows on each side of these, and two four-card rows at the bottom.|From the upper four rows, you can only move the left and right outermost cards. You may build sequences in suit to any of these outermost cards, upwards or downwards.|You cannot move whole sequences, you have to find a matching card to move one by one.|The eight cards on the bottom, you can only move away, you cannot fill the places anymore once you moved them.|Finally, there is one cellar place on the bottom in the middle, where you can store one card at a time, when there is a card that is definitely in the way.||If you play 'perfect', you may win one out of five games...||Good luck!",
            MessageBox.LEFT);
    }

    /**
     * TODO: javadoc
     *
     * @param window TODO: javadoc
     * @param scoreEntries TODO: javadoc
     */
    public static void showScores(Window window,
        final ScoreEntries scoreEntries)
    {
        if (scoreEntries.getNrOfEntries() == 0)
        {
            showNoScores(window);
            return;
        }

        final Button okButton = new Button("Ok");
        final Button clearButton = new Button("Clear");
        final Window dialog =
            new Window()
            {
                public void onEvent(Event event)
                {
                    if (event.type == ControlEvent.PRESSED)
                    {
                        if (event.target == okButton)
                        {
                            unpop();
                        }
                        if (event.target == clearButton)
                        {
                            clearScores(this, scoreEntries);
                            if (scoreEntries.getNrOfEntries() == 0)
                            {
                                unpop();
                            }
                        }
                    }
                }
            };
        dialog.setTitle("Scores");
        dialog.setBorderStyle(Window.ROUND_BORDER);
        Container scoreContainer =
            getScoreContainer(dialog, scoreEntries);
        dialog.setRect(Control.CENTER, Control.CENTER,
            dialog.getSize().x - dialog.getClientRect().width
            + scoreContainer.getSize().x,
            dialog.getSize().y - dialog.getClientRect().height
            + scoreContainer.getSize().y
            + okButton.getPreferredHeight() + 1);
        dialog.add(scoreContainer);
        dialog.add(okButton, Control.LEFT, Control.BOTTOM);
        dialog.add(clearButton, Control.RIGHT, Control.BOTTOM);
        window.popupBlockingModal(dialog);
    }

    /**
     * TODO: javadoc
     *
     * @param window TODO: javadoc
     * @param scoreEntries TODO: javadoc
     *
     * @return TODO: javadoc
     */
    private static Container getScoreContainer(Window window,
        final ScoreEntries scoreEntries)
    {
        Container scoreContainer = new Container();
        int columnCount = ScoreEntry.getHeaderNames().length;
        int rowCount = scoreEntries.getNrOfEntries();
        int[] widths = new int[columnCount];
        for (int i = 0; i < widths.length; i++)
        {
            widths[i] = 0;
        }
        int[] heights = new int[rowCount + 1];
        for (int i = 0; i < heights.length; i++)
        {
            heights[i] = 0;
        }
        for (int i = 0; i < columnCount; i++)
        {
            Label label = null;
            switch (i)
            {
                case (0) :
                    label =
                        new Label((String) ScoreEntry.getHeaderNames()[i],
                            Label.LEFT);
                    break;
                case (1) :
                    label =
                        new Label((String) ScoreEntry.getHeaderNames()[i],
                            Label.RIGHT);
                    break;
                case (2) :
                    label =
                        new Label((String) ScoreEntry.getHeaderNames()[i],
                            Label.RIGHT);
                    break;
            }
            label.setFont(label.getFont().asBold());
            updateWidthsAndHeights(widths, heights, i, 0, label);
            scoreContainer.add(label);
        }
        for (int i = 0; i < rowCount; i++)
        {
            ScoreEntry entry = scoreEntries.getEntry(i);
            Label label = new Label(
                    entry.getName(),
                    Label.LEFT);
            updateWidthsAndHeights(widths, heights, 0, i + 1, label);
            scoreContainer.add(label);
            label =
                new Label(
                    Convert.toString(entry.getNrOfMoves()),
                    Label.RIGHT);
            updateWidthsAndHeights(widths, heights, 1, i + 1, label);
            scoreContainer.add(label);
            label =
                new Label(
                    Dialogs.makeDurationString(entry.getTime()),
                    Label.RIGHT);
            updateWidthsAndHeights(widths, heights, 2, i + 1, label);
            scoreContainer.add(label);
        }
        Control[] controls = scoreContainer.getChildren();
        int height = 0;
        int width = 0;
        for (int j = 0; j < (rowCount + 1); j++)
        {
            width = 0;
            for (int i = 0; i < columnCount; i++)
            {
                controls[controls.length - (i + (j * columnCount))
                - 1].setRect(width, height, widths[i], heights[j]);
                width += (widths[i] + 1);
            }
            height += (heights[j] + 1);
        }
        scoreContainer.setRect(window.getClientRect().x,
            window.getClientRect().y, width, height);
        return scoreContainer;
    }

    /**
     * TODO: javadoc
     *
     * @param window TODO: javadoc
     * @param scoreEntries TODO: javadoc
     */
    private static void clearScores(Window window,
        ScoreEntries scoreEntries)
    {
        if (scoreEntries.getNrOfEntries() == 0)
        {
            showNoScores(window);
        }
        MessageBox messageBox =
            new MessageBox("Delete scores",
                wrap("Are you sure you want to delete the scores?",
                    window), new String[] {"Ok", "Cancel"});
        window.popupBlockingModal(messageBox);
        if (messageBox.getPressedButtonIndex() == 0)
        {
            scoreEntries.clear();
        }
    }

    /**
     * TODO: javadoc
     *
     * @param window TODO: javadoc
     * @param title TODO: javadoc
     * @param text TODO: javadoc
     * @param alignment TODO: javadoc
     */
    private static void showMessageBox(Window window, String title,
        String text, int alignment)
    {
        MessageBox messageBox =
            new MessageBox(title,
                wrap(text, window));
        messageBox.setTextAlignment(alignment);
        window.popupBlockingModal(messageBox);
    }

    /**
     * TODO: javadoc
     *
     * @param window TODO: javadoc
     */
    private static void showNoScores(Window window)
    {
        showMessageBox(window, "Scores", "No scores yet!",
            MessageBox.LEFT);
    }

    /**
     * TODO: javadoc
     *
     * @param widths TODO: javadoc
     * @param heights TODO: javadoc
     * @param i TODO: javadoc
     * @param j TODO: javadoc
     * @param label TODO: javadoc
     */
    private static void updateWidthsAndHeights(int[] widths,
        int[] heights, int i, int j, Label label)
    {
        widths[i] = Math.max(
                widths[i],
                label.getPreferredWidth());
        heights[j] = Math.max(
                heights[j],
                label.getPreferredHeight());
    }

    /**
     * TODO: javadoc
     *
     * @param text TODO: javadoc
     * @param window TODO: javadoc
     *
     * @return TODO: javadoc
     */
    private static String wrap(String text, Window window)
    {
        FontMetrics fontMetrics =
            window.getFontMetrics(window.getFont());
        StringBuffer wrap = new StringBuffer();
        String[] paragraphs = Convert.tokenizeString(text, '|');

        for (int i = 0; i < paragraphs.length; i++)
        {
            if (i != 0)
            {
                wrap.append('|');
            }

            wrap.append(
                Convert.insertLineBreak(Settings.screenWidth - 6,
                    '|', fontMetrics, paragraphs[i]));
        }

        return wrap.toString();
    }

    /**
     * TODO: javadoc
     *
     * @param integer TODO: javadoc
     * @param size TODO: javadoc
     *
     * @return TODO: javadoc
     */
    private static String zeroPad(int integer, int size)
    {
        String string = Convert.toString(integer);
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
;
