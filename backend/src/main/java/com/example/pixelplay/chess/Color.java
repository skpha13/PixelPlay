package com.example.pixelplay.chess;

public enum Color {
    WHITE, BLACK;

    Color reverse() {
        return this == WHITE ? BLACK : WHITE;
    }
}
