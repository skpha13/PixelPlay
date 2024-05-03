package com.example.pixelplay.chess.moving.validator;

import com.example.pixelplay.chess.Position;
import com.example.pixelplay.chess.PositionFlags;
import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Piece;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.controllers.AttackController;
import com.example.pixelplay.chess.moving.exceptions.IncorrectCastlingException;

public class CastleValidator implements MoveValidator {
    private final Position position;
    private final AttackController attackController;

    public CastleValidator(Position position) {
        this.position = position;
        this.attackController = new AttackController(position);
    }

    @Override
    public boolean isValid(Move move) {
        try {
            Color currentTurn = position.getTurn();
            Piece piece = getPiece(move);
            checkCurrentTurnColor(piece, currentTurn); //TODO move to moveValidator
            checkRules(move);
            return true;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void checkRules(Move move) {
        Castle type = getType(move);
        Color kingColor = getKingColor(move);

        boolean ok = switch (type) {
            case SHORT -> canShortCastle(kingColor);
            case LONG -> canLongCastle(kingColor);
        };

        if(!ok) {
            throw new IncorrectCastlingException("Move does not comply to castling rules");
        }
    }

    private Castle getType(Move move) {
        if(move.direction().getFile() == 2) {
            return Castle.SHORT;
        }
        else {
            return Castle.LONG;
        }
    }

    private Color getKingColor(Move move) {
        Piece piece = getPiece(move);
        return piece.getColor();
    }

    public boolean canShortCastle(Color color) {
        boolean kingChecked = attackController.kingIsInCheck(color);
        Square kingSquare = getKingSquare(color);
        boolean kingMoved = kingMoved(color);
        boolean rookMoved = rookMoved(color, Castle.SHORT);
        boolean squaresFree = squaresFree(kingSquare, Castle.SHORT);
        boolean squaresNotAttacked = squaresNotAttacked(kingSquare, color, Castle.SHORT);

        return !kingChecked && !kingMoved && !rookMoved && squaresFree && squaresNotAttacked;

    }
    public boolean canLongCastle(Color color) {
        boolean kingChecked = attackController.kingIsInCheck(color);
        Square kingSquare = getKingSquare(color);
        boolean kingMoved = kingMoved(color);
        boolean rookMoved = rookMoved(color, Castle.LONG);
        boolean squaresFree = squaresFree(kingSquare, Castle.LONG);
        boolean squaresNotAttacked = squaresNotAttacked(kingSquare, color, Castle.LONG);

        return !kingChecked && !kingMoved && !rookMoved && squaresFree && squaresNotAttacked;

    }

    private Piece getPiece(Move move) {
        PieceGetter pieceGetter = new PieceGetter(position);
        return pieceGetter.tryToGetPiece(move);
    }

    private Square getKingSquare(Color color) {
        return switch (color) {
            case WHITE -> whiteKingSquare;
            case BLACK -> blackKingSquare;
        };
    }

    private boolean kingMoved(Color color) {
        return switch (color) {
            case WHITE -> position.getFlag(PositionFlags.Flag.WHITE_KING_MOVED);
            case BLACK -> position.getFlag(PositionFlags.Flag.BLACK_KING_MOVED);
        };
    }
    private boolean rookMoved(Color color, Castle type) {
        return switch (color) {
            case WHITE -> switch (type) {
                case SHORT -> position.getFlag(PositionFlags.Flag.WHITE_SHORT_ROOK_MOVED);
                case LONG -> position.getFlag(PositionFlags.Flag.WHITE_LONG_ROOK_MOVED);
            };
            case BLACK -> switch (type) {
                case SHORT -> position.getFlag(PositionFlags.Flag.BLACK_SHORT_ROOK_MOVED);
                case LONG -> position.getFlag(PositionFlags.Flag.BLACK_LONG_ROOK_MOVED);
            };
        };
    }

    private boolean squaresFree(Square kingSquare, Castle type) {
        return switch (type) {
            case SHORT -> position.isFree(kingSquare.move(shortCastleDirection))
                    && position.isFree(kingSquare.move(shortCastleDirection, 2));
            case LONG -> position.isFree(kingSquare.move(longCastleDirection))
                    && position.isFree(kingSquare.move(longCastleDirection, 2))
                    && position.isFree(kingSquare.move(longCastleDirection, 3));
        };
    }

    private boolean squaresNotAttacked(Square kingSquare, Color kingColor, Castle type) {
        Square castleDirection = switch (type) {
            case SHORT -> shortCastleDirection;
            case LONG -> longCastleDirection;
        };
        Color enemyColor = kingColor.reverse();
        return !attackController.isAttackedBy(enemyColor, kingSquare.move(castleDirection))
                && !attackController.isAttackedBy(enemyColor, kingSquare.move(castleDirection, 2));
    }

    private static final Square whiteKingSquare = new Square(0, 4);
    private static final Square blackKingSquare = new Square(7, 4);

    private static final Square shortCastleDirection = new Square(0, 1);
    private static final Square longCastleDirection = new Square(0, -1);

    enum Castle {
        SHORT, LONG;
    }
}