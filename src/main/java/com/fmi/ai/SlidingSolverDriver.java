package com.fmi.ai;

public class SlidingSolverDriver {

    public static void main(String[] args) {
        int[][] puzzle = {{1, 2, 3}, {4, 5, 6}, {0, 7, 8}};
        int[][] puzzle2 = {{7, 3, 1}, {2, 4, 5}, {6, 0, 8}};
        int[][] puzzle1 = {{4, 2, 1, 3}, {11, 6, 5, 12}, {13, 0, 7, 8}, {9, 10, 14, 15}};
        String solution = new SlidingSolver(puzzle2).solvePuzzleIDAStar();
        System.out.println(solution);
    }

}
