package com.example.pixelplay.chess;

public class PieceUtil {
    public static Color pieceColor(Piece piece) {
        if(piece == Piece.WhiteKing
                || piece == Piece.WhiteQueen
                || piece == Piece.WhiteKnight
                || piece == Piece.WhiteBishop
                || piece == Piece.WhiteRook
                || piece == Piece.WhitePawn) {
            return Color.BLACK;
        }

        if(piece == Piece.BlackKing
                || piece == Piece.BlackQueen
                || piece == Piece.BlackBishop
                || piece == Piece.BlackRook
                || piece == Piece.BlackKnight
                || piece == Piece.BlackPawn) {
            return Color.BLACK;
        }
        return null;
    }
}
