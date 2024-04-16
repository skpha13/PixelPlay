package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.base.Position;
import com.example.pixelplay.chess.base.Square;

public class CastlingController {
    private final Position position;

    private Square whiteKingSquare = new Square(0, 4);
    private Square blackKingSquare = new Square(7, 4);

    private Square shortCastleDirection = new Square(0, 1);

    private boolean whiteKingMoved = false;
    private boolean whiteShortRookMoved = false;
    private boolean blackKingMoved = false;
    private boolean blackShortRookMoved = false;

    public CastlingController(Position position) {
        this.position = position;
    }

    public boolean canShortCastle(Color color) {
        return switch (color) {
            case WHITE -> canWhiteShortCastle();
            case BLACK -> canBlackShortCastle();
            default -> false;
        };
    }

    public boolean canWhiteShortCastle() {
        boolean squaresFree = position.isFree(whiteKingSquare.move(shortCastleDirection)) &&
                position.isFree(whiteKingSquare.move(shortCastleDirection).move(shortCastleDirection));

        return !whiteKingMoved && !whiteShortRookMoved && squaresFree;
    }

    public boolean canBlackShortCastle() {
        boolean squaresFree = position.isFree(blackKingSquare.move(shortCastleDirection)) &&
                position.isFree(blackKingSquare.move(shortCastleDirection).move(shortCastleDirection));

        return !blackKingMoved && !blackShortRookMoved && squaresFree;
    }

    public void clearFlags() {
        whiteKingMoved = true;
        whiteShortRookMoved = true;
        blackKingMoved = true;
        blackShortRookMoved = true;
    }
}
