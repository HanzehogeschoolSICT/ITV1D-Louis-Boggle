package displays;

import models.LetterColorModel;

import javax.swing.*;
import java.awt.*;

class LegendColorDisplay extends JPanel {
    private final Color color;
    private final Dimension preferredSize;

    /**
     * Initialize the legend color display using the given color and size.
     *
     * @param letterColor Color to display in the legend.
     * @param size        Size of the color block to display.
     */
    LegendColorDisplay(LetterColorModel letterColor, int size) {
        color = letterColor.backgroundColor;
        preferredSize = new Dimension(size, size);
    }

    /**
     * Get the preferred size for the legend color display.
     *
     * @return Preferred size for the legend color display.
     */
    @Override
    public Dimension getPreferredSize() {
        return preferredSize;
    }

    /**
     * Draw the legend color using the given graphics.
     *
     * @param graphics Graphics to draw the legend color on.
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        int width = getWidth();
        int height = getHeight();

        graphics.setColor(color);
        graphics.fillRect(0, 0, width, height);

        graphics.setColor(color.darker());
        graphics.drawRect(0, 0, width - 1, height - 1);
    }
}
