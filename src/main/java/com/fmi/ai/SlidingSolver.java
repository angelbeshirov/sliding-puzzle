package com.fmi.ai;

import java.util.*;

public class SlidingSolver {
    private int[][] board;
    private TileBoard expected;


    public SlidingSolver(int[][] initialBoard) {
        this.board = initialBoard;
        expected = generateExpected(initialBoard.length);
    }

    //    public SlidingSolution solvePuzzleIDAStar() {
    public String solvePuzzleIDAStar() {
        String res = "NO SOLUTION";
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
        TileBoard tileBoard = new TileBoard(board, zeroCoordinates);
        int bound = tileBoard.getHeuristic();
        Set<TileBoard> visited = new HashSet<>();
        Stack<TileBoard> stack = new Stack<>();
        stack.push(tileBoard);


        int t = Integer.MAX_VALUE;
        while (t != -1) {
            t = subroutine(stack, visited, bound);
            visited = new HashSet<>();
            if (t == -1) {
                res = Arrays.toString(stack.toArray());
            }

            if (t == Integer.MAX_VALUE) {
                System.out.println("NOT FOUND");
                break;
            }

            bound = t;
        }


        return res;
    }

    private int subroutine(Stack<TileBoard> fringe, Set<TileBoard> visited, int bound) {
        final TileBoard tileBoard = fringe.peek();
        int heuristic = tileBoard.getHeuristic();
        if (heuristic > bound) {
            return heuristic;
        }

        visited.add(tileBoard);

        if (expected.equals(tileBoard)) {
            return -1;
        }

        int min = Integer.MAX_VALUE;
        for (TileBoard neighbor : tileBoard.getNextBoards()) {
            if (!fringe.contains(neighbor) && !visited.contains(neighbor)) {
                fringe.add(neighbor);
                int k = subroutine(fringe, visited, bound);
                if (k == -1)
                    return k;

                if (k < min)
                    min = k;

                fringe.pop();
            }
        }

        return min;
    }

    private TileBoard generateExpected(int n) {
        int[][] expected = new int[n][n];
        int iter = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                expected[i][j] = iter++;
            }
        }

        expected[n - 1][n - 1] = 0;

        return new TileBoard(expected, new Pair<>(n - 1, n - 1));
    }
}