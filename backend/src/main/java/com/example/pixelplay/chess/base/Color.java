package com.example.pixelplay.chess.base;

public enum Color {
    WHITE, BLACK;

    public Color reverse() {
        return this == WHITE ? BLACK : WHITE;
    }
}
