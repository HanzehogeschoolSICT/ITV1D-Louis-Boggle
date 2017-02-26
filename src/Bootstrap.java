import controllers.BoardController;
import data.Settings;
import displays.MainDisplay;
import models.BoardModel;
import services.SolveService;

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
        BoardModel board = new BoardModel(Settings.BOARD_SIZE);
        SolveService solveService = new SolveService(board, words);

        BoardController boardController = new BoardController();
        new MainDisplay(boardController);
    }
}
