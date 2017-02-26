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

    public BoardController(Set<String> words) {
        this.words = words;
    }

    public void setBoard(int size) {
        board = new BoardModel(size);
        solveService = new SolveService(board, words);
        updateBoardDisplayHandler.accept(board);
    }

    public void solveBoard() {
        if (solveService == null)
            return;

        List<MatchModel> matches = solveService.getMatches();
        updateMatchesHandler.accept(matches);
    }

    public void displayMatch(MatchModel match) {
        setDisplayMatchHandler.accept(match);
        updateBoardDisplayHandler.accept(board);
    }

    public void setSetDisplayMatchHandler(Consumer<MatchModel> setDisplayMatchHandler) {
        this.setDisplayMatchHandler = setDisplayMatchHandler;
    }

    public void setUpdateMatchesHandler(Consumer<List<MatchModel>> updateMatchesHandler) {
        this.updateMatchesHandler = updateMatchesHandler;
    }

    public void setUpdateBoardDisplayHandler(Consumer<BoardModel> updateBoardDisplayHandler) {
        this.updateBoardDisplayHandler = updateBoardDisplayHandler;
    }
}
