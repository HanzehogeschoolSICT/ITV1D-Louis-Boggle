package workers;

import helpers.WordHelper;
import models.BoardModel;
import models.MatchModel;
import models.PointModel;
import models.SolveWorkerDataModel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolveWorker implements Runnable {
    private final BoardModel board;
    private final Set<String> words;
    private final List<MatchModel> matches;
    private final PointModel startPoint;
    private final WordHelper wordHelper;

    /**
     * Initialize the solve worker.
     *
     * @param data       Data to use for solving.
     * @param startPoint Start point for solving.
     */
    public SolveWorker(SolveWorkerDataModel data, PointModel startPoint) {
        this.board = data.getBoard();
        this.words = data.getWords();
        this.matches = data.getMatches();
        this.startPoint = startPoint;

        wordHelper = WordHelper.getInstance();
    }

    /**
     * Start solving.
     */
    @Override
    public void run() {
        visitPoint("", startPoint, new HashSet<>());
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

        if (!checkForMatch(currentWord, visitedPoints)) {
            // Prevent unnecessary operations if the current word doesn't have a match already.
            if (!wordHelper.hasPartialMatch(words, currentWord))
                return;
        }

        List<PointModel> surroundingPoints = board.getSurroundingPoints(point);
        for (PointModel surroundingPoint : surroundingPoints) {
            // Create a copy of the set to prevent different paths from sharing visited points.
            Set<PointModel> visitedPointsCopy = new HashSet<>(visitedPoints);
            visitPoint(currentWord, surroundingPoint, visitedPointsCopy);
        }
    }

    /**
     * Check if the current word matches any of the words.
     *
     * @param currentWord   Word to check for.
     * @param visitedPoints Visited points for the current word.
     * @return True if the current word has any match.
     */
    private boolean checkForMatch(String currentWord, Set<PointModel> visitedPoints) {
        if (!words.contains(currentWord))
            return false;

        MatchModel match = new MatchModel(currentWord, visitedPoints);
        matches.add(match);

        return true;
    }
}
