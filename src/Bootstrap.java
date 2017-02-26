import controllers.BoardController;
import displays.MainDisplay;

import java.util.HashSet;
import java.util.Set;

class Bootstrap {
    /**
     * Bootstrap the application.
     *
     * @param args Any arguments.
     */
    public static void main(String[] args) {
        Set<String> words = new HashSet<>();
        BoardController boardController = new BoardController(words);
        new MainDisplay(boardController);
    }
}
