package com.example.pixelplay.chess;

import java.util.Arrays;
import java.util.Objects;

public class Position {
    private Piece[][] board = new Piece[8][8];

    private boolean whiteCanShortCastle;
    private boolean whiteCanLongCastle;
    private boolean blackCanShortCastle;
    private boolean blackCanLongCastle;

    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    public void setFlags(
            boolean whiteCanShortCastle,
            boolean whiteCanLongCastle,
            boolean blackCanShortCastle,
            boolean blackCanLongCastle
    ) {
        this.whiteCanShortCastle = whiteCanShortCastle;
        this.whiteCanLongCastle = whiteCanLongCastle;
        this.blackCanShortCastle = blackCanShortCastle;
        this.blackCanLongCastle = blackCanLongCastle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return whiteCanShortCastle == position.whiteCanShortCastle && whiteCanLongCastle == position.whiteCanLongCastle && blackCanShortCastle == position.blackCanShortCastle && blackCanLongCastle == position.blackCanLongCastle && Objects.deepEquals(board, position.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.deepHashCode(board), whiteCanShortCastle, whiteCanLongCastle, blackCanShortCastle, blackCanLongCastle);
    }
}
