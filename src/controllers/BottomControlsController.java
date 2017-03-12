package controllers;

import data.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import models.MatchModel;

/**
 * Controller for the bottom controls.
 */
public class BottomControlsController {
    @FXML
    private Label matchedWordsLabel;
    @FXML
    private ComboBox<MatchModel> matchesComboBox;

    @FXML
    private Label noMatchedWordsLabel;
    @FXML
    private ComboBox<String> noMatchesComboBox;

    /**
     * Initialize the bottom controls display.
     */
    @FXML
    public void initialize() {
        ObservableList<MatchModel> matchList = DataManager.getMatchList();
        matchList.addListener((ListChangeListener<MatchModel>) c -> onMatchListChanged());
        matchesComboBox.setItems(matchList);

        // Placeholder combo box to show if there are no matches to prevent showing the
        // previous matches (which stays visible when the observable list is cleared for some reason).
        ObservableList<String> noMatchList = FXCollections.observableArrayList("None");
        noMatchesComboBox.setItems(noMatchList);
        SelectionModel<String> selectionModel = noMatchesComboBox.getSelectionModel();
        selectionModel.selectFirst();
    }

    /**
     * Handle a matches combo box action.
     *
     * @param actionEvent Event for the action.
     */
    @FXML
    private void onMatchesComboBoxAction(ActionEvent actionEvent) {
        MatchModel match = matchesComboBox.getSelectionModel().getSelectedItem();
        DataManager.setMatch(match);
    }

    /**
     * Handle a change in the mast list.
     */
    private void onMatchListChanged() {
        ObservableList<MatchModel> matchList = matchesComboBox.getItems();

        boolean hasMatches = matchList.size() != 0;

        matchedWordsLabel.setVisible(hasMatches);
        matchedWordsLabel.setManaged(hasMatches);

        noMatchedWordsLabel.setVisible(!hasMatches);
        noMatchedWordsLabel.setManaged(!hasMatches);

        if (!hasMatches)
            return;

        // Select the first item to make sure there's always a displayed solution.
        SelectionModel<MatchModel> selectionModel = matchesComboBox.getSelectionModel();
        selectionModel.selectFirst();
    }
}
