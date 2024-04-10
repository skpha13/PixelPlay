package com.example.pixelplay.chess;

public class PositionGenerator {
    private static final String[] initialBoardStrings = new String[] {
            "rhbqkbhr",
            "pppppppp",
            "........",
            "........",
            "........",
            "........",
            "PPPPPPPP",
            "RHBQKBHR"
    };

    public static Position initialPosition() {
        Position position = new Position();
        position.setBoard(PositionUtil.translateBoard(initialBoardStrings));
        position.setFlags(true, true, true, true);
        return position;
    }


}
