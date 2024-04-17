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

    public CastlingController(Position position, boolean flags) {
        this.position = position;
        setFlags(flags);
    }

    public boolean canShortCastle(Color color) {
        Square kingSquare = switch (color) {
            case WHITE -> whiteKingSquare;
            case BLACK -> blackKingSquare;
        };

        boolean kingMoved = switch (color) {
            case WHITE -> whiteKingMoved;
            case BLACK -> blackKingMoved;
        };

        boolean rookMoved = switch (color) {
            case WHITE -> whiteShortRookMoved;
            case BLACK -> blackKingMoved;
        };

        boolean squaresFree = position.isFree(kingSquare.move(shortCastleDirection)) &&
                position.isFree(kingSquare.move(shortCastleDirection, 2));

        boolean squaresNotAttacked = !position.isAttackedBy(color.reverse(), kingSquare.move(shortCastleDirection)) &&
                !position.isAttackedBy(color.reverse(), kingSquare.move(shortCastleDirection, 2));

        return !kingMoved && !rookMoved && squaresFree && squaresNotAttacked;

    }
    public boolean canLongCastle(Color color) {
        Square kingSquare = switch (color) {
            case WHITE -> whiteKingSquare;
            case BLACK -> blackKingSquare;
        };

        boolean kingMoved = switch (color) {
            case WHITE -> whiteKingMoved;
            case BLACK -> blackKingMoved;
        };

        boolean rookMoved = switch (color) {
            case WHITE -> whiteShortRookMoved;
            case BLACK -> blackKingMoved;
        };

        boolean squaresFree = position.isFree(kingSquare.move(longCastleDirection)) &&
                position.isFree(kingSquare.move(longCastleDirection, 2)) &&
                position.isFree(kingSquare.move(longCastleDirection, 3));

        boolean squaresNotAttacked = !position.isAttackedBy(color.reverse(), kingSquare.move(longCastleDirection)) &&
                !position.isAttackedBy(color.reverse(), kingSquare.move(longCastleDirection, 2));

        return !kingMoved && !rookMoved && squaresFree && squaresNotAttacked;
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
