package displays;

import controllers.BoardController;
import data.Settings;
import models.BoardModel;
import models.MatchModel;

import javax.swing.*;
import java.awt.*;

class BoardDisplay extends JPanel {
    private BoardController boardController;
    private MatchModel displayMatch;

    BoardDisplay(BoardController boardController) {
        this.boardController = boardController;
        boardController.setSetDisplayMatchHandler(this::setDisplayMatchHandler);
        boardController.setUpdateBoardDisplayHandler(this::updateBoardDisplayHandler);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Settings.BOARD_DISPLAY_SIZE, Settings.BOARD_DISPLAY_SIZE);
    }

    private void updateBoardDisplayHandler(BoardModel board) {

    }

    private void setDisplayMatchHandler(MatchModel displayMatch) {
        this.displayMatch = displayMatch;
    }
}
