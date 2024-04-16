package com.example.pixelplay.chess.base;

public enum PieceType {
    None( 0),
    WhiteKing(1),
    WhiteQueen( 2),
    WhiteRook(3),
    WhiteBishop(4),
    WhiteKnight(5),
    WhitePawn(6),
    BlackKing(7),
    BlackQueen(8),
    BlackRook(9),
    BlackBishop(10),
    BlackKnight(11),
    BlackPawn(12);

    public final int value;
    PieceType(int value) {
        this.value = value;
    }

    public Color color() {
        if(this == PieceType.WhiteKing
                || this == PieceType.WhiteQueen
                || this == PieceType.WhiteKnight
                || this == PieceType.WhiteBishop
                || this == PieceType.WhiteRook
                || this == PieceType.WhitePawn) {
            return Color.WHITE;
        }

        if(this == PieceType.BlackKing
                || this == PieceType.BlackQueen
                || this == PieceType.BlackBishop
                || this == PieceType.BlackRook
                || this == PieceType.BlackKnight
                || this == PieceType.BlackPawn) {
            return Color.BLACK;
        }
        return null;
    }
}
