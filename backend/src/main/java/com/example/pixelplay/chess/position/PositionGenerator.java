package com.example.pixelplay.chess.position;

import com.example.pixelplay.chess.base.Color;

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
        return customPosition(initialBoardStrings);
    }

    public static Position customPosition(String[] boardStrings) {
        return new Position(PositionUtil.translateBoard(boardStrings), Color.WHITE);
    }

    public static Position customPosition(String[] boardStrings, boolean castlingFlags) {
        return new Position(PositionUtil.translateBoard(boardStrings), Color.WHITE);
    }

    public static Position customPosition(String[] boardStrings, Color whoseTurn) {
        return new Position(PositionUtil.translateBoard(boardStrings), whoseTurn);
    }


}
