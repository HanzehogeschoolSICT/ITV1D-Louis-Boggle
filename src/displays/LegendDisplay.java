package displays;

import data.Settings;
import models.LetterColorModel;

import javax.swing.*;
import java.awt.*;

class LegendDisplay extends JPanel {
    private final int legendColorSize;

    /**
     * Initialize the legend display.
     */
    LegendDisplay() {
        Font currentFont = getFont();
        FontMetrics fontMetrics = getFontMetrics(currentFont);
        legendColorSize = fontMetrics.getHeight();

        addLegendColor(Settings.LETTER_COLOR, "No Match");
        addLegendColor(Settings.LETTER_MATCH_COLOR, "Match");
        addLegendColor(Settings.LETTER_START_COLOR, "Match Start");
    }

    /**
     * Add a legend color to the legend.
     *
     * @param letterColorModel Color to use for the legend color.
     * @param name             Name to display for the legend color.
     */
    private void addLegendColor(LetterColorModel letterColorModel, String name) {
        LegendColorDisplay legendColorDisplay = new LegendColorDisplay(letterColorModel, legendColorSize);
        JLabel letterColorSeparator = new JLabel("=");
        JLabel letterColorLabel = new JLabel(name);

        add(legendColorDisplay);
        add(letterColorSeparator);
        add(letterColorLabel);
    }
}
