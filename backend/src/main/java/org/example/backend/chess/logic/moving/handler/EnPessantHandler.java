package org.example.backend.chess.logic.moving.handler;

import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.base.Piece;
import org.example.backend.chess.logic.moving.MoveHandler;
import org.example.backend.chess.logic.position.Position;
import org.example.backend.chess.logic.base.Square;

public class EnPessantHandler implements MoveHandler {
    private final Position position;

    public EnPessantHandler(Position position) {
        this.position = position;
    }

    @Override
    public void makeMove(Move move) {
        Piece piece = position.getPiece(move.start());
        Square capturedPawnSquare = new Square(move.start().getRank(), move.end().getFile());
        position.setPiece(move.start(), new Piece());
        position.setPiece(capturedPawnSquare, new Piece());
        position.setPiece(move.end(), piece);
    }
}
