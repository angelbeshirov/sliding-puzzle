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
        if (0 <= zeroCoordinates.getKey() - 1) {
            final int[][] moveLeftBoard = Util.copy2DArray(board);

            moveLeftBoard[zeroCoordinates.getKey()][zeroCoordinates.getValue()] = moveLeftBoard[zeroCoordinates.getKey() - 1][zeroCoordinates.getValue()];
            moveLeftBoard[zeroCoordinates.getKey() - 1][zeroCoordinates.getValue()] = 0;

            final TileBoard moveDown = new TileBoard(moveLeftBoard, new Pair<>(zeroCoordinates.getKey() - 1, zeroCoordinates.getValue()));
            moveDown.appendMoves(moves + "D");
            neighbours.add(moveDown);
        }

        if (board.length > zeroCoordinates.getKey() + 1) {
            final int[][] moveRightBoard = Util.copy2DArray(board);

            moveRightBoard[zeroCoordinates.getKey()][zeroCoordinates.getValue()] = moveRightBoard[zeroCoordinates.getKey() + 1][zeroCoordinates.getValue()];
            moveRightBoard[zeroCoordinates.getKey() + 1][zeroCoordinates.getValue()] = 0;

            final TileBoard moveUp = new TileBoard(moveRightBoard, new Pair<>(zeroCoordinates.getKey() + 1, zeroCoordinates.getValue()));
            moveUp.appendMoves(moves + "U");
            neighbours.add(moveUp);
        }

        if (0 <= zeroCoordinates.getValue() - 1) {
            final int[][] moveUpBoard = Util.copy2DArray(board);

            moveUpBoard[zeroCoordinates.getKey()][zeroCoordinates.getValue()] = moveUpBoard[zeroCoordinates.getKey()][zeroCoordinates.getValue() - 1];
            moveUpBoard[zeroCoordinates.getKey()][zeroCoordinates.getValue() - 1] = 0;

            final TileBoard moveLeft = new TileBoard(moveUpBoard, new Pair<>(zeroCoordinates.getKey(), zeroCoordinates.getValue() - 1));
            moveLeft.appendMoves(moves + "R");
            neighbours.add(moveLeft);
        }

        if (board.length > zeroCoordinates.getValue() + 1) {
            final int[][] moveDownBoard = Util.copy2DArray(board);

            moveDownBoard[zeroCoordinates.getKey()][zeroCoordinates.getValue()] = moveDownBoard[zeroCoordinates.getKey()][zeroCoordinates.getValue() + 1];
            moveDownBoard[zeroCoordinates.getKey()][zeroCoordinates.getValue() + 1] = 0;

            final TileBoard moveRight = new TileBoard(moveDownBoard, new Pair<>(zeroCoordinates.getKey(), zeroCoordinates.getValue() + 1));
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
        // TODO the black doesn't participate in the heuristic distance
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
