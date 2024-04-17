package com.example.pixelplay.chess.base;

public final class Move {
    public final Square start;
    public final Square end;

    public Move(Square start, Square end) {
        this.start = start;
        this.end = end;
    }
}
