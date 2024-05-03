package com.example.pixelplay.chess.position;

import com.example.pixelplay.chess.base.*;

public class Position {
    private final Piece[][] board = new Piece[8][8];
    private Color turn = Color.WHITE;
    private Square enPessantSquare = null;

    private final PositionFlags positionFlags = new PositionFlags();

    public Position(PieceType[][] board, Color turn) {
        setBoard(board);
        this.turn = turn;
    }

    public Piece getPiece(Square square) {
        return board[square.getRank()][square.getFile()];
    }

    public boolean isFree(Square square) {
        return board[square.getRank()][square.getFile()].type() == PieceType.None;
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
                if(board[i][j].type() == king) {
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
