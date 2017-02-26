package displays;

import controllers.BoardController;
import data.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;

class TopControlsDisplay extends JPanel {
    private final BoardController boardController;
    private JSpinner boardSizeSpinner;

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

        JButton newBoardButton = new JButton("New Board");
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
    }

    /**
     * Initialize controls for solving boards.
     */
    private void initializeSolveBoard() {
        JButton solveBoardButton = new JButton("Solve Board");
        solveBoardButton.addActionListener(this::solveBoardHandler);
        add(solveBoardButton);
    }

    /**
     * Handle board solving request.
     *
     * @param actionEvent Event for the request.
     */
    private void solveBoardHandler(ActionEvent actionEvent) {
        boardController.solveBoard();
    }
}
