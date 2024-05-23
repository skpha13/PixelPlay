package org.example.backend.chess.logic.base;

public enum Color {
    WHITE, BLACK;

    public Color reverse() {
        return this == WHITE ? BLACK : WHITE;
    }
}
