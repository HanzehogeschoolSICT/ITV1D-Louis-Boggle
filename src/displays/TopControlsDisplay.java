package displays;

import controllers.BoardController;
import data.Settings;
import workers.DisplaySolveWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;

class TopControlsDisplay extends JPanel {
    private final BoardController boardController;
    private JSpinner boardSizeSpinner;
    private JButton newBoardButton;
    private JButton solveBoardButton;

    /**
     * Initialize the top controls display.
     *
     * @param boardController Board controller to use.
     */
    TopControlsDisplay(BoardController boardController) {
        this.boardController = boardController;

        initializeNewBoard();
        initializeSolveBoard();
    }

    /**
     * Initialize controls for creating new boards.
     */
    private void initializeNewBoard() {
        JLabel boardSizeLabel = new JLabel("Board size:");
        add(boardSizeLabel);

        SpinnerNumberModel boardSizeModel = new SpinnerNumberModel(Settings.BOARD_SIZE,
                Settings.BOARD_MINIMUM_SIZE, Settings.BOARD_MAXIMUM_SIZE, Settings.BOARD_STEP_SIZE);
        boardSizeSpinner = new JSpinner(boardSizeModel);
        add(boardSizeSpinner);

        newBoardButton = new JButton("New Board");
        newBoardButton.addActionListener(this::newBoardHandler);
        add(newBoardButton);
    }

    /**
     * Handle new board creation request.
     *
     * @param actionEvent Event for the request.
     */
    private void newBoardHandler(ActionEvent actionEvent) {
        int boardSize = (int) boardSizeSpinner.getValue();
        boardController.setBoard(boardSize);
        solveBoardButton.setEnabled(true);
    }

    /**
     * Initialize controls for solving boards.
     */
    private void initializeSolveBoard() {
        solveBoardButton = new JButton("Solve Board");
        solveBoardButton.addActionListener(this::solveBoardHandler);
        solveBoardButton.setEnabled(false);
        add(solveBoardButton);
    }

    /**
     * Handle board solving request.
     *
     * @param actionEvent Event for the request.
     */
    private void solveBoardHandler(ActionEvent actionEvent) {
        DisplaySolveWorker displaySolveWorker = new DisplaySolveWorker(boardController,
                boardSizeSpinner, newBoardButton);

        // The board only has to be solved once.
        solveBoardButton.setEnabled(false);

        displaySolveWorker.start();
    }
}
