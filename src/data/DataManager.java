package data;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.BoardModel;
import models.MatchModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DataManager {
    private static Property<BoardModel> boardProperty = new SimpleObjectProperty<>();
    private static Property<MatchModel> matchProperty = new SimpleObjectProperty<>();

    private static Set<String> words = new HashSet<>();
    private static ObservableList<MatchModel> matchList = FXCollections.observableList(new ArrayList<>());

    public static Property<BoardModel> getBoardProperty() {
        return boardProperty;
    }

    public static Property<MatchModel> getMatchProperty() {
        return matchProperty;
    }

    public static Set<String> getWords() {
        return words;
    }

    public static ObservableList<MatchModel> getMatchList() {
        return matchList;
    }
}
