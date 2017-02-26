package displays;

import controllers.BoardController;
import models.MatchModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

class BottomControlsDisplay extends JPanel {
    private final BoardController boardController;
    private JComboBox<MatchModel> matchesComboBox;

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
    }
}
