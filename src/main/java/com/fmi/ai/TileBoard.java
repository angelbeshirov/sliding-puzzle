package com.fmi.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TileBoard {
    private int[][] board;
    private String moves = "";

    private Pair<Integer, Integer> zeroCoordinates;
    private int manhattan;
    private int heuristic;

    public TileBoard(final int[][] board, final Pair<Integer, Integer> zeroCoordinates) {
        this.board = board;
        this.zeroCoordinates = zeroCoordinates;
        this.manhattan = calculateManhattanDistance();
        this.heuristic = this.manhattan + moves.length();
    }

    public List<TileBoard> getNextBoards() {
        final List<TileBoard> neighbours = new ArrayList<>();
        if (0 <= zeroCoordinates.getX() - 1) {
            final int[][] moveLeftBoard = Util.copy2DArray(board);

            moveLeftBoard[zeroCoordinates.getX()][zeroCoordinates.getY()] = moveLeftBoard[zeroCoordinates.getX() - 1][zeroCoordinates.getY()];
            moveLeftBoard[zeroCoordinates.getX() - 1][zeroCoordinates.getY()] = 0;

            final TileBoard moveDown = new TileBoard(moveLeftBoard, new Pair<>(zeroCoordinates.getX() - 1, zeroCoordinates.getY()));
            moveDown.appendMoves(moves + "D");
            neighbours.add(moveDown);
        }

        if (board.length > zeroCoordinates.getX() + 1) {
            final int[][] moveRightBoard = Util.copy2DArray(board);

            moveRightBoard[zeroCoordinates.getX()][zeroCoordinates.getY()] = moveRightBoard[zeroCoordinates.getX() + 1][zeroCoordinates.getY()];
            moveRightBoard[zeroCoordinates.getX() + 1][zeroCoordinates.getY()] = 0;

            final TileBoard moveUp = new TileBoard(moveRightBoard, new Pair<>(zeroCoordinates.getX() + 1, zeroCoordinates.getY()));
            moveUp.appendMoves(moves + "U");
            neighbours.add(moveUp);
        }

        if (0 <= zeroCoordinates.getY() - 1) {
            final int[][] moveUpBoard = Util.copy2DArray(board);

            moveUpBoard[zeroCoordinates.getX()][zeroCoordinates.getY()] = moveUpBoard[zeroCoordinates.getX()][zeroCoordinates.getY() - 1];
            moveUpBoard[zeroCoordinates.getX()][zeroCoordinates.getY() - 1] = 0;

            final TileBoard moveLeft = new TileBoard(moveUpBoard, new Pair<>(zeroCoordinates.getX(), zeroCoordinates.getY() - 1));
            moveLeft.appendMoves(moves + "R");
            neighbours.add(moveLeft);
        }

        if (board.length > zeroCoordinates.getY() + 1) {
            final int[][] moveDownBoard = Util.copy2DArray(board);

            moveDownBoard[zeroCoordinates.getX()][zeroCoordinates.getY()] = moveDownBoard[zeroCoordinates.getX()][zeroCoordinates.getY() + 1];
            moveDownBoard[zeroCoordinates.getX()][zeroCoordinates.getY() + 1] = 0;

            final TileBoard moveRight = new TileBoard(moveDownBoard, new Pair<>(zeroCoordinates.getX(), zeroCoordinates.getY() + 1));
            moveRight.appendMoves(moves + "L");
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
        final int n = board.length;
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
        final int row = (0 != x % n) ? (x / n) : (x / n - 1);
        final int col = (0 != x % n) ? (x % n - 1) : (n - 1);

        return new Pair<>(row, col);
    }

    private void appendMoves(final String moves) {
        this.moves += moves;
        this.heuristic = this.manhattan + this.moves.length();
    }

    public String getMoves() {
        return moves;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TileBoard tileBoard = (TileBoard) o;
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
        final StringBuilder sb = new StringBuilder();
        sb.append("TileBoard{").append("board=[");
        for (int i = 0; i < board.length; i++) {
            sb.append(Arrays.toString(board[i]));
        }
        sb.append(", myMoves='\"").append(moves).append("\'").append(", zeroCoordinates=").append(zeroCoordinates.toString())
                .append(", heuristic=").append(heuristic).append("}").append("\n");

        return sb.toString();
    }
}
