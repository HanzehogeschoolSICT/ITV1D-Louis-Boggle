package services;

import models.BoardModel;
import models.MatchModel;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolveServiceTest {
    /**
     * Test if the SolveService can find the requested words in the board.
     */
    @Test
    void matchWordsTest() {
        // This board contains WAT in 3 ways:
        // - X: 0, Y: 0 / X: 1, Y: 0 / X: 2, Y: 0
        // - X: 0, Y: 0 / X: 1, Y: 1 / X: 2, Y: 0
        // - X: 0, Y: 0 / X: 1, Y: 1 / X: 2, Y: 2
        char[][] boardData = new char[][]{
                {'W', 'A', 'T'},
                {'Y', 'A', 'N'},
                {'R', 'V', 'T'}
        };

        Set<String> words = new HashSet<>();
        words.add("WAT");

        BoardModel board = new BoardModel(boardData, 3);
        SolveService solveService = new SolveService(board, words);

        List<MatchModel> matches = solveService.getMatches();
        assertEquals(matches.size(), 3, "Invalid number of matches!");
    }
}
