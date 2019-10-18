package com.fmi.ai;

/**
 * Abstracts the solution of the puzzle. Converts the letter moves into more friendly format.
 * This class is also immutable.
 *
 * @author angel.beshirov
 */
public class SlidingSolution {

    /**
     * Contains the letter moves. E.g. LLD -> Left Left Down
     */
    private String moves;

    /**
     * Parametrized constructor which initializes the moves.
     *
     * @param moves the letter moves
     */
    public SlidingSolution(final String moves) {
        this.moves = moves;
    }

    /**
     * Returns the letter moves. (Not converted)
     *
     * @return the letter moves
     */
    public String getMoves() {
        return moves;
    }

    /**
     * Converts the letter moves into more meaningful and humanly readable format.
     * <p>
     * E.g.
     * 1 2 3
     * 0 5 6
     * 4 7 8
     * <p>
     * The solution of this puzzle will look like this:
     * Number of moves: 3
     * Solution: Up Left Left
     *
     * @return the number of moves and the moves which have to be taken to solve the puzzle.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Number of moves: ").append(moves != null ? moves.length() : 0).append("\nSolution: ");
        for (final char move : moves.toCharArray()) {
            switch (move) {
                case 'U':
                    sb.append("Up ");
                    break;
                case 'R':
                    sb.append("Right ");
                    break;
                case 'D':
                    sb.append("Down ");
                    break;
                case 'L':
                    sb.append("Left ");
                    break;
            }
        }
        return sb.toString();
    }
}
