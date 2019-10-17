package com.fmi.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TileBoard {
    //String representation of a puzzle board
    private int[][] board;
    //String representation of the list of moves that generated this board
    private String myMoves = "";

    private Pair<Integer, Integer> zeroCoordinates;
    private int heuristic;

    /* You may add more instance and class variables and methods as you see fit - you will need to */

    public TileBoard(final int[][] board, Pair<Integer, Integer> zeroCoordinates) {
        this.board = board;
//        for(int i = 0; i < n; i++) {
//            for(int j = 0; j < board[i].length; j++) {
//                if(this.board[i][j] == 0) {
//                    zeroCoordinates = new Pair<>(i, j);
//                    break;
//                }
//            }
//        }
        this.zeroCoordinates = zeroCoordinates;
        this.heuristic = calculateManhattanDistance() + myMoves.length();
    }

    /*
     * Returns a list of boards that are one move away.  This list *DOES NOT* contain the
     * previous board, as this would undo a moving we just made (see the lab documentation).
     */
    public List<TileBoard> getNextBoards() {
        final List<TileBoard> neighbours = new ArrayList<>();
        if (0 <= zeroCoordinates.getX() - 1) {
            int[][] moveLeftBoard = Util.copy2DArray(board);

            moveLeftBoard[zeroCoordinates.getX()][zeroCoordinates.getY()] = moveLeftBoard[zeroCoordinates.getX() - 1][zeroCoordinates.getY()];
            moveLeftBoard[zeroCoordinates.getX() - 1][zeroCoordinates.getY()] = 0;

            final TileBoard moveDown = new TileBoard(moveLeftBoard, new Pair<>(zeroCoordinates.getX() - 1, zeroCoordinates.getY()));
            moveDown.myMoves += "up ";
            neighbours.add(moveDown);
        }

        if (board.length > zeroCoordinates.getX() + 1) {
            int[][] moveRightBoard = Util.copy2DArray(board);

            moveRightBoard[zeroCoordinates.getX()][zeroCoordinates.getY()] = moveRightBoard[zeroCoordinates.getX() + 1][zeroCoordinates.getY()];
            moveRightBoard[zeroCoordinates.getX() + 1][zeroCoordinates.getY()] = 0;

            final TileBoard moveUp = new TileBoard(moveRightBoard, new Pair<>(zeroCoordinates.getX() + 1, zeroCoordinates.getY()));
            moveUp.myMoves += "down ";
            neighbours.add(moveUp);
        }

        if (0 <= zeroCoordinates.getY() - 1) {
            int[][] moveUpBoard = Util.copy2DArray(board);

            moveUpBoard[zeroCoordinates.getX()][zeroCoordinates.getY()] = moveUpBoard[zeroCoordinates.getX()][zeroCoordinates.getY() - 1];
            moveUpBoard[zeroCoordinates.getX()][zeroCoordinates.getY() - 1] = 0;

            final TileBoard moveLeft = new TileBoard(moveUpBoard, new Pair<>(zeroCoordinates.getX(), zeroCoordinates.getY() - 1));
            moveLeft.myMoves += "left ";
            neighbours.add(moveLeft);
        }

        if (board.length > zeroCoordinates.getY() + 1) {
            int[][] moveDownBoard = Util.copy2DArray(board);

            moveDownBoard[zeroCoordinates.getX()][zeroCoordinates.getY()] = moveDownBoard[zeroCoordinates.getX()][zeroCoordinates.getY() + 1];
            moveDownBoard[zeroCoordinates.getX()][zeroCoordinates.getY() + 1] = 0;

            final TileBoard moveRight = new TileBoard(moveDownBoard, new Pair<>(zeroCoordinates.getX(), zeroCoordinates.getY() + 1));
            moveRight.myMoves += "right ";
            neighbours.add(moveRight);
        }
        return neighbours;
    }

    public int getHeuristic() {
        return this.heuristic;
    }

    /*
     * Evaluates the given board using the Manhattan distance heuristic.
     */
    public int calculateManhattanDistance() {
        int n = board.length;
        int manhattanDistance = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < board[i].length; j++) {
                manhattanDistance += Util.calcDistance(new Pair<>(i, j), getExpectedCoordinates(board[i][j], n));
            }
        }

        return manhattanDistance;
    }

    private Pair<Integer, Integer> getExpectedCoordinates(final int x, final int n) {
        if (0 == x) {
            return new Pair<>(n - 1, n - 1);
        }
        int row = (0 != x % n) ? (x / n) : (x / n - 1);
        int col = (0 != x % n) ? (x % n - 1) : (n - 1);

        return new Pair<>(row, col);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TileBoard tileBoard = (TileBoard) o;
        return Arrays.deepEquals(board, tileBoard.board) &&
                Objects.equals(zeroCoordinates, tileBoard.zeroCoordinates);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(zeroCoordinates);
        result = 31 * result + Arrays.hashCode(board);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TileBoard{").append("board=[");
        for (int i = 0; i < board.length; i++) {
            sb.append(Arrays.toString(board[i]));
        }
        sb.append(", myMoves='\"").append(myMoves).append("\'").append(", zeroCoordinates=").append(zeroCoordinates.toString())
                .append(", heuristic=").append(heuristic).append("}").append("\n");

        return sb.toString();
    }
}
