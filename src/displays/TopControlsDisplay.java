package displays;

import data.DataManager;
import data.Settings;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    public void initialize() {
        SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(Settings.BOARD_MINIMUM_SIZE,
                        Settings.BOARD_MAXIMUM_SIZE, Settings.BOARD_SIZE, Settings.BOARD_STEP_SIZE);

        boardSizeSpinner.setValueFactory(spinnerValueFactory);
    }

    /**
     * Handle new board creation request.
     *
     * @param actionEvent Event for the request.
     */
    @FXML
    private void newBoardHandler(ActionEvent actionEvent) {
        int boardSize = boardSizeSpinner.getValue();
        BoardModel boardModel = new BoardModel(boardSize);

        Property<BoardModel> boardProperty = DataManager.getBoardProperty();
        boardProperty.setValue(boardModel);

        ObservableList<MatchModel> matchList = DataManager.getMatchList();
        matchList.clear();
    }

    /**
     * Handle board solving request.
     *
     * @param actionEvent Event for the request.
     */
    @FXML
    private void solveBoardHandler(ActionEvent actionEvent) {
        Property<BoardModel> boardProperty = DataManager.getBoardProperty();
        BoardModel board = boardProperty.getValue();

        Set<String> words = DataManager.getWords();
        List<MatchModel> matches = new SolveService(board, words).getMatches();

        ObservableList<MatchModel> matchList = DataManager.getMatchList();
        matchList.setAll(matches);
    }
}
