package com.example.pixelplay.chess;

import com.example.pixelplay.chess.mechanics.PieceMechanics;
import com.example.pixelplay.chess.mechanics.PieceMechanicsFactory;

public enum PieceType {
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
    PieceType(byte value) {
        this.value = value;
    }

    public Color color() {
        if(this == PieceType.WhiteKing
                || this == PieceType.WhiteQueen
                || this == PieceType.WhiteKnight
                || this == PieceType.WhiteBishop
                || this == PieceType.WhiteRook
                || this == PieceType.WhitePawn) {
            return Color.BLACK;
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
