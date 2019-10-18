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
}
