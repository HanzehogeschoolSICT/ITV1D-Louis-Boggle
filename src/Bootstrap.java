import controllers.BoardController;
import data.Log;
import displays.MainDisplay;
import helpers.LetterHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

        Log.info("Loading words from %s", path);
        Set<String> words = loadWords(path);
        Log.info("Loaded %d words", words.size());

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
            String line = readFirstLine(bufferedReader);

            while (line != null) {
                addWord(words, line);
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            fileReader.close();
        } catch (Exception exception) {
            Log.error("Words file not found: %s", path);
        }

        return words;
    }

    /**
     * Read the first line of the buffered reader.
     * This method removes the UTF8 Byte Order Mark, which causes valid words to be marked as invalid.
     *
     * @param bufferedReader Buffered reader to use.
     * @return Sanitized first line.
     */
    private static String readFirstLine(BufferedReader bufferedReader) {
        try {
            String line = bufferedReader.readLine();

            final String utf8bom = "\uFEFF";
            if (line.startsWith(utf8bom))
                line = line.substring(1);

            return line;
        } catch (IOException exception) {
            Log.error("Failed to read words");
            return null;
        }
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
