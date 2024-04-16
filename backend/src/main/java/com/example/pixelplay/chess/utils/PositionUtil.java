package com.example.pixelplay.chess.utils;

import com.example.pixelplay.chess.base.PieceType;
import com.example.pixelplay.chess.base.Square;

public class PositionUtil {

    // Converts a list of Strings representing pieces on a board to an 2d array of pieces
    static PieceType[][] translateBoard(String[] boardStrings) {
        checkSize(boardStrings);
        PieceType[][] board = new PieceType[8][8];
        for(int i = 0; i < 8; i ++) {
            for(int j = 0; j < 8; j ++) {
                board[i][j] = translatePiece(boardStrings[i].charAt(j));
            }
        }
        return board;
    }

    private static PieceType translatePiece(char chr) {
        return switch(chr) {
            case 'r' -> PieceType.BlackRook;
            case 'h' -> PieceType.BlackKnight;
            case 'b' -> PieceType.BlackBishop;
            case 'q' -> PieceType.BlackQueen;
            case 'k' -> PieceType.BlackKing;
            case 'p' -> PieceType.BlackPawn;
            case 'R' -> PieceType.WhiteRook;
            case 'H' -> PieceType.WhiteKnight;
            case 'B' -> PieceType.WhiteBishop;
            case 'Q' -> PieceType.WhiteQueen;
            case 'K' -> PieceType.WhiteKing;
            case 'P' -> PieceType.WhitePawn;
            default -> PieceType.None;
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

    public static int getRank(byte index) {
        return index/ 8;
    }

    public static int getFile(byte index) {
        return index% 8;
    }

    public static Square getSquare(byte index) {
        return new Square(getRank(index), getFile(index));
    }

    public static boolean isOnBoard(int rank, int file) {
        return rank >= 0 && rank < 8 && file >= 0 && file < 8;
    }

    public static boolean isOnBoard(Square square) {
        return square.getRank() >= 0 && square.getRank() < 8 && square.getFile() >= 0 && square.getFile() < 8;
    }
}
