package models;

import helpers.LetterHelper;

import java.util.ArrayList;
import java.util.List;

public class BoardModel {
    private final int size;
    private final char[][] board;

    /**
     * Initialize the board using the given width and height.
     *
     * @param size Size of the board.
     */
    public BoardModel(int size) {
        this.size = size;
        board = new char[size][size];

        fillBoard();
    }

    /**
     * Initialize the board using random letters.
     */
    private void fillBoard() {
        LetterHelper letterHelper = LetterHelper.getInstance();

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                char letter = letterHelper.getRandomLetter();
                board[x][y] = letter;
            }
        }
    }

    /**
     * Get the letter on the position of the given point.
     *
     * @param point Point to get the letter for.
     * @return Letter on the given point.
     */
    public char getLetter(PointModel point) {
        return board[point.x][point.y];
    }

    /**
     * Get all surrounding points for the given point
     *
     * @param point Point to get surrounding points for.
     * @return List of surrounding points.
     */
    public List<PointModel> getSurroundingPoints(PointModel point) {
        List<PointModel> surroundingPoints = new ArrayList<>();

        // Top Left, Top, Top Right
        addPointToList(surroundingPoints, point.x - 1, point.y - 1);
        addPointToList(surroundingPoints, point.x, point.y - 1);
        addPointToList(surroundingPoints, point.x + 1, point.y - 1);

        // Left, Right
        addPointToList(surroundingPoints, point.x - 1, point.y);
        addPointToList(surroundingPoints, point.x + 1, point.y);

        // Bottom Left, Bottom, Bottom Right
        addPointToList(surroundingPoints, point.x - 1, point.y + 1);
        addPointToList(surroundingPoints, point.x, point.y + 1);
        addPointToList(surroundingPoints, point.x + 1, point.y + 1);

        return surroundingPoints;
    }

    /**
     * Get all points for the board.
     *
     * @return List of all points.
     */
    public List<PointModel> getAllPoints() {
        List<PointModel> allPoints = new ArrayList<>();

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                PointModel point = new PointModel(x, y);
                allPoints.add(point);
            }
        }

        return allPoints;
    }

    /**
     * Add a point to the list of points if it is within the board.
     *
     * @param list List of points.
     * @param x    X position to add.
     * @param y    Y position to add.
     */
    private void addPointToList(List<PointModel> list, int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size)
            return;

        PointModel point = new PointModel(x, y);
        list.add(point);
    }

    /**
     * Check if the given position is the last index of the board.
     *
     * @param position Position to check.
     * @return True if it is the last index, false otherwise.
     */
    private boolean isLastIndex(int position) {
        return position == size - 1;
    }

    /**
     * Get a string representation of the board.
     *
     * @return String representation of the board.
     */
    @Override
    public String toString() {
        StringBuilder boardBuilder = new StringBuilder();

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                boardBuilder.append(board[x][y]);

                if (!isLastIndex(y))
                    boardBuilder.append(' ');
            }

            if (!isLastIndex(x))
                boardBuilder.append('\n');
        }

        return boardBuilder.toString();
    }
}
