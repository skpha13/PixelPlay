package org.example.backend.chess.logic.position;

import org.example.backend.chess.logic.base.*;
import org.example.backend.chess.logic.moving.MoveHandler;
import org.example.backend.chess.logic.moving.handler.GeneralHandler;

import java.util.ArrayList;
import java.util.List;

public class Position implements Cloneable {
    private final Piece[][] board;
    private Color turn;
    private Square enPassantSquare = null;

    private PositionFlags positionFlags = new PositionFlags();

    public Position(Piece[][] board, Color turn) {
        this.board = board;
        this.turn = turn;
    }

    public Piece getPiece(Square square) {
        try {
            return board[square.getRank()][square.getFile()];
        }
        catch (Exception ex) {
            return new Piece();
        }
    }

    public List<Square> getPieceSquares(Color color) {
        List<Square> squares = new ArrayList<>();

        for(int rank = 0; rank < 8; rank ++) {
            for(int file = 0; file < 8; file ++) {
                if(board[rank][file].color() == color) {
                    squares.add(new Square(rank, file));
                }
            }
        }

        return squares;
    }

    public boolean isFree(Square square) {
        try {
            return board[square.getRank()][square.getFile()].type() == PieceType.None;
        }
        catch (Exception e) {
            return false;
        }
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

    public void makeMove(Move move) {
        MoveHandler moveHandler = new GeneralHandler(this);
        moveHandler.makeMove(move);
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

    public void setEnPassantSquare(Square enPassantSquare) {
        this.enPassantSquare = enPassantSquare;
    }

    public boolean canEnPassant(Square square) {
        boolean enemyColor = (turn == Color.BLACK && square.getRank() == 2) || (turn == Color.WHITE && square.getRank() == 5);
        return square.equals(enPassantSquare) && enemyColor;
    }

    public boolean getFlag(Flag flag) {
        return positionFlags.getFlag(flag);
    }

    public void setFlag(Flag flag, boolean value) {
        positionFlags.setFlag(flag, value);
    }

    @Override
    public Position clone() {
        Piece[][] boardClone = new Piece[8][8];
        for(int rank = 0; rank < 8; rank ++) {
            System.arraycopy(board[rank], 0, boardClone[rank], 0, 8);
        }
        Position clone = new Position(boardClone, turn);
        clone.enPassantSquare = enPassantSquare;
        clone.positionFlags = positionFlags;

        return clone;
    }
}
