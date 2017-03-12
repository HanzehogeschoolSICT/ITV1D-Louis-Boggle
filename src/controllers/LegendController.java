package controllers;

import data.Settings;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import models.LetterColorModel;

/**
 * Controller for the legend.
 */
public class LegendController {
    @FXML
    private Label noMatchColorLabel;
    @FXML
    private Canvas noMatchColorCanvas;

    @FXML
    private Label matchColorLabel;
    @FXML
    private Canvas matchColorCanvas;

    @FXML
    private Label matchStartColorLabel;
    @FXML
    private Canvas matchStartColorCanvas;

    /**
     * Initialize the legend display.
     */
    @FXML
    public void initialize() {
        syncColorWithLabel(noMatchColorLabel, noMatchColorCanvas, Settings.LETTER_COLOR);
        syncColorWithLabel(matchColorLabel, matchColorCanvas, Settings.LETTER_MATCH_COLOR);
        syncColorWithLabel(matchStartColorLabel, matchStartColorCanvas, Settings.LETTER_START_COLOR);
    }

    /**
     * Sync the size of the label's associated color canvas.
     *
     * @param label  Label to sync.
     * @param canvas Color canvas to sync.
     * @param color  Color to draw on the canvas.
     */
    private void syncColorWithLabel(Label label, Canvas canvas, LetterColorModel color) {
        // The colors are square, so height is the same value as width.
        label.heightProperty().addListener((observable, oldValue, newValue) ->
                drawLegendColor(canvas, color, newValue.doubleValue()));
    }

    /**
     * Draw a legend color on the given canvas.
     *
     * @param canvas Canvas to draw the legend color on.
     * @param color  Color to draw on the canvas.
     * @param size   Size of the legend color.
     */
    private void drawLegendColor(Canvas canvas, LetterColorModel color, double size) {
        canvas.setWidth(size);
        canvas.setHeight(size);

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        Color backgroundColor = color.getBackgroundColor();
        graphicsContext.setFill(backgroundColor);
        graphicsContext.setStroke(backgroundColor.darker());

        graphicsContext.fillRect(0, 0, size, size);
        graphicsContext.strokeRect(0, 0, size, size);
    }
}
