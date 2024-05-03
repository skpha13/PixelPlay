package com.example.pixelplay.chess.base;

import com.example.pixelplay.chess.position.Position;

public record Piece(PieceType type, Position position, Square square) {

    public Color getColor() {
        return type.color();
    }

    public boolean isColor(Color color) {
        return type.color() == color;
    }
}
