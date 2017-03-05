package models;

import helpers.WordHelper;

import java.util.List;
import java.util.Set;

/**
 * Model containing information required to find matches on the board.
 */
public class SolveWorkerDataModel {
    private final BoardModel board;
    private final Set<String> words;
    private final List<MatchModel> matches;
    private final WordHelper wordHelper;

    /**
     * Initialize the solve worker data.
     *
     * @param board   Board to use for solving.
     * @param words   Words to match.
     * @param matches List of found matches.
     */
    public SolveWorkerDataModel(BoardModel board, Set<String> words, List<MatchModel> matches) {
        this.board = board;
        this.words = words;
        this.matches = matches;
        this.wordHelper = new WordHelper(words);
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

    /**
     * Get the word helper.
     *
     * @return Word helper.
     */
    public WordHelper getWordHelper() {
        return wordHelper;
    }
}
