package helpers;

import java.util.HashSet;
import java.util.Set;

public class WordHelper {
    private final Set<String> wordParts = new HashSet<>();

    /**
     * Initialize the word helper.
     *
     * @param words Word set to use for partial matching.
     */
    public WordHelper(Set<String> words) {
        fillWordParts(words);
    }

    /**
     * Fill the word parts set using the given words set.
     * By filling the set with every possible word part, the matching becomes an O(1) operation.
     * This is critical for the performance, because the hasPartialMatch method is invoked many times.
     *
     * @param words Word set to use.
     */
    private void fillWordParts(Set<String> words) {
        for (String word : words) {
            // Only add word parts, not the whole word. This is sufficient because
            // the max index is exclusive for both the for loop and the substring method.
            int maxIndex = word.length();

            for (int i = 1; i < maxIndex; i++)
                wordParts.add(word.substring(0, i));
        }
    }

    /**
     * Check if a word has a partial match.
     *
     * @param matchWord Partial word to match.
     * @return True if the word has a partial match, false otherwise.
     */
    public boolean hasPartialMatch(String matchWord) {
        return wordParts.contains(matchWord);
    }
}
