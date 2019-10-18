package com.fmi.ai;

public class SlidingSolverDriver {

    public static void main(final String[] args) {
        final long start = System.currentTimeMillis();
        final int[][] puzzle = {{3, 5, 1}, {4, 2, 8}, {6, 0, 7}};
        final int[][] puzzle2 = {{7, 3, 6}, {5, 0, 1}, {2, 8, 4}};
        final int[][] puzzle21 = {{7, 8, 4}, {3, 0, 1}, {6, 2, 5}};
        final int[][] puzzle1 = {{6, 7, 5, 4}, {1, 2, 11, 14}, {9, 15, 10, 12}, {13, 3, 8, 0}};
        final int[][] puzzle3 = {{2, 7, 13, 15}, {4, 11, 1, 14}, {0, 3, 10, 8}, {12, 9, 6, 5}};
        final int[][] puzzle4 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 0, 10, 11}, {13, 14, 15, 12}};
        final int[][] puzzle5 = {{11, 3, 9, 14}, {6, 8, 13, 2}, {1, 10, 12, 5}, {7, 0 , 4, 15}};
        final int[][] puzzle7 = {{6, 15, 8, 11}, {9, 13, 10, 1}, {4, 12, 14, 7}, {5, 0, 3, 2}};
        final int[][] puzzle6 = {{0, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {1, 17, 18, 19, 20}, {16, 21 , 22, 23, 24}};
        final SlidingSolution solution = new SlidingSolver(puzzle21).solvePuzzleIDAStar();
        //System.out.println(solution.getMoves());
        System.out.println(solution);
        System.out.println("Total time: " + (System.currentTimeMillis() - start));
    }
}
