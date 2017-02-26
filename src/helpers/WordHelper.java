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
     * Get the number of characters of the longest word in the given set.
     *
     * @param words Set to get the longest word for.
     * @return Number of characters of the longest word.
     */
    public int getLongestWordLength(Set<String> words) {
        int longestWord = 0;

        for (String word : words)
            if (word.length() > longestWord)
                longestWord = word.length();

        return longestWord;
    }
}
