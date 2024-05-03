package com.example.pixelplay.chess.moving.basic;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Piece;
import com.example.pixelplay.chess.base.PieceType;
import com.example.pixelplay.chess.position.Position;
import com.example.pixelplay.chess.moving.MoveHandler;

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
