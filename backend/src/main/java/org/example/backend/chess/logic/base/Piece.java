package org.example.backend.chess.logic.base;

public record Piece(PieceType type, Color color) {
    public Piece() {
        this(PieceType.None, Color.WHITE);
    }

    public boolean isColor(Color color) {
        return this.color == color;
    }
}
