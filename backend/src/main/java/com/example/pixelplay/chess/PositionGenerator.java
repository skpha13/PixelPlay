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
        Position position = new Position();
        position.setBoard(PositionUtil.translateBoard(boardStrings));
        position.setCastlingFlags(true);
        return position;
    }

    public static Position customPosition(String[] boardStrings, boolean castlingFlags) {
        Position position = new Position();
        position.setBoard(PositionUtil.translateBoard(boardStrings));
        position.setCastlingFlags(castlingFlags);
        return position;
    }


}
