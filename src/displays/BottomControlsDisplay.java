package displays;

import controllers.BoardController;
import models.MatchModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

class BottomControlsDisplay extends JPanel {
    private final BoardController boardController;
    private JComboBox<MatchModel> matchesComboBox;
    private JComboBox<String> noMatchesComboBox;

    /**
     * Initialize the bottom controls display.
     *
     * @param boardController Board controller to use.
     */
    BottomControlsDisplay(BoardController boardController) {
        this.boardController = boardController;
        boardController.setUpdateMatchesHandler(this::updateMatchesHandler);

        initializeMatches();
    }

    /**
     * Initialize the controls showing the matches.
     */
    private void initializeMatches() {
        JLabel matchesLabel = new JLabel("Matched words:");
        add(matchesLabel);

        matchesComboBox = new JComboBox<>();
        matchesComboBox.addActionListener(this::matchSelectedHandler);
        add(matchesComboBox);

        noMatchesComboBox = new JComboBox<>();
        noMatchesComboBox.addItem("None");
        noMatchesComboBox.setEnabled(false);
        add(noMatchesComboBox);

        checkForMatches();
    }

    /**
     * Check if any matches have been found and adjust control accordingly.
     */
    private void checkForMatches() {
        boolean hasItems = matchesComboBox.getItemCount() > 0;

        matchesComboBox.setVisible(hasItems);
        noMatchesComboBox.setVisible(!hasItems);
    }

    /**
     * Handle a match selection.
     *
     * @param actionEvent Event for the selection.
     */
    private void matchSelectedHandler(ActionEvent actionEvent) {
        JComboBox matchesComboBox = (JComboBox) actionEvent.getSource();
        MatchModel match = (MatchModel) matchesComboBox.getSelectedItem();

        boardController.displayMatch(match);
    }

    /**
     * Handle an update of found matches.
     *
     * @param matches List of found matches.
     */
    private void updateMatchesHandler(List<MatchModel> matches) {
        matchesComboBox.removeAllItems();

        for (MatchModel match : matches)
            matchesComboBox.addItem(match);

        checkForMatches();
    }
}
