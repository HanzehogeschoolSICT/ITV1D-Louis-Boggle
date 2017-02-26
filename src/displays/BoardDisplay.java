package displays;

import controllers.BoardController;
import data.Settings;
import models.BoardModel;
import models.MatchModel;
import models.PointModel;

import javax.swing.*;
import java.awt.*;

class BoardDisplay extends JPanel {
    private BoardModel board;
    private MatchModel displayMatch;

    /**
     * Initialize the board display.
     *
     * @param boardController Board controller to use.
     */
    BoardDisplay(BoardController boardController) {
        boardController.setSetDisplayMatchHandler(this::setDisplayMatchHandler);
        boardController.setUpdateBoardDisplayHandler(this::updateBoardDisplayHandler);
    }

    /**
     * Get the preferred size for the board display.
     *
     * @return Preferred size for the board display.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Settings.BOARD_DISPLAY_SIZE, Settings.BOARD_DISPLAY_SIZE);
    }

    /**
     * Handle updating the board display.
     *
     * @param board Board to display.
     */
    private void updateBoardDisplayHandler(BoardModel board) {
        this.board = board;
        repaint();
    }

    /**
     * Handle setting the match to display.
     *
     * @param displayMatch Match to display.
     */
    private void setDisplayMatchHandler(MatchModel displayMatch) {
        this.displayMatch = displayMatch;
    }

    /**
     * Draw the board using the given graphics.
     *
     * @param graphics Graphics to draw the board on.
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (board == null)
            return;

        int boardSize = board.size();
        int letterSize = Settings.BOARD_DISPLAY_SIZE / boardSize;

        java.util.List<PointModel> allPoints = board.getAllPoints();
        for (PointModel point : allPoints)
            drawPoint(graphics, point, letterSize);
    }

    /**
     * Draw a single point using the given graphics.
     *
     * @param graphics   Graphics to draw the point on.
     * @param point      Point to draw.
     * @param letterSize Letter size per point.
     */
    private void drawPoint(Graphics graphics, PointModel point, int letterSize) {
        boolean isMatch = displayMatch != null && displayMatch.hasPoint(point);
        Color backgroundColor = isMatch ? Settings.LETTER_MATCH_BACKGROUND_COLOR : Settings.LETTER_BACKGROUND_COLOR;
        Color foregroundColor = isMatch ? Settings.LETTER_MATCH_FOREGROUND_COLOR : Settings.LETTER_FOREGROUND_COLOR;

        graphics.setColor(backgroundColor);
        graphics.fillRect(point.x * letterSize, point.y * letterSize, letterSize, letterSize);

        graphics.setColor(foregroundColor);
        drawLetterInPoint(graphics, point, letterSize);
    }

    /**
     * Draw a letter in a point using the given graphics.
     *
     * @param graphics   Graphics to draw the point on.
     * @param point      Point to draw.
     * @param letterSize Letter size per point.
     */
    private void drawLetterInPoint(Graphics graphics, PointModel point, int letterSize) {
        String letter = ((Character) board.getLetter(point)).toString();
        FontMetrics fontMetrics = graphics.getFontMetrics();

        int x = point.x * letterSize + ((letterSize - fontMetrics.stringWidth(letter)) / 2);
        int y = point.y * letterSize + ((letterSize + fontMetrics.getHeight() / 2) / 2);

        graphics.drawString(letter, x, y);
    }
}
