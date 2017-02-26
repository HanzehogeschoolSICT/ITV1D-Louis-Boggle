package helpers;

import java.util.Random;

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
}
