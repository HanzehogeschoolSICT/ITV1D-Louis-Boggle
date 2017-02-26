package displays;

import controllers.BoardController;
import data.Log;

import javax.swing.*;
import java.awt.*;

public class MainDisplay extends JFrame {
    /**
     * Initialize the main display.
     *
     * @param boardController Board controller to use.
     */
    public MainDisplay(BoardController boardController) {
        setTitle("Boggle");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Container container = getContentPane();
        setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        initializeViews(boardController);
        setSystemLookAndFeel();

        boardController.setAdjustSizeHandler(this::adjustSizeHandler);
        boardController.adjustSize();
        setVisible(true);

        if (!boardController.hasWords())
            showNoWordsMessage();
    }

    /**
     * Initialize all the sub views for the display.
     *
     * @param boardController Board controller to use.
     */
    private void initializeViews(BoardController boardController) {
        TopControlsDisplay topControlsDisplay = new TopControlsDisplay(boardController);
        add(topControlsDisplay);

        BoardDisplay boardDisplay = new BoardDisplay(boardController);
        add(boardDisplay);

        BottomControlsDisplay bottomControlsDisplay = new BottomControlsDisplay(boardController);
        add(bottomControlsDisplay);
    }

    /**
     * Try to set the system look and feel.
     */
    private void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception exception) {
            Log.error("Failed to apply system look and feel");
        }
    }

    /**
     * Show a message indicating that no words have been loaded.
     */
    private void showNoWordsMessage() {
        String message = String.format("No words have been loaded. Please check the console output.%n" +
                "By default, words.txt in the working directory of this program is loaded.%n" +
                "To use a specific file, start this program using the --words option.%n" +
                "Example: java -jar Boggle.jar --words C:\\words.txt");

        JOptionPane.showOptionDialog(this, message, "No words loaded",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
    }

    /**
     * Handle control size changes.
     */
    private void adjustSizeHandler() {
        pack();
        // Show frame in the center of the screen.
        setLocationRelativeTo(null);
    }
}
