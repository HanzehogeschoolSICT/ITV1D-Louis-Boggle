package displays;

import data.DataManager;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import models.MatchModel;

public class BottomControlsDisplay {
    @FXML
    private ComboBox<MatchModel> matchesComboBox;

    @FXML
    public void initialize() {
        ObservableList<MatchModel> matchList = DataManager.getMatchList();
        matchesComboBox.setItems(matchList);
    }

    /**
     * Handle a match selection.
     *
     * @param actionEvent Event for the selection.
     */
    @FXML
    private void matchSelectedHandler(ActionEvent actionEvent) {
        MatchModel match = matchesComboBox.getSelectionModel().getSelectedItem();

        Property<MatchModel> matchProperty = DataManager.getMatchProperty();
        matchProperty.setValue(match);
    }
}
