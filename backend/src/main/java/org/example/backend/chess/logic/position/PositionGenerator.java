package org.example.backend.chess.logic.position;

import org.example.backend.chess.logic.base.Color;
import org.example.backend.chess.logic.base.Piece;
import org.example.backend.chess.logic.base.PieceType;

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

    public static Position customPosition(String[] boardStrings, Color whoseTurn) {
        return new Position(translateBoard(boardStrings), whoseTurn);
    }

    // Converts a list of Strings representing pieces on a board to a 2d array of pieces
    public static Piece[][] translateBoard(String[] boardStrings) {
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
        PieceType type = switch(chr) {
            case 'r', 'R' -> PieceType.Rook;
            case 'h', 'H' -> PieceType.Knight;
            case 'b', 'B' -> PieceType.Bishop;
            case 'q', 'Q' -> PieceType.Queen;
            case 'k', 'K' -> PieceType.King;
            case 'p', 'P' -> PieceType.Pawn;
            default -> PieceType.None;
        };

        Color color = Character.isLowerCase(chr) ? Color.BLACK : Color.WHITE;

        return new Piece(type, color);
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
