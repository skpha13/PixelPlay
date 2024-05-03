package com.example.pixelplay.chess.position;

import com.example.pixelplay.chess.base.Square;

public class PositionUtil {

    public static boolean isOnBoard(Square square) {
        return square.getRank() >= 0 && square.getRank() < 8 && square.getFile() >= 0 && square.getFile() < 8;
    }

    public static final Square whiteKingSquare = new Square(0, 4);
    public static final Square blackKingSquare = new Square(7, 4);

    public static final Square shortCastleDirection = new Square(0, 1);
    public static final Square longCastleDirection = new Square(0, -1);
}
