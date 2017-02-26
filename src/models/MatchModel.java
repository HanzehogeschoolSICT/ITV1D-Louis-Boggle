package models;

import java.util.Set;

public class MatchModel {
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
     * Get the matched word.
     *
     * @return Matched word.
     */
    public String getWord() {
        return word;
    }

    /**
     * Get the points used for the match.
     *
     * @return Points used for the match.
     */
    public Set<PointModel> getPoints() {
        return points;
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
