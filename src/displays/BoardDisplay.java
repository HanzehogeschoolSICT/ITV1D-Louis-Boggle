package displays;

import controllers.BoardController;
import data.Settings;

import javax.swing.*;
import java.awt.*;

class BoardDisplay extends JPanel {
    private BoardController boardController;

   BoardDisplay(BoardController boardController) {
       this.boardController = boardController;
   }

   @Override
   public Dimension getPreferredSize() {
       return new Dimension(Settings.BOARD_DISPLAY_SIZE, Settings.BOARD_DISPLAY_SIZE);
   }
}
