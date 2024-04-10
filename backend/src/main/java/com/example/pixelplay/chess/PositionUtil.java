package com.example.pixelplay.chess;

public class PositionUtil {

    // Converts a list of Strings representing pieces on a board to an 2d array of pieces
    static Piece[][] translateBoard(String[] boardStrings) {
        checkSize(boardStrings);
        Piece[][] board = new Piece[8][8];
        for(int i = 0; i < 8; i ++) {
            for(int j = 0; j < 8; j ++) {
                board[i][j] = translatePiece(boardStrings[i].charAt(j));
            }
        }
        return board;
    }

    private static Piece translatePiece(char chr) {
        return switch(chr) {
            case 'r' -> Piece.BlackRook;
            case 'h' -> Piece.BlackKnight;
            case 'b' -> Piece.BlackBishop;
            case 'q' -> Piece.BlackQueen;
            case 'k' -> Piece.BlackKing;
            case 'p' -> Piece.BlackPawn;
            case 'R' -> Piece.WhiteRook;
            case 'H' -> Piece.WhiteKnight;
            case 'B' -> Piece.WhiteBishop;
            case 'Q' -> Piece.WhiteQueen;
            case 'K' -> Piece.WhiteKing;
            case 'P' -> Piece.WhitePawn;
            default -> Piece.None;
        };
    }

    private static void checkSize(String[] board) {
        if(board.length != 8) {
            throw new IllegalArgumentException("Invalid board size");
        }
        for(int i = 0; i < 8; i++) {
            if(board[i].length() != 8) {
                throw new IllegalArgumentException("Invalid board size");
            }
        }
    }

    public static int getRank(byte square) {
        return square / 8;
    }

    public static int getFile(byte square) {
        return square % 8;
    }

    public static byte getSquare(int rank, int file) {
        return (byte) (8*rank + file);
    }

    public static boolean isOnBoard(int rank, int file) {
        return rank >= 0 && rank < 8 && file >= 0 && file < 8;
    }
}
