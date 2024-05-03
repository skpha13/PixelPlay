package com.example.pixelplay.chess.position;

import com.example.pixelplay.chess.base.*;

public class Position {
    private final Piece[][] board;
    private Color turn;
    private Square enPessantSquare = null;

    private final PositionFlags positionFlags = new PositionFlags();

    public Position(Piece[][] board, Color turn) {
        this.board = board;
        this.turn = turn;
    }

    public Piece getPiece(Square square) {
        return board[square.getRank()][square.getFile()];
    }

    public boolean isFree(Square square) {
        return board[square.getRank()][square.getFile()].type() == PieceType.None;
    }

    public Square findKing(Color color) {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(board[i][j].type() == PieceType.King && board[i][j].color() == color) {
                    return new Square(i, j);
                }
            }
        }
        return null;
    }

    public void setPiece(Square square, Piece piece) {
        board[square.getRank()][square.getFile()] = piece;
    }

    public void swapTurn() {
        turn = turn.reverse();
    }

    public Color getTurn() {
        return turn;
    }

    public void setEnPessantSquare(Square enPessantSquare) {
        this.enPessantSquare = enPessantSquare;
    }

    public boolean canEnPessant(Square square) {
        return square.equals(enPessantSquare);
    }

    public boolean getFlag(Flag flag) {
        return positionFlags.getFlag(flag);
    }

    public void setFlag(Flag flag, boolean value) {
        positionFlags.setFlag(flag, value);
    }
}
