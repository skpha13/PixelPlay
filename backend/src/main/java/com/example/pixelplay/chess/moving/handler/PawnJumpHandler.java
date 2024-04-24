package com.example.pixelplay.chess.moving.handler;

import com.example.pixelplay.chess.base.*;

public class PawnJumpHandler implements MoveHandler {
    private final Position position;

    public PawnJumpHandler(Position position) {
        this.position = position;
    }

    @Override
    public void makeMove(Move move) {
        Piece piece = position.getPiece(move.start);
        position.setPiece(move.end, piece);
        position.setPiece(move.start, new Piece(PieceType.None, position, move.start));
        position.swapTurn(move);

        updateEnPessant(move);

    }

    private void updateEnPessant(Move move) {
        Square enPessantSquare = computeEnPessantSquare(move);
        position.setEnPessantSquare(enPessantSquare);
    }

    private Square computeEnPessantSquare(Move move) {
        Square square = new Square(
                (move.start.getRank() + move.end.getRank())/ 2,
                (move.start.getFile() + move.end.getFile())/ 2
        );
        return square;
    }
}
