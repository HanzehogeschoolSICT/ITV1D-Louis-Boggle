package data;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.BoardModel;
import models.MatchModel;

import java.util.HashSet;
import java.util.Set;

public class DataManager {
    private static final Property<BoardModel> boardProperty = new SimpleObjectProperty<>();
    private static final Property<MatchModel> matchProperty = new SimpleObjectProperty<>();

    private static final Set<String> words = new HashSet<>();
    private static final ObservableList<MatchModel> matchList = FXCollections.observableArrayList();

    /**
     * Get the board property.
     *
     * @return Board property.
     */
    public static Property<BoardModel> getBoardProperty() {
        return boardProperty;
    }

    /**
     * Get the board.
     *
     * @return Board.
     */
    public static BoardModel getBoard() {
        return boardProperty.getValue();
    }

    /**
     * Set the board.
     *
     * @param board New board.
     */
    public static void setBoard(BoardModel board) {
        boardProperty.setValue(board);
    }

    /**
     * Get the match property.
     *
     * @return Match property.
     */
    public static Property<MatchModel> getMatchProperty() {
        return matchProperty;
    }

    /**
     * Set the match.
     *
     * @param match New match.
     */
    public static void setMatch(MatchModel match) {
        matchProperty.setValue(match);
    }

    /**
     * Get the set of words to match.
     *
     * @return Set of words to match.
     */
    public static Set<String> getWords() {
        return words;
    }

    /**
     * Get the list of matches.
     *
     * @return List of matches.
     */
    public static ObservableList<MatchModel> getMatchList() {
        return matchList;
    }
}
