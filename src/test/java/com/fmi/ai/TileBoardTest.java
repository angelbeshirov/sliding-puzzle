package com.fmi.ai;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class TileBoardTest {

    @Test
    public void testManhattanDistance() {
        final int[][] t = {{3, 2, 1}, {4, 6, 5}, {7, 0, 8}};
        TileBoard tileBoard = new TileBoard(t, new Pair<>(2, 2));
        int k = tileBoard.calculateManhattanDistance();
        assertTrue(8 == k);
    }

    @Test
    public void testManhattanDistance_reversed() {
        final int[][] t = {{8, 7, 6}, {5, 4, 3}, {2, 1, 0}};
        TileBoard tileBoard = new TileBoard(t, new Pair<>(2, 2));
        int k = tileBoard.calculateManhattanDistance();
        assertTrue(16 == k);
    }

    @Test
    public void testManhattanDistance_random() {
        final int[][] t = {{6, 8, 4}, {3, 1, 2}, {7, 5, 0}};
        TileBoard tileBoard = new TileBoard(t, new Pair<>(2, 2));
        int k = tileBoard.calculateManhattanDistance();
        assertTrue(16 == k);
    }

    @Test
    public void testManhattanDistance_n4() {
        final int[][] t = {{11, 8, 4, 1}, {14, 3, 10, 9}, {2, 7, 5, 12}, {6, 13, 15, 0}};
        TileBoard tileBoard = new TileBoard(t, new Pair<>(3, 3));
        int k = tileBoard.calculateManhattanDistance();
        assertTrue(34 == k);
    }

    @Test
    public void testNeighbours() {
        final int[][] t = {{11, 8, 4, 1}, {14, 3, 10, 9}, {2, 0, 5, 12}, {6, 13, 15, 7}};
        TileBoard tileBoard = new TileBoard(t, new Pair<>(2, 1));
        List<TileBoard> neighbours = tileBoard.getNextBoards();
        assertTrue(neighbours.size() == 4);
        int[][] copyLeft = copyArr(t);
        int[][] copyRight = copyArr(t);
        int[][] copyUp = copyArr(t);
        int[][] copyDown = copyArr(t);

        copyLeft[2][1] = 2;
        copyLeft[2][0] = 0;

        copyRight[2][1] = 5;
        copyRight[2][2] = 0;

        copyUp[2][1] = 3;
        copyUp[1][1] = 0;

        copyDown[2][1] = 13;
        copyDown[3][1] = 0;

        TileBoard copyLeftTile = new TileBoard(copyLeft, new Pair<>(2, 0));
        TileBoard copyRightTile = new TileBoard(copyRight, new Pair<>(2, 2));
        TileBoard copyUpTile = new TileBoard(copyUp, new Pair<>(1, 1));
        TileBoard copyLDownTile = new TileBoard(copyDown, new Pair<>(3, 1));

        assertTrue(neighbours.stream().anyMatch(copyLeftTile::equals));
        assertTrue(neighbours.stream().anyMatch(copyRightTile::equals));
        assertTrue(neighbours.stream().anyMatch(copyUpTile::equals));
        assertTrue(neighbours.stream().anyMatch(copyLDownTile::equals));
    }

    @Test
    public void testNeighbours_corner() {
        final int[][] t = {{11, 8, 4, 1}, {0, 3, 10, 9}, {2, 14, 5, 12}, {6, 13, 15, 7}};
        TileBoard tileBoard = new TileBoard(t, new Pair<>(1, 0));
        List<TileBoard> neighbours = tileBoard.getNextBoards();
        assertTrue(neighbours.size() == 3);
        int[][] copyRight = copyArr(t);
        int[][] copyUp = copyArr(t);
        int[][] copyDown = copyArr(t);

        copyRight[1][0] = 3;
        copyRight[1][1] = 0;

        copyUp[1][0] = 11;
        copyUp[0][0] = 0;

        copyDown[1][0] = 2;
        copyDown[2][0] = 0;

        TileBoard copyRightTile = new TileBoard(copyRight, new Pair<>(1, 1));
        TileBoard copyUpTile = new TileBoard(copyUp, new Pair<>(0, 0));
        TileBoard copyLDownTile = new TileBoard(copyDown, new Pair<>(2, 0));

        assertTrue(neighbours.stream().anyMatch(copyRightTile::equals));
        assertTrue(neighbours.stream().anyMatch(copyUpTile::equals));
        assertTrue(neighbours.stream().anyMatch(copyLDownTile::equals));
    }

    private int[][] copyArr(int[][] a) {
        int[][] s = new int[a.length][];
        for (int i = 0; i < a.length; i++) {
            s[i] = new int[a[i].length];
            for (int j = 0; j < a.length; j++) {
                s[i][j] = a[i][j];
            }
        }

        return s;
    }


}
