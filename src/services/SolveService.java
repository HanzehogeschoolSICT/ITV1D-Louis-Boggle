package services;

import data.Log;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import models.BoardModel;
import models.MatchModel;
import models.PointModel;
import models.SolveWorkerDataModel;
import workers.SolveWorker;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Service to find all matches on the given board.
 */
public class SolveService extends Service<List<MatchModel>> {
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
        matches = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * Get all matching words for the board.
     *
     * @return All matching words for the board.
     */
    List<MatchModel> getMatches() {
        if (!isSolved) {
            Instant start = Instant.now();
            solve();

            long duration = Duration.between(start, Instant.now()).toMillis();
            Log.info("Solving took %d milliseconds", duration);
        }

        Collections.sort(matches);
        return matches;
    }

    /**
     * Try to find all words in the given board.
     */
    private void solve() {
        List<PointModel> allPoints = board.getAllPoints();

        // Distribute the work over the number of cores in this PC.
        // There is no guarantee this will happen, but this is a good number of threads.
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(processors);
        SolveWorkerDataModel solveWorkerData = new SolveWorkerDataModel(board, words, matches);

        for (PointModel point : allPoints)
            executorService.execute(new SolveWorker(solveWorkerData, point));

        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            Log.info("Found %d matches", matches.size());
            isSolved = true;
        } catch (InterruptedException exception) {
            Log.error("Solver was interrupted unexpectedly");
        }
    }

    /**
     * Create a task that will be executed when the service is started.
     *
     * @return Task containing the result of the service.
     */
    @Override
    protected Task<List<MatchModel>> createTask() {
        return new Task<List<MatchModel>>() {
            /**
             * Execute the task to retrieve its result.
             *
             * @return Result of the service.
             */
            protected List<MatchModel> call() {
                return getMatches();
            }
        };
    }
}
