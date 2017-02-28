package displays;

import data.Log;
import data.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class TopControlsDisplay {
    @FXML
    private Spinner boardSizeSpinner;

    /**
     * Initialize the top controls display.
     */
    public TopControlsDisplay() {
        SpinnerValueFactory spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
                Settings.BOARD_MINIMUM_SIZE, Settings.BOARD_MAXIMUM_SIZE,
                Settings.BOARD_SIZE, Settings.BOARD_STEP_SIZE);

        //boardSizeSpinner.setValueFactory(spinnerValueFactory);
    }

    /**
     * Handle new board creation request.
     *
     * @param actionEvent Event for the request.
     */
    @FXML
    private void newBoardHandler(ActionEvent actionEvent) {
        Spinner boardSizeSpinner = (Spinner) actionEvent.getSource();
        int boardSize = (int)boardSizeSpinner.getValue();
        Log.info("Board size: %d", boardSize);
    }

    /**
     * Handle board solving request.
     *
     * @param actionEvent Event for the request.
     */
    @FXML
    private void solveBoardHandler(ActionEvent actionEvent) {
        Log.info("Solve board");
    }
}
