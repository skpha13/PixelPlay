package com.example.pixelplay.chess;

public enum Piece {
    None((byte) 0),
    WhiteKing((byte) 1),
    WhiteQueen((byte) 2),
    WhiteRook((byte) 3),
    WhiteBishop((byte) 4),
    WhiteKnight((byte) 5),
    WhitePawn((byte) 6),
    BlackKing((byte) 7),
    BlackQueen((byte) 8),
    BlackRook((byte) 9),
    BlackBishop((byte) 10),
    BlackKnight((byte) 11),
    BlackPawn((byte) 12);

    public final byte value;
    Piece(byte value) {
        this.value = value;
    }
}
