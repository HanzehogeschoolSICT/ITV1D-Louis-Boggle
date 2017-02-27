package data;

import models.LetterColorModel;

import java.awt.*;

public class Settings {
    public static final int BOARD_SIZE = 16;
    public static final int BOARD_MINIMUM_SIZE = 3;
    public static final int BOARD_MAXIMUM_SIZE = 16;
    public static final int BOARD_STEP_SIZE = 1;

    public static final int BOARD_DISPLAY_SIZE = 400;

    public static final LetterColorModel LETTER_COLOR = new LetterColorModel(
            new Color(33, 150, 243), new Color(255, 255, 255));
    public static final LetterColorModel LETTER_MATCH_COLOR = new LetterColorModel(
            new Color(0, 150, 136), new Color(255, 255, 255));
    public static final LetterColorModel LETTER_START_COLOR = new LetterColorModel(
            new Color(0,121,107), new Color(255, 255, 255));
}
