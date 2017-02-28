import data.DataManager;
import data.Log;
import helpers.LetterHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
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
    public void start(Stage primaryStage) {
        String wordsPath = getWordsPath();
        loadWords(wordsPath);

        showGui(primaryStage);
        Set<String> words = DataManager.getWords();

        if (words.size() == 0)
            showNoWordsMessage(primaryStage);
    }

    private void showGui(Stage stage) {
        URL mainDisplayUrl = Bootstrap.class.getResource("/MainDisplay.fxml");

        try {
            Parent mainDisplay = FXMLLoader.load(mainDisplayUrl);
            stage.setTitle("Boggle");

            stage.setScene(new Scene(mainDisplay));
            stage.setResizable(false);
            stage.sizeToScene();

            stage.show();
        } catch (IOException exception) {
            Log.error("Failed to load GUI");
        }
    }

    private String getWordsPath() {
        Parameters parameters = getParameters();
        Map<String, String> namedParameters = parameters.getNamed();

        String path = System.getProperty("user.dir") + File.separatorChar + "words.txt";

        if (namedParameters.containsKey("words"))
            path = namedParameters.get("words");

        return path;
    }

    /**
     * Try to load words from a file.
     *
     * @param path Path to the file containing words.
     */
    private void loadWords(String path) {
        Log.info("Loading words from %s", path);
        Set<String> words = DataManager.getWords();

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

        Log.info("Loaded %d words", words.size());
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

    /**
     * Show a message indicating that no words have been loaded.
     */
    private void showNoWordsMessage(Stage owner) {
        String message = String.format("No words have been loaded. Please check the console output.%n%n" +
                "By default, words.txt in the working directory of this program is loaded. " +
                "To use a specific file, start this program using the words option.%n%n" +
                "Example: java -jar Boggle.jar --words=C:\\words.txt");

        Alert alert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);

        alert.setTitle("No words loaded");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(owner);

        alert.show();
    }
}
