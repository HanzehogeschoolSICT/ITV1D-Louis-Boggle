package displays;

import data.Settings;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;
import models.BoardModel;
import models.LetterColorModel;
import models.MatchModel;
import models.PointModel;

import java.util.List;

public class BoardDisplay {
    @FXML
    private Canvas boardCanvas;

    private BoardModel board;
    private MatchModel match;

    private GraphicsContext graphics;

    @FXML
    public void initialize() {
        graphics = boardCanvas.getGraphicsContext2D();
    }

    public void updateBoard(BoardModel board, MatchModel match) {
        this.board = board;
        this.match = match;

        drawBoard();
    }

    private void drawBoard() {
        clear();

        if (board == null)
            return;

        int boardSize = board.size();
        double letterSize = boardCanvas.getHeight() / boardSize;

        List<PointModel> allPoints = board.getAllPoints();
        for (PointModel point : allPoints)
            drawPoint(match, point, letterSize);
    }

    private void clear() {
        double width = boardCanvas.getWidth();
        double height = boardCanvas.getHeight();

        graphics.clearRect(0, 0, width, height);
    }

    /**
     * Draw a single point using the given graphics.
     *
     * @param point      Point to draw.
     * @param letterSize Letter size per point.
     */
    private void drawPoint(MatchModel match, PointModel point, double letterSize) {
        LetterColorModel letterColor = getLetterColor(match, point);

        graphics.setFill(letterColor.backgroundColor);
        graphics.fillRect(point.x * letterSize, point.y * letterSize, letterSize, letterSize);

        graphics.setFill(letterColor.foregroundColor);
        drawLetterInPoint(point, letterSize);
    }

    /**
     * Get the background and foreground color to draw the letter with.
     *
     * @param point Point to get the color for.
     * @return Background and foreground color to use.
     */
    private LetterColorModel getLetterColor(MatchModel match, PointModel point) {
        if (match == null || !match.hasPoint(point))
            return Settings.LETTER_COLOR;

        return match.isStartPoint(point) ?
                Settings.LETTER_START_COLOR : Settings.LETTER_MATCH_COLOR;
    }

    /**
     * Draw a letter in a point.
     *
     * @param point      Point to draw.
     * @param letterSize Letter size per point.
     */
    private void drawLetterInPoint(PointModel point, double letterSize) {
        graphics.setTextAlign(TextAlignment.CENTER);
        graphics.setTextBaseline(VPos.CENTER);

        double centerX = point.x * letterSize + (letterSize / 2);
        double centerY = point.y * letterSize + (letterSize / 2);

        String letter = ((Character) board.getLetter(point)).toString();
        graphics.fillText(letter, centerX, centerY);
    }
}
