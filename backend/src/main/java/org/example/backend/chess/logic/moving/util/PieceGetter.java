package org.example.backend.chess.logic.moving.util;

import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.base.Piece;
import org.example.backend.chess.logic.position.Position;

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
