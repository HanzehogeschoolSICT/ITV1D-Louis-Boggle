package services;

import helpers.WordHelper;
import models.BoardModel;
import models.MatchModel;
import models.PointModel;
import models.SolveWorkerDataModel;
import workers.SolveWorker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SolveService {
    private final BoardModel board;
    private final Set<String> words;
    private final List<MatchModel> matches;
    private final int longestWordLength;
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
        matches = Collections.synchronizedList(new ArrayList<>());

        WordHelper wordHelper = WordHelper.getInstance();
        longestWordLength = wordHelper.getLongestWordLength(words);
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

        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(processors);
        SolveWorkerDataModel solveWorkerData = new SolveWorkerDataModel(board, words, matches, longestWordLength);

        for (PointModel point : allPoints)
            executorService.execute(new SolveWorker(solveWorkerData, point));

        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            isSolved = true;
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }


}
