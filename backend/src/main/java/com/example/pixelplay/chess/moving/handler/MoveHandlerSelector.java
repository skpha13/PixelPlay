package com.example.pixelplay.chess.moving.handler;

import com.example.pixelplay.chess.base.*;

import static java.lang.Math.abs;

public class MoveHandlerSelector {
    public static MoveHandler getMoveHandler(Position position, Move move) {
        if(isPawnJump(position, move)) {
            return new PawnJumpHandler(position);
        }
        else {
            return new SimpleMoveHandler(position);
        }
    }

    private static boolean isPawnJump(Position position, Move move) {
        Piece piece = position.getPiece(move.start);
        return isPawn(piece) && is2SquaresJump(move);
    }

    private static boolean isPawn(Piece piece) {
        return piece.getType() == PieceType.WhitePawn
                || piece.getType() == PieceType.BlackPawn;
    }

    private static boolean is2SquaresJump(Move move) {
        Square direction = move.direction();
        return abs(direction.getRank()) == 2;
    }
}
