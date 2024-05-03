package com.example.pixelplay.chess.position;

import com.example.pixelplay.chess.base.PieceType;
import com.example.pixelplay.chess.base.Square;

public class PositionUtil {

    public static boolean isOnBoard(Square square) {
        return square.getRank() >= 0 && square.getRank() < 8 && square.getFile() >= 0 && square.getFile() < 8;
    }
}
