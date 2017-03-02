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
        int x = point.getX();
        int y = point.getY();

        return board[x][y];
    }

    /**
     * Get the size of the board.
     *
     * @return Size of the board.
     */
    public int size() {
        return size;
    }

    /**
     * Get all surrounding points for the given point
     *
     * @param point Point to get surrounding points for.
     * @return List of surrounding points.
     */
    public List<PointModel> getSurroundingPoints(PointModel point) {
        List<PointModel> surroundingPoints = new ArrayList<>();

        int x = point.getX();
        int y = point.getY();

        // Top Left, Top, Top Right
        addPointToList(surroundingPoints, x - 1, y - 1);
        addPointToList(surroundingPoints, x, y - 1);
        addPointToList(surroundingPoints, x + 1, y - 1);

        // Left, Right
        addPointToList(surroundingPoints, x - 1, y);
        addPointToList(surroundingPoints, x + 1, y);

        // Bottom Left, Bottom, Bottom Right
        addPointToList(surroundingPoints, x - 1, y + 1);
        addPointToList(surroundingPoints, x, y + 1);
        addPointToList(surroundingPoints, x + 1, y + 1);

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

                if (y == size - 1)
                    boardBuilder.append(' ');
            }

            if (x == size - 1)
                boardBuilder.append('\n');
        }

        return boardBuilder.toString();
    }
}
