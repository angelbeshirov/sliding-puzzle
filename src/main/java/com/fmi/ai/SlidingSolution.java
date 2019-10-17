package com.fmi.ai;

public class SlidingSolution {
    private String myMoves;

    public SlidingSolution(String moves) {
        myMoves = moves;
    }

    public String getMoves()
    {
        return myMoves;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Move the empty space in the following directions:\n\n");
        for(char move : myMoves.toCharArray())
        {
            switch(move)
            {
                case 'U' :
                    sb.append("UP\n");
                    break;
                case 'R' :
                    sb.append("RIGHT\n");
                    break;
                case 'D' :
                    sb.append("DOWN\n");
                    break;
                case 'L' :
                    sb.append("LEFT\n");
                    break;
            }
        }
        return sb.toString();
    }
}
