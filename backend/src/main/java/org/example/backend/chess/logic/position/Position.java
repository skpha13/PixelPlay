package org.example.backend.chess.logic.position;

import org.example.backend.chess.logic.base.Color;
import org.example.backend.chess.logic.base.Piece;
import org.example.backend.chess.logic.base.PieceType;
import org.example.backend.chess.logic.base.Square;

import java.util.ArrayList;
import java.util.List;

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
