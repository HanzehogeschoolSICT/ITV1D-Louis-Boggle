package displays;

import data.Settings;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import models.LetterColorModel;

public class LegendDisplay {
    @FXML
    private Canvas noMatchColorCanvas;
    @FXML
    private Canvas matchColorCanvas;
    @FXML
    private Canvas matchStartColorCanvas;

    /**
     * Initialize the legend display.
     */
    @FXML
    public void initialize() {
        drawLegendColor(noMatchColorCanvas, Settings.LETTER_COLOR);
        drawLegendColor(matchColorCanvas, Settings.LETTER_MATCH_COLOR);
        drawLegendColor(matchStartColorCanvas, Settings.LETTER_START_COLOR);
    }

    private void drawLegendColor(Canvas canvas, LetterColorModel color) {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        graphicsContext.setFill(color.backgroundColor);
        graphicsContext.setStroke(color.backgroundColor.darker());

        double width = canvas.getWidth();
        double height = canvas.getHeight();

        graphicsContext.fillRect(0, 0, width, height);
        graphicsContext.strokeRect(0, 0, width - 1, height -1);
    }
}
