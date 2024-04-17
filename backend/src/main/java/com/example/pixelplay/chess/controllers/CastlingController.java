package com.example.pixelplay.chess.controllers;

import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.base.Position;
import com.example.pixelplay.chess.base.Square;

public class CastlingController {
    private final Position position;
    private final AttackController attackController;

    private final Square whiteKingSquare = new Square(0, 3);
    private final Square blackKingSquare = new Square(7, 3);

    private final Square shortCastleDirection = new Square(0, -1);
    private final Square longCastleDirection = new Square(0, 1);
    private boolean whiteKingMoved = false;
    private boolean whiteShortRookMoved = false;
    private boolean whiteLongRookMoved = false;
    private boolean blackKingMoved = false;
    private boolean blackShortRookMoved = false;
    private boolean blackLongRookMoved = false;

    public CastlingController(Position position, AttackController attackController, boolean flags) {
        this.position = position;
        this.attackController = attackController;
        setFlags(flags);
    }

    public boolean canShortCastle(Color color) {
        boolean kingChecked = attackController.kingIsInCheck(color);
        Square kingSquare = getKingSquare(color);
        boolean kingMoved = kingMoved(color);
        boolean rookMoved = rookMoved(color, Castling.SHORT);
        boolean squaresFree = squaresFree(kingSquare, Castling.SHORT);
        boolean squaresNotAttacked = squaresNotAttacked(kingSquare, color, Castling.SHORT);

        return !kingChecked && !kingMoved && !rookMoved && squaresFree && squaresNotAttacked;

    }
    public boolean canLongCastle(Color color) {
        boolean kingChecked = attackController.kingIsInCheck(color);
        Square kingSquare = getKingSquare(color);
        boolean kingMoved = kingMoved(color);
        boolean rookMoved = rookMoved(color, Castling.LONG);
        boolean squaresFree = squaresFree(kingSquare, Castling.LONG);
        boolean squaresNotAttacked = squaresNotAttacked(kingSquare, color, Castling.LONG);

        return !kingChecked && !kingMoved && !rookMoved && squaresFree && squaresNotAttacked;

    }

    public void setFlags(boolean value) {
        whiteKingMoved = value;
        whiteShortRookMoved = value;
        whiteLongRookMoved = value;
        blackKingMoved = value;
        blackShortRookMoved = value;
        blackLongRookMoved = value;
    }

    private Square getKingSquare(Color color) {
        return switch (color) {
            case WHITE -> whiteKingSquare;
            case BLACK -> blackKingSquare;
        };
    }

    private boolean kingMoved(Color color) {
        return switch (color) {
            case WHITE -> whiteKingMoved;
            case BLACK -> blackKingMoved;
        };
    }
    private boolean rookMoved(Color color, Castling type) {
        return switch (color) {
            case WHITE -> switch (type) {
                case SHORT -> whiteShortRookMoved;
                case LONG -> whiteLongRookMoved;
            };
            case BLACK -> switch (type) {
                case SHORT -> blackShortRookMoved;
                case LONG -> blackLongRookMoved;
            };
        };
    }

    private boolean squaresFree(Square kingSquare, Castling type) {
        return switch (type) {
            case SHORT -> position.isFree(kingSquare.move(shortCastleDirection))
                    && position.isFree(kingSquare.move(shortCastleDirection, 2));
            case LONG -> position.isFree(kingSquare.move(longCastleDirection))
                    && position.isFree(kingSquare.move(longCastleDirection, 2))
                    && position.isFree(kingSquare.move(longCastleDirection, 3));
        };
    }

    private boolean squaresNotAttacked(Square kingSquare, Color kingColor, Castling type) {
        Square castleDirection = switch (type) {
            case SHORT -> shortCastleDirection;
            case LONG -> longCastleDirection;
        };
        Color enemyColor = kingColor.reverse();
        return !attackController.isAttackedBy(enemyColor, kingSquare.move(castleDirection))
                && !attackController.isAttackedBy(enemyColor, kingSquare.move(castleDirection, 2));
    }

    enum Castling {
        SHORT, LONG;
    }
}
