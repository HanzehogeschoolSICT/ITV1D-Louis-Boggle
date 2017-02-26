import models.BoardModel;
import models.MatchModel;
import services.SolveService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Bootstrap {
    /**
     * Bootstrap the application.
     *
     * @param args Any arguments.
     */
    public static void main(String[] args) {
        BoardModel board = new BoardModel(16);

        Set<String> words = new HashSet<>();
        SolveService solveService = new SolveService(board, words);
        List<MatchModel> matches = solveService.getMatches();
    }
}
