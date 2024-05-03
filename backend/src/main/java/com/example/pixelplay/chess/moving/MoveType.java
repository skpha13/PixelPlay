package com.example.pixelplay.chess.moving;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Piece;
import com.example.pixelplay.chess.base.PieceType;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.position.Position;

import static java.lang.Math.abs;

public enum MoveType {
    BASIC,
    CASTLE,
    PROMOTION,
    PAWN_JUMP;

    public static MoveType getMoveType(Position position, Move move) {
        if(isCastle(position, move)) {
            return CASTLE;
        }
        if(isPromotion(position, move)) {
            return PROMOTION;
        }
        if(isPawnJump(position, move)) {
            return PAWN_JUMP;
        }
        return BASIC;
    }

    private static boolean isCastle(Position position, Move move) {
        Piece piece = position.getPiece(move.start());
        boolean kingMoved = (piece.type() == PieceType.King);
        boolean movedTwoFiles = abs(move.direction().getFile()) == 2;

        return kingMoved && movedTwoFiles;
    }

    private static boolean isPawnJump(Position position, Move move) {
        Piece piece = position.getPiece(move.start());
        boolean isPawn = piece.type() == PieceType.Pawn;
        boolean isTwoSquaresJump = abs(move.direction().getRank()) == 2;
        return isPawn && isTwoSquaresJump;
    }

    private static boolean isPromotion(Position position, Move move) {
        Piece piece = position.getPiece(move.start());
        boolean isPawn = (piece.type() == PieceType.Pawn);
        boolean movedOnLastFile = (move.end().getRank() == 0 || move.end().getRank() == 7);

        return isPawn && movedOnLastFile;
    }
}
