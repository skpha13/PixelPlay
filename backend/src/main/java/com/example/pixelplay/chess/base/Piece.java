package com.example.pixelplay.chess.base;

public record Piece(PieceType type) {

    public Color getColor() {
        return type.color();
    }

    public boolean isColor(Color color) {
        return type.color() == color;
    }
}
