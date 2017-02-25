import models.BoardModel;
import models.PointModel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solver {
    private BoardModel board;
    private Set<String> words;

    /**
     * Initialize the solver using the given board and list of words.
     *
     * @param board Board to use.
     * @param words List of words to use.
     */
    Solver(BoardModel board, Set<String> words) {
        this.board = board;
        this.words = words;
    }

    /**
     * Try to find all words in the given board.
     */
    void solve() {
        List<PointModel> allPoints = board.getAllPoints();

        for (PointModel point: allPoints)
            visitPoint("", point, new HashSet<>());
    }

    /**
     * Visit the given point (if allowed) and check for a match in the list of words.
     *
     * @param currentWord Current chain of letters.
     * @param point Point to visit.
     * @param visitedPoints Points which already have been visited.
     */
    private void visitPoint(String currentWord, PointModel point, Set<PointModel> visitedPoints) {
        // Check if the point has already been visited (this is not allowed).
        if (visitedPoints.contains(point))
            return;

        char letter = board.getLetter(point);
        visitedPoints.add(point);
        currentWord += letter;

        // Check if the current word matches any word in the list.
        if (words.contains(currentWord))
            System.out.println(currentWord);

        List<PointModel> surroundingPoints = board.getSurroundingPoints(point);
        for (PointModel surroundingPoint: surroundingPoints) {
            // Create a copy of the set to prevent different paths from sharing visited points.
            Set<PointModel> visitedPointsCopy = new HashSet<>(visitedPoints);
            visitPoint(currentWord, surroundingPoint, visitedPointsCopy);
        }
    }
}
