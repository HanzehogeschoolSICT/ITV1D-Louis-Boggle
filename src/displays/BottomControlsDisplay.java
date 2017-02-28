package displays;

import data.DataManager;
import javafx.beans.property.Property;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionModel;
import models.MatchModel;

public class BottomControlsDisplay {
    @FXML
    private ComboBox<MatchModel> matchesComboBox;

    @FXML
    public void initialize() {
        ObservableList<MatchModel> matchList = DataManager.getMatchList();
        matchList.addListener((ListChangeListener<MatchModel>) c -> matchListChanged());
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

    private void matchListChanged() {
        ObservableList<MatchModel> matchList = matchesComboBox.getItems();

        boolean hasMatches = matchList.size() != 0;
        matchesComboBox.setDisable(!hasMatches);

        if (!hasMatches)
            return;

        SelectionModel<MatchModel> selectionModel = matchesComboBox.getSelectionModel();
        selectionModel.selectFirst();
    }
}
