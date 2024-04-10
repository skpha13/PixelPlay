package com.example.pixelplay.chess;

import java.util.Arrays;
import java.util.Objects;

public class Position {
    private Piece[][] board = new Piece[8][8];

    private boolean whiteCanShortCastle;
    private boolean whiteCanLongCastle;
    private boolean blackCanShortCastle;
    private boolean blackCanLongCastle;

    /// Creates a starting position
    public Position() {
        setupInitialBoard(this);
        setupInitialFlags(this);
    }

    public static void setupInitialBoard(Position position) {
        position.board[0] = new Piece[]{Piece.WhiteRook, Piece.WhiteKnight, Piece.WhiteBishop, Piece.WhiteQueen, Piece.WhiteKing, Piece.WhiteBishop, Piece.WhiteKnight, Piece.WhiteRook};
        position.board[1] = new Piece[]            {Piece.WhitePawn, Piece.WhitePawn,Piece.WhitePawn,Piece.WhitePawn,Piece.WhitePawn,Piece.WhitePawn,Piece.WhitePawn,Piece.WhitePawn};
        for(int i = 2; i < 6; i ++) {
            for(int j = 0; j < 8; j ++) {
                position.board[i][j] = Piece.None;
            }
        }

        position.board[6] = new Piece[]{Piece.BlackPawn, Piece.BlackPawn,Piece.BlackPawn,Piece.BlackPawn,Piece.BlackPawn,Piece.BlackPawn,Piece.BlackPawn,Piece.BlackPawn};
        position.board[7] = new Piece[]        {Piece.BlackRook, Piece.BlackKnight, Piece.BlackBishop, Piece.BlackQueen, Piece.BlackKing, Piece.BlackBishop, Piece.BlackKnight, Piece.BlackRook};

    }

    public static void setupInitialFlags(Position position) {
        position.blackCanLongCastle = true;
        position.blackCanShortCastle = true;
        position.whiteCanLongCastle = true;
        position.whiteCanShortCastle = true;
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
