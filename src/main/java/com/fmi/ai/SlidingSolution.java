package com.fmi.ai;

public class SlidingSolution {
    private String myMoves;

    public SlidingSolution(final String moves) {
        myMoves = moves;
    }

    public String getMoves() {
        return myMoves;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Solution: ");
        for (final char move : myMoves.toCharArray()) {
            switch (move) {
                case 'U':
                    sb.append("UP ");
                    break;
                case 'R':
                    sb.append("RIGHT ");
                    break;
                case 'D':
                    sb.append("DOWN ");
                    break;
                case 'L':
                    sb.append("LEFT ");
                    break;
            }
        }
        return sb.toString();
    }
}
