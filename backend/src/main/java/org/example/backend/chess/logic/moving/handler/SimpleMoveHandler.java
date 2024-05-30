package org.example.backend.chess.logic.moving.handler;

import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.base.Piece;
import org.example.backend.chess.logic.moving.MoveHandler;
import org.example.backend.chess.logic.position.Position;

public class SimpleMoveHandler implements MoveHandler {
    private final Position position;

    public SimpleMoveHandler(Position position) {
        this.position = position;
    }

    public void makeMove(Move move) {
        Piece piece = position.getPiece(move.start());
        position.setPiece(move.end(), piece);
        position.setPiece(move.start(), new Piece());
        position.swapTurn();
    }
}
