package com.example.pixelplay.chess.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.abs;

public class Position {
    private final Piece[][] board = new Piece[8][8];
    private Color turn = Color.WHITE;
    private Square enPessantSquare = null;

    public Position(PieceType[][] board, boolean castlingFlags) {
        setBoard(board);
    }

    public Piece getPiece(Square square) {
        return board[square.getRank()][square.getFile()];
    }

    public boolean isFree(Square square) {
        return board[square.getRank()][square.getFile()].getType() == PieceType.None;
    }

    private boolean checkBoardSize(PieceType[][] board) {
        if (board.length != 8) {
            return false;
        }
        for(int i = 0; i < 8; i++) {
            if(board[i].length != 8) {
                return false;
            }
        }
        return true;
    }

    private void setBoard(PieceType[][] board) {
        if(!checkBoardSize(board)) {
            throw new IllegalArgumentException("Board is not valid");
        }

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                PieceType type = board[i][j];
                this.board[i][j] = createPiece(type, i, j);
            }
        }
    }

    private Piece createPiece(PieceType type, int rank, int file) {
        return new Piece(type, this, new Square(rank, file));
    }

    public Square findKing(Color color) {
        PieceType king;
        if(color == Color.WHITE) {
            king = PieceType.WhiteKing;
        }
        else {
            king = PieceType.BlackKing;
        }

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(board[i][j].getType() == king) {
                    return new Square(i, j);
                }
            }
        }
        return null;
    }

    public void makeMove(Move move) {
        Piece piece = getPiece(move.start);
        setPiece(move.end, piece);
        setPiece(move.start, new Piece(PieceType.None, this, move.start));
        nextTurn(move);
    }

    private void setPiece(Square square, Piece piece) {
        board[square.getRank()][square.getFile()] = piece;
    }

    private void nextTurn(Move move) {
        turn = turn.reverse();
        updateEnPessant(move);
    }

    private void updateEnPessant(Move move) {
        if(pawnMoved2Squares(move)) {
            enPessantSquare = computeEnPessantSquare(move);
        }
        else {
            enPessantSquare = null;
        }
    }

    private boolean pawnMoved2Squares(Move move) {
        boolean isPawn = getPiece(move.end).getType() == PieceType.WhitePawn
            || getPiece(move.end).getType() == PieceType.BlackPawn;
        boolean movedTwoSquares = abs(move.direction().getRank()) == 2;
        return isPawn && movedTwoSquares;
    }

    private Square computeEnPessantSquare(Move move) {
        Square square = new Square(
                (move.start.getRank() + move.end.getRank())/ 2,
                (move.start.getFile() + move.end.getFile())/ 2
        );
        return square;
    }

    public Color getTurn() {
        return turn;
    }

    public boolean canEnPessant(Square square) {
        return square.equals(enPessantSquare);
    }
}
