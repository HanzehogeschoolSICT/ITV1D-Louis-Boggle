import controllers.BoardController;
import displays.MainDisplay;
import helpers.LetterHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

class Bootstrap {
    /**
     * Bootstrap the application.
     *
     * @param args Any arguments.
     */
    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + File.separatorChar + "words.txt";
        if (args.length >= 2 && args[0].equals("--words"))
            path = args[1];

        System.out.println(String.format("Loading words from %s", path));
        Set<String> words = loadWords(path);

        BoardController boardController = new BoardController(words);
        new MainDisplay(boardController);
    }

    /**
     * Try to load words from a file.
     *
     * @param path Path to the file containing words.
     * @return Set of found words.
     */
    private static Set<String> loadWords(String path) {
        Set<String> words = new HashSet<>();

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            while (line != null) {
                addWord(words, line);
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            fileReader.close();
        } catch (Exception exception) {
            System.out.println(String.format("Words file not found: %s", path));
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
            System.out.println(String.format("Invalid word not added: %s", word));
            return;
        }

        words.add(word);
    }
}
