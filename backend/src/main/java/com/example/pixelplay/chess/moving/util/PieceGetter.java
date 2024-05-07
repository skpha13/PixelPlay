package com.example.pixelplay.chess.moving.util;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Piece;
import com.example.pixelplay.chess.position.Position;

public class PieceGetter {
    private final Position position;

    public PieceGetter(Position position) {
        this.position = position;
    }

    public Piece tryToGetPiece(Move move) {
        Piece piece = position.getPiece(move.start());
        if (piece == null) {
            throw new EmptySquareException("No piece to move");
        }
        return piece;
    }
}