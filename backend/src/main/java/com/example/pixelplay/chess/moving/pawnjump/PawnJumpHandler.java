package com.example.pixelplay.chess.moving.pawnjump;

import com.example.pixelplay.chess.position.Position;
import com.example.pixelplay.chess.base.*;
import com.example.pixelplay.chess.moving.MoveHandler;

public class PawnJumpHandler implements MoveHandler {
    private final Position position;

    public PawnJumpHandler(Position position) {
        this.position = position;
    }

    @Override
    public void makeMove(Move move) {
        Piece piece = position.getPiece(move.start());
        position.setPiece(move.end(), piece);
        position.setPiece(move.start(), new Piece());
        position.swapTurn();

        updateEnPessant(move);

    }

    private void updateEnPessant(Move move) {
        Square enPessantSquare = computeEnPessantSquare(move);
        position.setEnPessantSquare(enPessantSquare);
    }

    private Square computeEnPessantSquare(Move move) {
        return new Square(
                (move.start().getRank() + move.end().getRank())/ 2,
                (move.start().getFile() + move.end().getFile())/ 2
        );
    }
}
