package com.example.pixelplay.chess.base;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Move implements Comparable<Move> {
    private final Square start;
    private final Square end;
    private PieceType promotionType = PieceType.None;

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

    @Override
    public int compareTo(@NotNull Move o) {
        if(this.start.compareTo(o.start()) != 0) {
            return this.start.compareTo(o.start());
        }
        if(this.end.compareTo(o.end()) != 0) {
            return this.end.compareTo(o.end());
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return Objects.equals(start, move.start) && Objects.equals(end, move.end) && promotionType == move.promotionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, promotionType);
    }
}
