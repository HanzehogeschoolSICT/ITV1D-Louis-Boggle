import models.BoardModel;

import java.util.HashSet;
import java.util.Set;

public class Bootstrap {
    /**
     * Bootstrap the application.
     *
     * @param args Any arguments.
     */
    public static void main(String[] args) {
        BoardModel board = new BoardModel(3);

        Set<String> words = new HashSet<>();
        Solver solver = new Solver(board, words);

        solver.solve();
    }
}
