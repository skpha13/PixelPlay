package com.example.pixelplay.chess;

import com.example.pixelplay.chess.base.Position;

public class PositionGenerator {
    private static final String[] initialBoardStrings = new String[] {
            "RHBQKBHR",
            "PPPPPPPP",
            "........",
            "........",
            "........",
            "........",
            "pppppppp",
            "rhbqkbhr"
    };

    public static Position initialPosition() {
        Position position = new Position();
        position.setBoard(PositionUtil.translateBoard(initialBoardStrings));
        position.setFlags(true, true, true, true);
        return position;
    }

    public static Position customPosition(String[] boardStrings) {
        Position position = new Position();
        position.setBoard(PositionUtil.translateBoard(boardStrings));
        position.setFlags(true, true, true, true);
        return position;
    }


}
