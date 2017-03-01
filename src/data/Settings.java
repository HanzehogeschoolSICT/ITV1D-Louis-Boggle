package data;

import javafx.scene.paint.Color;
import models.LetterColorModel;

public class Settings {
    public static final int BOARD_SIZE = 16;
    public static final int BOARD_MINIMUM_SIZE = 3;
    public static final int BOARD_MAXIMUM_SIZE = 16;
    public static final int BOARD_STEP_SIZE = 1;

    /**
     * Default color for a letter (Material design color).
     */
    public static final LetterColorModel LETTER_COLOR = new LetterColorModel(
            Color.web("#2196F3"), Color.web("#FFFFFF"));
    /**
     * Default color for a letter which is part of a match (Material design color).
     */
    public static final LetterColorModel LETTER_MATCH_COLOR = new LetterColorModel(
            Color.web("#009688"), Color.web("#FFFFFF"));
    /**
     * Default color for a letter which is the start of a match (Material design color).
     */
    public static final LetterColorModel LETTER_START_COLOR = new LetterColorModel(
            Color.web("#00796B"), Color.web("#FFFFFF"));
}
