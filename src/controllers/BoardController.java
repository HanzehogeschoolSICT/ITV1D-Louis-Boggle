package controllers;

import models.BoardModel;
import models.MatchModel;
import services.SolveService;

import java.util.List;
import java.util.Set;

public class BoardController {
    private BoardModel board;
    private Set<String> words;
    private SolveService solveService;

    public BoardController(Set<String> words) {
        this.words = words;
    }

    public void setBoard(int size) {
        board = new BoardModel(size);
        solveService = new SolveService(board, words);
    }

    public BoardModel getBoard() {
        return board;
    }

    public void solveBoard() {
        if (solveService == null)
            return;

        List<MatchModel> matches = solveService.getMatches();
    }
}
