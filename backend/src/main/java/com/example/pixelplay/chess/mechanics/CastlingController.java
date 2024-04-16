package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.base.Position;
import com.example.pixelplay.chess.base.Square;

public class CastlingController {
    private final Position position;

    private Square whiteKingSquare = new Square(0, 3);
    private Square blackKingSquare = new Square(7, 3);

    private Square shortCastleDirection = new Square(0, -1);
    private Square longCastleDirection = new Square(0, 1);
    private boolean whiteKingMoved = false;
    private boolean whiteShortRookMoved = false;
    private boolean whiteLongRookMoved = false;
    private boolean blackKingMoved = false;
    private boolean blackShortRookMoved = false;
    private boolean blackLongRookMoved = false;

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
    public boolean canLongCastle(Color color) {
        return switch (color) {
            case WHITE -> canWhiteLongCastle();
            case BLACK -> canBlackLongCastle();
            default -> false;
        };
    }

    private boolean canWhiteShortCastle() {
        boolean squaresFree = position.isFree(whiteKingSquare.move(shortCastleDirection)) &&
                                 position.isFree(whiteKingSquare.move(shortCastleDirection, 2));

        return !whiteKingMoved && !whiteShortRookMoved && squaresFree;
    }

    private boolean canBlackShortCastle() {
        boolean squaresFree = position.isFree(blackKingSquare.move(shortCastleDirection)) &&
                                position.isFree(blackKingSquare.move(shortCastleDirection, 2));

        return !blackKingMoved && !blackShortRookMoved && squaresFree;
    }

    private boolean canWhiteLongCastle() {
        boolean squaresFree = position.isFree(whiteKingSquare.move(longCastleDirection)) &&
                                position.isFree(whiteKingSquare.move(longCastleDirection, 2)) &&
                                position.isFree(whiteKingSquare.move(longCastleDirection, 3));

        return !whiteKingMoved && !whiteLongRookMoved && squaresFree;
    }

    private boolean canBlackLongCastle() {
        boolean squaresFree = position.isFree(blackKingSquare.move(longCastleDirection)) &&
                                position.isFree(blackKingSquare.move(longCastleDirection, 2)) &&
                                position.isFree(blackKingSquare.move(longCastleDirection, 3));

        return !blackKingMoved && !blackLongRookMoved && squaresFree;
    }

    public void setFlags(boolean value) {
        whiteKingMoved = value;
        whiteShortRookMoved = value;
        whiteLongRookMoved = value;
        blackKingMoved = value;
        blackShortRookMoved = value;
        blackLongRookMoved = value;
    }
}
