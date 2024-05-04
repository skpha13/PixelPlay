package com.example.pixelplay.chess.moving.handler;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Piece;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.moving.MoveHandler;
import com.example.pixelplay.chess.position.Position;

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
