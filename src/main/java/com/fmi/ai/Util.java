package com.fmi.ai;

import java.util.Arrays;

public class Util {
    private Util() {

    }

    public static int calcDistance(final Pair<Integer, Integer> a, final Pair<Integer, Integer> b) {
        return Math.abs(a.getKey() - b.getKey()) + Math.abs(a.getValue() - b.getValue());
    }

    public static int[][] copy2DArray(final int[][] a) {
        final int[][] copy = new int[a.length][];
        for (int i = 0; i < a.length; i++) {
            copy[i] = Arrays.copyOf(a[i], a[i].length);
        }

        return copy;
    }

    /*
     * Evaluates the given board using the Manhattan distance heuristic.
     */
    public static int calculateManhattanDistance(int[][] a) {
        // TODO the black doesn't participate in the heuristic distance
        final int n = a.length;
        int manhattanDistance = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < a[i].length; j++) {
                manhattanDistance += Util.calcDistance(new Pair<>(i, j), getExpectedCoordinates(a[i][j], n));
            }
        }

        return manhattanDistance;
    }

    public static Pair<Integer, Integer> getExpectedCoordinates(final int x, final int n) {
        if (0 == x) {
            return new Pair<>(n - 1, n - 1);
        }
        final int row = (0 != x % n) ? (x / n) : (x / n - 1);
        final int col = (0 != x % n) ? (x % n - 1) : (n - 1);

        return new Pair<>(row, col);
    }
}
