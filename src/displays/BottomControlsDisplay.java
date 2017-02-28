package displays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import models.MatchModel;

import java.util.List;

public class BottomControlsDisplay {
    @FXML
    private ComboBox<MatchModel> matchesComboBox;

    /**
     * Handle a match selection.
     *
     * @param actionEvent Event for the selection.
     */
    @FXML
    private void matchSelectedHandler(ActionEvent actionEvent) {
        ComboBox matchesComboBox = (ComboBox) actionEvent.getSource();
        MatchModel match = (MatchModel) matchesComboBox.getSelectionModel().getSelectedItem();


    }

    /**
     * Handle an update of found matches.
     *
     * @param matches List of found matches.
     */
    private void updateMatchesHandler(List<MatchModel> matches) {

    }
}
