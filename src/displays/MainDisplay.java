package displays;

import controllers.BoardController;
import data.Log;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javax.swing.*;
import java.awt.*;

public class MainDisplay {
    /**
     * Initialize the main display.
     */
    public MainDisplay() {
//        setTitle("Boggle");
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setResizable(false);
//
//        Container container = getContentPane();
//        setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
//
//        initializeViews(boardController);
//        setSystemLookAndFeel();
//
//        boardController.setAdjustSizeHandler(this::adjustSizeHandler);
//        boardController.adjustSize();
//        setVisible(true);
//
//        if (!boardController.hasWords())
//            showNoWordsMessage();
    }

    /**
     * Show a message indicating that no words have been loaded.
     */
    private void showNoWordsMessage() {
        String message = String.format("No words have been loaded. Please check the console output.%n" +
                "By default, words.txt in the working directory of this program is loaded.%n" +
                "To use a specific file, start this program using the --words option.%n" +
                "Example: java -jar Boggle.jar --words C:\\words.txt");

        new Alert(Alert.AlertType.WARNING, message, ButtonType.OK).show();
    }
}
