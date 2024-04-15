package com.example.pixelplay.chess;

import com.example.pixelplay.chess.mechanics.PieceMechanics;

public final class Piece {
    public final PieceType type;
    public final PieceMechanics mechanics;

    public Piece(PieceType type, PieceMechanics mechanics) {
        this.type = type;
        this.mechanics = mechanics;
    }
}
