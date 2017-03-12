package controllers;

import data.DataManager;
import data.Settings;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.TextAlignment;
import models.BoardModel;
import models.LetterColorModel;
import models.MatchModel;
import models.PointModel;

import java.util.List;

/**
 * Controller for the board.
 */
public class BoardController {
    @FXML
    private Canvas boardCanvas;

    private BoardModel board;
    private MatchModel match;

    private GraphicsContext graphics;

    /**
     * Initialize the board controller.
     */
    @FXML
    public void initialize() {
        graphics = boardCanvas.getGraphicsContext2D();

        Property<BoardModel> boardProperty = DataManager.getBoardProperty();
        boardProperty.addListener((observable, oldValue, newValue) -> updateBoard(newValue, null));

        Property<MatchModel> matchProperty = DataManager.getMatchProperty();
        matchProperty.addListener((observable, oldValue, newValue) -> updateBoard(board, newValue));
    }

    /**
     * Update the board and display the updated board.
     *
     * @param board Board to update.
     * @param match Match to update.
     */
    private void updateBoard(BoardModel board, MatchModel match) {
        this.board = board;
        this.match = match;

        drawBoard();
    }

    /**
     * Draw the current board on the canvas.
     */
    private void drawBoard() {
        clear();

        if (board == null)
            return;

        int boardSize = board.size();
        // The board is square: height is the same as width.
        double letterSize = boardCanvas.getHeight() / boardSize;

        List<PointModel> allPoints = board.getAllPoints();
        for (PointModel point : allPoints)
            drawPoint(match, point, letterSize);
    }

    /**
     * Clear the canvas.
     */
    private void clear() {
        double width = boardCanvas.getWidth();
        double height = boardCanvas.getHeight();

        graphics.clearRect(0, 0, width, height);
    }

    /**
     * Draw a single point on the canvas.
     *
     * @param point      Point to draw.
     * @param letterSize Letter size per point.
     */
    private void drawPoint(MatchModel match, PointModel point, double letterSize) {
        LetterColorModel letterColor = getLetterColor(match, point);

        graphics.setFill(letterColor.getBackgroundColor());
        graphics.fillRect(point.getX() * letterSize, point.getY() * letterSize, letterSize, letterSize);

        graphics.setFill(letterColor.getForegroundColor());
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
     * @param point      Point to draw the letter in.
     * @param letterSize Letter size per point.
     */
    private void drawLetterInPoint(PointModel point, double letterSize) {
        // Results in a less blurry board when displaying large boards.
        graphics.setFontSmoothingType(FontSmoothingType.LCD);

        graphics.setTextAlign(TextAlignment.CENTER);
        graphics.setTextBaseline(VPos.CENTER);

        double centerX = point.getX() * letterSize + (letterSize / 2);
        double centerY = point.getY() * letterSize + (letterSize / 2);

        String letter = ((Character) board.getLetter(point)).toString();
        graphics.fillText(letter, centerX, centerY);
    }
}
