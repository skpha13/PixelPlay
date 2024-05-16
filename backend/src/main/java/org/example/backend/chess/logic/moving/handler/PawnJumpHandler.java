package org.example.backend.chess.logic.moving.handler;

import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.base.Piece;
import org.example.backend.chess.logic.base.Square;
import org.example.backend.chess.logic.position.Position;
import org.example.backend.chess.logic.moving.MoveHandler;

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
