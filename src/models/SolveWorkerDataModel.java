package models;

import java.util.List;
import java.util.Set;

public class SolveWorkerDataModel {
    private final BoardModel board;
    private final int longestWordLength;
    private final Set<String> words;
    private final List<MatchModel> matches;

    /**
     * Initialize the solve worker data.
     *
     * @param board Board to use for solving.
     * @param words Words to match.
     * @param matches List of found matches.
     * @param longestWordLength Length of the longest word to match.
     */
    public SolveWorkerDataModel(BoardModel board, Set<String> words, List<MatchModel> matches, int longestWordLength) {
        this.board = board;
        this.words = words;
        this.matches = matches;
        this.longestWordLength = longestWordLength;
    }

    /**
     * Get the board.
     *
     * @return Board.
     */
    public BoardModel getBoard() {
        return board;
    }

    /**
     * Get the length of the longest word.
     *
     * @return Length of the longest word.
     */
    public int getLongestWordLength() {
        return longestWordLength;
    }

    /**
     * Get the words to match.
     *
     * @return Words to match.
     */
    public Set<String> getWords() {
        return words;
    }

    /**
     * Get the matches.
     *
     * @return Matches.
     */
    public List<MatchModel> getMatches() {
        return matches;
    }
}
