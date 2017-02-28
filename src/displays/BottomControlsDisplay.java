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

    /**
     * Initialize the bottom controls display.
     */
    @FXML
    public void initialize() {
        ObservableList<MatchModel> matchList = DataManager.getMatchList();
        matchList.addListener((ListChangeListener<MatchModel>) c -> onMatchListChanged());
        matchesComboBox.setItems(matchList);
    }

    /**
     * Handle a matches combo box action.
     *
     * @param actionEvent Event for the action.
     */
    @FXML
    private void onMatchesComboBoxAction(ActionEvent actionEvent) {
        MatchModel match = matchesComboBox.getSelectionModel().getSelectedItem();

        Property<MatchModel> matchProperty = DataManager.getMatchProperty();
        matchProperty.setValue(match);
    }

    /**
     * Handle a change in the mast list.
     */
    private void onMatchListChanged() {
        ObservableList<MatchModel> matchList = matchesComboBox.getItems();

        boolean hasMatches = matchList.size() != 0;
        matchesComboBox.setDisable(!hasMatches);

        if (!hasMatches)
            return;

        // Select the first item to make sure there's always a displayed solution.
        SelectionModel<MatchModel> selectionModel = matchesComboBox.getSelectionModel();
        selectionModel.selectFirst();
    }
}
