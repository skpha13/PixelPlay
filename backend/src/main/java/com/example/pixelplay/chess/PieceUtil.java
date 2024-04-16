package com.example.pixelplay.chess;

import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.base.PieceType;

public class PieceUtil {
    public static Color pieceColor(PieceType piece) {
        if(piece == PieceType.WhiteKing
                || piece == PieceType.WhiteQueen
                || piece == PieceType.WhiteKnight
                || piece == PieceType.WhiteBishop
                || piece == PieceType.WhiteRook
                || piece == PieceType.WhitePawn) {
            return Color.BLACK;
        }

        if(piece == PieceType.BlackKing
                || piece == PieceType.BlackQueen
                || piece == PieceType.BlackBishop
                || piece == PieceType.BlackRook
                || piece == PieceType.BlackKnight
                || piece == PieceType.BlackPawn) {
            return Color.BLACK;
        }
        return null;
    }
}
