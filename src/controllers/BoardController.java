package controllers;

import models.BoardModel;
import models.MatchModel;
import services.SolveService;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class BoardController {
    private final Set<String> words;
    private BoardModel board;
    private SolveService solveService;

    private Consumer<MatchModel> setDisplayMatchHandler;
    private Consumer<List<MatchModel>> updateMatchesHandler;
    private Consumer<BoardModel> updateBoardDisplayHandler;
    private Runnable adjustSizeHandler;

    /**
     * Initialize the board controller.
     *
     * @param words List of words to match.
     */
    public BoardController(Set<String> words) {
        this.words = words;
    }

    /**
     * Check if any words exist.
     *
     * @return True if there are words, false otherwise.
     */
    public boolean hasWords() {
        return words.size() > 0;
    }

    /**
     * Create a new board using the given size.
     *
     * @param size Size of the new board.
     */
    public void setBoard(int size) {
        board = new BoardModel(size);
        solveService = new SolveService(board, words);

        updateMatchesHandler.accept(null);
        setDisplayMatchHandler.accept(null);
        updateBoardDisplayHandler.accept(board);
    }

    /**
     * Solve the board.
     */
    public void solveBoard() {
        if (solveService == null)
            return;

        List<MatchModel> matches = solveService.getMatches();
        updateMatchesHandler.accept(matches);
    }

    /**
     * Display a specific match on the board.
     *
     * @param match Match to display.
     */
    public void displayMatch(MatchModel match) {
        setDisplayMatchHandler.accept(match);
        updateBoardDisplayHandler.accept(board);
    }

    /**
     * Adjust the size of the window to its contents.
     */
    public void adjustSize() {
        adjustSizeHandler.run();
    }

    /**
     * Set the set display match handler.
     *
     * @param setDisplayMatchHandler Set display match handler.
     */
    public void setSetDisplayMatchHandler(Consumer<MatchModel> setDisplayMatchHandler) {
        this.setDisplayMatchHandler = setDisplayMatchHandler;
    }

    /**
     * Set the update matches handler.
     *
     * @param updateMatchesHandler Update matches handler.
     */
    public void setUpdateMatchesHandler(Consumer<List<MatchModel>> updateMatchesHandler) {
        this.updateMatchesHandler = updateMatchesHandler;
    }

    /**
     * Set the update board display handler.
     *
     * @param updateBoardDisplayHandler Update board display handler.
     */
    public void setUpdateBoardDisplayHandler(Consumer<BoardModel> updateBoardDisplayHandler) {
        this.updateBoardDisplayHandler = updateBoardDisplayHandler;
    }

    /**
     * Set the adjust size handler.
     *
     * @param adjustSizeHandler Adjust size handler.
     */
    public void setAdjustSizeHandler(Runnable adjustSizeHandler) {
        this.adjustSizeHandler = adjustSizeHandler;
    }
}
