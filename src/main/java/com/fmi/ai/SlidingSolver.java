package com.fmi.ai;

import java.util.*;

public class SlidingSolver {
    private final int[][] board;
    private final TileBoard expected;
    private final StringBuilder sb = new StringBuilder();


    public SlidingSolver(final int[][] initialBoard) {
        this.board = initialBoard;
        this.expected = generateExpected(initialBoard.length);
    }

    public SlidingSolution solvePuzzleIDAStar() {
        String res = "";
        int row;
        int col = 0;

        outer:
        for (row = 0; row < board.length; row++) {
            for (col = 0; col < board[row].length; col++) {
                if (board[row][col] == 0) {
                    break outer;
                }
            }
        }
        final Pair<Integer, Integer> zeroCoordinates = new Pair<>(row, col);
        final TileBoard tileBoard = new TileBoard(board, Util.calculateManhattanDistance(board), zeroCoordinates);


        int bound = tileBoard.getHeuristic();
        TileBoard tile = tileBoard;
//        final Stack<TileBoard> stack = new Stack<>();
//        stack.push(tileBoard);
        // somewhat okay up to here
        int t = Integer.MAX_VALUE;
        while (t != -1) {

            //t = subroutine(tile, ,bound);

            if (t == -1) {
                //res = stack.pop().getMoves();
            }

            if (t == Integer.MAX_VALUE) {
                System.out.println("NOT FOUND");
                break;
            }
            if (t < bound)
                bound = t;
            //stack.clear();
            //stack.push(tileBoard);
        }


        return new SlidingSolution(res);
    }

    private int subroutine(final Stack<TileBoard> fringe, final int bound) {
        final TileBoard tileBoard = fringe.peek();
        final int heuristic = tileBoard.getHeuristic();
        if (heuristic > bound) {
            return heuristic;
        }

        if (expected.equals(tileBoard)) {
            return -1;
        }

        int min = Integer.MAX_VALUE;
//        for (final TileBoard neighbor : tileBoard.getNextBoards()) {
//            if (!fringe.contains(neighbor)) {
//                fringe.add(neighbor);
//                final int k = subroutine(fringe, bound);
//                if (k == -1)
//                    return k;
//
//                if (k < min)
//                    min = k;
//
//                fringe.pop();
//            }
//        }

        return min;
    }

    private TileBoard generateExpected(final int n) {
        final int[][] expected = new int[n][n];
        int iter = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                expected[i][j] = iter++;
            }
        }

        expected[n - 1][n - 1] = 0;

        return new TileBoard(expected, 0, new Pair<>(n - 1, n - 1));
    }
}