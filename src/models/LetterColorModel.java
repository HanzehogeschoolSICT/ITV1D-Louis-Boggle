package models;

import javafx.scene.paint.Color;

public class LetterColorModel {
    private final Color backgroundColor;
    private final Color foregroundColor;

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

    /**
     * Get the background color.
     *
     * @return Background color.
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Get the foreground color.
     *
     * @return Foreground color.
     */
    public Color getForegroundColor() {
        return foregroundColor;
    }
}
