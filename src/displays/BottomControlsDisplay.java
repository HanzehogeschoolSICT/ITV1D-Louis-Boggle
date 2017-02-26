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

    BottomControlsDisplay(BoardController boardController) {
        this.boardController = boardController;
        boardController.setUpdateMatchesHandler(this::updateMatchesHandler);

        initializeMatches();
    }

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

    private void checkForMatches() {
        boolean hasItems = matchesComboBox.getItemCount() > 0;

        matchesComboBox.setVisible(hasItems);
        noMatchesComboBox.setVisible(!hasItems);
    }

    private void matchSelectedHandler(ActionEvent actionEvent) {
        JComboBox matchesComboBox = (JComboBox) actionEvent.getSource();
        MatchModel match = (MatchModel) matchesComboBox.getSelectedItem();

        boardController.displayMatch(match);
    }

    private void updateMatchesHandler(List<MatchModel> matches) {
        matchesComboBox.removeAllItems();

        for (MatchModel match : matches)
            matchesComboBox.addItem(match);

        checkForMatches();
    }
}
