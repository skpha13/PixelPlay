package com.example.pixelplay.chess.moving.handler;

import com.example.pixelplay.chess.Position;
import com.example.pixelplay.chess.PositionFlags;
import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Piece;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.moving.validator.CastleValidator;

public class CastleHandler implements MoveHandler{
    private final Position position;

    public CastleHandler(Position position) {
        this.position = position;
    }


    @Override
    public void makeMove(Move move) {
        moveKing(move);
        moveRook(move);
        updateFlags(move);
    }

    private void moveKing(Move move) {
        Piece king = position.getPiece(move.start);
        Piece empty = position.getPiece(move.end);
        position.setPiece(move.end, king);
        position.setPiece(move.start, empty);
    }

    private void moveRook(Move move) {
        Square direction = getDirection(move);
        Square rookSquare = getRookSquare(move, direction);
        Square emptySquare = move.start.move(direction, 1);
        Piece rook = position.getPiece(rookSquare);
        Piece empty = position.getPiece(emptySquare);

        position.setPiece(rookSquare, empty);
        position.setPiece(emptySquare, rook);
    }

    private Square getDirection(Move move) {
        return switch (move.direction().getFile()){
            case 2 -> shortCastleDirection;
            case -2 -> longCastleDirection;
            default -> throw new IllegalStateException("Unexpected value: " + move.direction().getFile());
        };
    }

    private Square getRookSquare(Move move, Square direction) {
        return switch (direction.getFile()){
            case 1 -> move.start.move(direction, 3);
            case -1 -> move.start.move(direction, 4);
            default -> throw new IllegalStateException("Unexpected value: " + move.direction().getFile());
        };
    }

    private Castle getType(Move move) {
        if(move.direction().getFile() == 2) {
            return Castle.SHORT;
        }
        else {
            return Castle.LONG;
        }
    }

    private Color getColor(Move move) {
        return switch (move.start.getRank()) {
            case 0 -> Color.WHITE;
            case 7 -> Color.BLACK;
            default -> throw new IllegalStateException("Unexpected value: " + move.start.getRank());
        };
    }

    private void updateFlags(Move move) {
        switch (getColor(move)) {
            case WHITE -> {switch (getType(move)) {
                case SHORT -> {
                    position.setFlag(PositionFlags.Flag.WHITE_KING_MOVED, true);
                    position.setFlag(PositionFlags.Flag.WHITE_SHORT_ROOK_MOVED, true);
                }
                case LONG -> {
                    position.setFlag(PositionFlags.Flag.WHITE_KING_MOVED, true);
                    position.setFlag(PositionFlags.Flag.WHITE_LONG_ROOK_MOVED, true);
                }
            }}
            case BLACK -> {switch (getType(move)) {
                case SHORT -> {
                    position.setFlag(PositionFlags.Flag.BLACK_KING_MOVED, true);
                    position.setFlag(PositionFlags.Flag.BLACK_SHORT_ROOK_MOVED, true);
                }
                case LONG -> {
                    position.setFlag(PositionFlags.Flag.BLACK_KING_MOVED, true);
                    position.setFlag(PositionFlags.Flag.BLACK_LONG_ROOK_MOVED, true);
                }
            }}
        }
    }



    private static final Square shortCastleDirection = new Square(0, 1);
    private static final Square longCastleDirection = new Square(0, -1);

    enum Castle {
        SHORT, LONG;
    }
}
