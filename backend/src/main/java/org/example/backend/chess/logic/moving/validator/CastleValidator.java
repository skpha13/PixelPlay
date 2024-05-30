package org.example.backend.chess.logic.moving.validator;

import org.example.backend.chess.logic.position.Flag;
import org.example.backend.chess.logic.position.Position;
import org.example.backend.chess.logic.base.Color;
import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.base.Piece;
import org.example.backend.chess.logic.position.AttackCalculator;
import org.example.backend.chess.logic.moving.MoveValidator;
import org.example.backend.chess.logic.moving.util.PieceGetter;
import org.example.backend.chess.logic.base.Square;
import org.example.backend.chess.logic.position.PositionUtil;

public class CastleValidator implements MoveValidator {
    private final Position position;
    private final AttackCalculator attackCalculator;

    public CastleValidator(Position position) {
        this.position = position;
        this.attackCalculator = new AttackCalculator(position);
    }

    @Override
    public boolean isValid(Move move) {
        try {
            checkRules(move);
            return true;
        }
        catch (Exception e) {
            return false;
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
        return piece.color();
    }

    public boolean canShortCastle(Color color) {
        boolean kingChecked = attackCalculator.kingIsInCheck(color);
        Square kingSquare = getKingSquare(color);
        boolean kingMoved = kingMoved(color);
        boolean rookMoved = rookMoved(color, Castle.SHORT);
        boolean squaresFree = squaresFree(kingSquare, Castle.SHORT);
        boolean squaresNotAttacked = squaresNotAttacked(kingSquare, color, Castle.SHORT);

        return !kingChecked && !kingMoved && !rookMoved && squaresFree && squaresNotAttacked;

    }
    public boolean canLongCastle(Color color) {
        boolean kingChecked = attackCalculator.kingIsInCheck(color);
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
            case WHITE -> PositionUtil.whiteKingSquare;
            case BLACK -> PositionUtil.blackKingSquare;
        };
    }

    private boolean kingMoved(Color color) {
        return switch (color) {
            case WHITE -> position.getFlag(Flag.WHITE_KING_MOVED);
            case BLACK -> position.getFlag(Flag.BLACK_KING_MOVED);
        };
    }
    private boolean rookMoved(Color color, Castle type) {
        return switch (color) {
            case WHITE -> switch (type) {
                case SHORT -> position.getFlag(Flag.WHITE_SHORT_ROOK_MOVED);
                case LONG -> position.getFlag(Flag.WHITE_LONG_ROOK_MOVED);
            };
            case BLACK -> switch (type) {
                case SHORT -> position.getFlag(Flag.BLACK_SHORT_ROOK_MOVED);
                case LONG -> position.getFlag(Flag.BLACK_LONG_ROOK_MOVED);
            };
        };
    }

    private boolean squaresFree(Square kingSquare, Castle type) {
        return switch (type) {
            case SHORT -> position.isFree(kingSquare.move(PositionUtil.shortCastleDirection))
                    && position.isFree(kingSquare.move(PositionUtil.shortCastleDirection, 2));
            case LONG -> position.isFree(kingSquare.move(PositionUtil.longCastleDirection))
                    && position.isFree(kingSquare.move(PositionUtil.longCastleDirection, 2))
                    && position.isFree(kingSquare.move(PositionUtil.longCastleDirection, 3));
        };
    }

    private boolean squaresNotAttacked(Square kingSquare, Color kingColor, Castle type) {
        Square castleDirection = switch (type) {
            case SHORT -> PositionUtil.shortCastleDirection;
            case LONG -> PositionUtil.longCastleDirection;
        };
        Color enemyColor = kingColor.reverse();
        return !attackCalculator.isAttackedBy(enemyColor, kingSquare.move(castleDirection))
                && !attackCalculator.isAttackedBy(enemyColor, kingSquare.move(castleDirection, 2));
    }

    enum Castle {
        SHORT, LONG
    }
}
