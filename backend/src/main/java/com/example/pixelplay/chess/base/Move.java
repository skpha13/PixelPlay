package com.example.pixelplay.chess.base;

public class Move {
    private final Square start;
    private final Square end;
    public PieceType promotionType = PieceType.None;

    public Move(Square start, Square end) {
        this.start = start;
        this.end = end;
    }
    public Move(Square start, Square end, PieceType promotionType) {
        this.start = start;
        this.end = end;
        this.promotionType = promotionType;
    }

    public Square start() {
        return start;
    }

    public Square end() {
        return end;
    }

    public PieceType promotionType() {
        return promotionType;
    }

    public Square direction() {
        return end.minus(start);
    }
}
