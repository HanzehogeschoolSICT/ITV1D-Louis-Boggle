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
     * Get the hash code for the point.
     *
     * @return Hash code for the point.
     */
    public int hashCode() {
        return x * 31 + y;
    }
}
