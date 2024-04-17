package com.example.pixelplay.chess;

import com.example.pixelplay.chess.base.Position;
import com.example.pixelplay.chess.utils.PositionUtil;

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
        return new Position(PositionUtil.translateBoard(boardStrings), true);
    }

    public static Position customPosition(String[] boardStrings, boolean castlingFlags) {
        return new Position(PositionUtil.translateBoard(boardStrings), castlingFlags);
    }


}
