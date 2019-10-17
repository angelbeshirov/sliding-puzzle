package com.fmi.ai;

import java.util.Arrays;

public class Util {
    private Util() {

    }

    public static int calcDistance(Pair<Integer, Integer> a, Pair<Integer, Integer> b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    public static int[][] copy2DArray(int[][] a) {
        int[][] copy = new int[a.length][];
        for (int i = 0; i < a.length; i++) {
            copy[i] = Arrays.copyOf(a[i], a[i].length);
        }

        return copy;
    }
}
