package displays;

import data.DataManager;
import data.Settings;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import models.BoardModel;
import models.MatchModel;
import services.SolveService;

import java.util.List;
import java.util.Set;

public class TopControlsDisplay {
    @FXML
    private Spinner<Integer> boardSizeSpinner;
    @FXML
    private Button newBoardButton;
    @FXML
    private Button solveBoardButton;

    /**
     * Initialize the top controls display.
     */
    @FXML
    public void initialize() {
        SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(Settings.BOARD_MINIMUM_SIZE,
                        Settings.BOARD_MAXIMUM_SIZE, Settings.BOARD_SIZE, Settings.BOARD_STEP_SIZE);

        boardSizeSpinner.setValueFactory(spinnerValueFactory);
    }

    /**
     * Handle a new board button action.
     *
     * @param actionEvent Event for the action.
     */
    @FXML
    private void onNewBoardButtonAction(ActionEvent actionEvent) {
        solveBoardButton.setDisable(false);

        int boardSize = boardSizeSpinner.getValue();
        BoardModel boardModel = new BoardModel(boardSize);

        Property<BoardModel> boardProperty = DataManager.getBoardProperty();
        boardProperty.setValue(boardModel);

        ObservableList<MatchModel> matchList = DataManager.getMatchList();
        matchList.clear();
    }

    /**
     * Handle a solve board button action.
     *
     * @param actionEvent Event for the action.
     */
    @FXML
    private void onSolveBoardButtonAction(ActionEvent actionEvent) {
        setIsSolving(true);

        Property<BoardModel> boardProperty = DataManager.getBoardProperty();
        BoardModel board = boardProperty.getValue();

        Set<String> words = DataManager.getWords();

        SolveService solveService = new SolveService(board, words);
        solveService.setOnSucceeded(event -> {
            List<MatchModel> matches = solveService.getValue();

            ObservableList<MatchModel> matchList = DataManager.getMatchList();
            matchList.setAll(matches);
            setIsSolving(false);
        });

        solveService.start();
    }

    /**
     * Change the state of the controls to prevent the user from performing actions while the board is being solved.
     *
     * @param isSolving True if the board will be solved, false if it has been solved.
     */
    private void setIsSolving(boolean isSolving) {
        boardSizeSpinner.setDisable(isSolving);
        newBoardButton.setDisable(isSolving);

        // The board can only be solved once,
        // so permanently disable the solve button.
        solveBoardButton.setDisable(true);
    }
}
