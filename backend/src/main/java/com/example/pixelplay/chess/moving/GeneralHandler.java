package com.example.pixelplay.chess.moving;

import com.example.pixelplay.chess.position.Position;
import com.example.pixelplay.chess.base.*;
import com.example.pixelplay.chess.moving.basic.SimpleMoveHandler;
import com.example.pixelplay.chess.moving.castle.CastleHandler;
import com.example.pixelplay.chess.moving.pawnjump.PawnJumpHandler;

import static java.lang.Math.abs;

public class GeneralHandler implements MoveHandler{
    private final Position position;

    public GeneralHandler(Position position) {
        this.position = position;
    }

    @Override
    public void makeMove(Move move) {
        MoveHandler handler = getMoveHandler(move);
        handler.makeMove(move);
    }

    private MoveHandler getMoveHandler(Move move) {
        if(isCastle(position, move)){
            return new CastleHandler(position);
        }
        else if(isPawnJump(position, move)) {
            return new PawnJumpHandler(position);
        }
        else {
            return new SimpleMoveHandler(position);
        }
    }

    private static boolean isCastle(Position position, Move move) {
        Piece piece = position.getPiece(move.start());
        boolean kingMoved = (piece.type() == PieceType.BlackKing) || (piece.type() == PieceType.WhiteKing);
        boolean movedTwoFiles = abs(move.direction().getFile()) == 2;

        return kingMoved && movedTwoFiles;
    }

    private static boolean isPawnJump(Position position, Move move) {
        Piece piece = position.getPiece(move.start());
        return isPawn(piece) && is2SquaresJump(move);
    }

    private static boolean isPawn(Piece piece) {
        return piece.type() == PieceType.WhitePawn
                || piece.type() == PieceType.BlackPawn;
    }

    private static boolean is2SquaresJump(Move move) {
        Square direction = move.direction();
        return abs(direction.getRank()) == 2;
    }
}
