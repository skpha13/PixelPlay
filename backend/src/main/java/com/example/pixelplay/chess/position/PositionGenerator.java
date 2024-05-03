package com.example.pixelplay.chess.position;

import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.base.PieceType;

public class PositionGenerator {
    private static final String[] initialBoardStrings = new String[] {
            "RHBQKBHR",
            "PPPPPPPP",
            "........",
            "........",
            "........",
            "........",
            "pppppppp",
            "rhbqkbhr"
    };

    public static Position initialPosition() {
        return customPosition(initialBoardStrings);
    }

    public static Position customPosition(String[] boardStrings) {
        return new Position(translateBoard(boardStrings), Color.WHITE);
    }

    public static Position customPosition(String[] boardStrings, boolean castlingFlags) {
        return new Position(translateBoard(boardStrings), Color.WHITE);
    }

    public static Position customPosition(String[] boardStrings, Color whoseTurn) {
        return new Position(translateBoard(boardStrings), whoseTurn);
    }

    // Converts a list of Strings representing pieces on a board to an 2d array of pieces
    public static PieceType[][] translateBoard(String[] boardStrings) {
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


}
