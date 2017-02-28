package models;

import javafx.scene.paint.Color;

public class LetterColorModel {
    public final Color backgroundColor;
    public final Color foregroundColor;

    public LetterColorModel(Color backgroundColor, Color foregroundColor) {
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }
}
