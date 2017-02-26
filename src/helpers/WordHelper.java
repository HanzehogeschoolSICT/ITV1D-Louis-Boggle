package helpers;

import java.util.Set;

public class WordHelper {
    private static WordHelper instance;

    /**
     * Initialize the word helper.
     */
    private WordHelper() {

    }

    /**
     * Get the current word helper instance.
     *
     * @return Current word helper instance.
     */
    public static WordHelper getInstance() {
        if (instance == null)
            instance = new WordHelper();

        return instance;
    }

    /**
     * Check if a word has a partial match.
     *
     * @param words     Set of words to check.
     * @param matchWord Partial word to match.
     * @return True if the word has a partial match, false otherwise.
     */
    public boolean hasPartialMatch(Set<String> words, String matchWord) {
        for (String word : words) {
            // Check if the word starts with instead of contains,
            // because the word has to match all points from the starting point.
            if (word.startsWith(matchWord))
                return true;
        }

        return false;
    }
}
