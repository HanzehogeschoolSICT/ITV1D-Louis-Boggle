package models;

public class PointModel {
    final int x;
    final int y;

    /**
     * Initialize the point using the given X and Y positions.
     *
     * @param x X position.
     * @param y Y position.
     */
    PointModel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get a string representation of the point.
     *
     * @return String representation of the point.
     */
    @Override
    public String toString() {
       return String.format("X: %d, Y: %d", x, y);
    }

    /**
     * Check if another object equals this point.
     *
     * @param other Other object to check.
     * @return True if they are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PointModel))
            return false;

        PointModel otherPoint = (PointModel) other;
        return x == otherPoint.x && y == otherPoint.y;
    }

    /**
     * Get the hash code for the point.
     *
     * @return Hash code for the point.
     */
    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
