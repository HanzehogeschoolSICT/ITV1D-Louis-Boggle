package helpers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Static helper for everything related to single letters.
 */
public class LetterHelper {
    private static LetterHelper instance = null;
    private final Character[] letters;
    private final Random random = new Random();

    /**
     * Initialize the letter helper.
     */
    private LetterHelper() {
        letters = new Character[]{
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
    }

    /**
     * Get the current letter helper instance.
     *
     * @return Current letter helper instance.
     */
    public static LetterHelper getInstance() {
        if (instance == null)
            instance = new LetterHelper();

        return instance;
    }

    /**
     * Get a random letter.
     *
     * @return Random letter.
     */
    public char getRandomLetter() {
        int randomIndex = random.nextInt(letters.length);
        return letters[randomIndex];
    }

    /**
     * Check if a word consists only out of letters.
     *
     * @param word Word to check.
     * @return True if the word only consists out of letters, false otherwise.
     */
    public boolean allLetters(String word) {
        char[] characters = word.toCharArray();
        Set<Character> letterSet = new HashSet<>(Arrays.asList(letters));

        for (char character : characters) {
            if (!letterSet.contains(character))
                return false;
        }

        return true;
    }
}
