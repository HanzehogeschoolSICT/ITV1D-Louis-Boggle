package models;

import java.util.Set;

public class MatchModel implements Comparable<MatchModel> {
    private final String word;
    private final Set<PointModel> points;

    /**
     * Initialize the match model using the given word and points.
     *
     * @param word   Matched word.
     * @param points Points used for the match.
     */
    public MatchModel(String word, Set<PointModel> points) {
        this.word = word;
        this.points = points;
    }

    /**
     * Check if a point is included in this match.
     *
     * @param point Point to check.
     * @return True if the point is included, false otherwise.
     */
    public boolean hasPoint(PointModel point) {
        return points.contains(point);
    }

    /**
     * Compare this match model to another match model.
     *
     * @param other Other match model.
     * @return Integer indicating equality (less, equal or greater).
     */
    @Override
    public int compareTo(MatchModel other) {
        return word.compareTo(other.word);
    }

    /**
     * Get a string representation of the match.
     *
     * @return String representation of the match.
     */
    @Override
    public String toString() {
        return String.format("Word: %s, points: %d", word, points.size());
    }
}
