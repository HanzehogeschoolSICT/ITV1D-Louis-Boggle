import controllers.BoardController;
import data.Log;
import displays.MainDisplay;
import helpers.LetterHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Bootstrap extends Application {
    /**
     * Bootstrap the application.
     *
     * @param args Any arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parameters parameters = getParameters();
        Map<String, String> namedParameters = parameters.getNamed();

        String path = System.getProperty("user.dir") + File.separatorChar + "words.txt";
        if (namedParameters.containsKey("--words"))
            path = namedParameters.get("--words");

        Log.info("Loading words from %s", path);
        Set<String> words = loadWords(path);
        Log.info("Loaded %d words", words.size());

        BoardController boardController = new BoardController(words);
        //new MainDisplay(boardController);

        Parent mainDisplay = FXMLLoader.load(getClass().getResource("/MainDisplay.fxml"));
        primaryStage.setTitle("Boggle");
        primaryStage.setScene(new Scene(mainDisplay));
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * Try to load words from a file.
     *
     * @param path Path to the file containing words.
     * @return Set of found words.
     */
    private static Set<String> loadWords(String path) {
        Set<String> words = new HashSet<>();

        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line = bufferedReader.readLine();

            while (line != null) {
                addWord(words, line);
                line = bufferedReader.readLine();
            }
        } catch (Exception exception) {
            Log.error("Words file not found: %s", path);
        }

        return words;
    }

    /**
     * Validate and add a single word to the list of words.
     *
     * @param words List of words.
     * @param word  Word to validate and add.
     */
    private static void addWord(Set<String> words, String word) {
        word = word.trim();
        if (word.equals(""))
            return;

        word = word.toUpperCase();
        LetterHelper letterHelper = LetterHelper.getInstance();

        if (!letterHelper.allLetters(word)) {
            Log.error("Invalid word not added: %s", word);
            return;
        }

        words.add(word);
    }
}
