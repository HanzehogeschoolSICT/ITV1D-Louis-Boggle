package services;

import models.BoardModel;
import models.MatchModel;
import models.PointModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolveService {
    private final BoardModel board;
    private final Set<String> words;
    private final List<MatchModel> matches;
    private boolean isSolved;

    /**
     * Initialize the solve service using the given board and list of words.
     *
     * @param board Board to use.
     * @param words List of words to use.
     */
    public SolveService(BoardModel board, Set<String> words) {
        this.board = board;
        this.words = words;
        matches = new ArrayList<>();
    }

    /**
     * Get all matching words for the board.
     *
     * @return All matching words for the board.
     */
    public List<MatchModel> getMatches() {
        if (!isSolved)
            solve();

        return matches;
    }

    /**
     * Try to find all words in the given board.
     */
    private void solve() {
        List<PointModel> allPoints = board.getAllPoints();

        for (PointModel point : allPoints)
            visitPoint("", point, new HashSet<>());

        isSolved = true;
    }

    /**
     * Visit the given point (if allowed) and check for a match in the list of words.
     *
     * @param currentWord   Current chain of letters.
     * @param point         Point to visit.
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
        if (words.contains(currentWord)) {
            MatchModel match = new MatchModel(currentWord, visitedPoints);
            matches.add(match);
        }

        List<PointModel> surroundingPoints = board.getSurroundingPoints(point);
        for (PointModel surroundingPoint : surroundingPoints) {
            // Create a copy of the set to prevent different paths from sharing visited points.
            Set<PointModel> visitedPointsCopy = new HashSet<>(visitedPoints);
            visitPoint(currentWord, surroundingPoint, visitedPointsCopy);
        }
    }
}
