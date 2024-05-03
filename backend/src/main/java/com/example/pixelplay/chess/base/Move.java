package com.example.pixelplay.chess.base;

public record Move(Square start, Square end) {

    public Square direction() {
        return end.minus(start);
    }
}
