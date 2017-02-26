package displays;

import controllers.BoardController;
import models.MatchModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

class BottomControlsDisplay extends JPanel {
    private BoardController boardController;

    BottomControlsDisplay(BoardController boardController) {
        this.boardController = boardController;

        initializeMatches();
    }

    private void initializeMatches() {
        JLabel matchesLabel = new JLabel("Matched words:");
        add(matchesLabel);

        JComboBox<MatchModel> matchesComboBox = new JComboBox<>();
        matchesComboBox.addActionListener(this::matchSelectedHandler);
        add(matchesComboBox);
    }

    private void matchSelectedHandler(ActionEvent actionEvent) {

    }
}
