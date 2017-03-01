package models;

import javafx.scene.paint.Color;

public class LetterColorModel {
    public final Color backgroundColor;
    public final Color foregroundColor;

    /**
     * Initialize the letter color model.
     *
     * @param backgroundColor Background color to use.
     * @param foregroundColor Foreground color to use.
     */
    public LetterColorModel(Color backgroundColor, Color foregroundColor) {
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }
}
