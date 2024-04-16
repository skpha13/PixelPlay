package com.example.pixelplay.chess;

import com.example.pixelplay.chess.mechanics.Square;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Position {
    private Piece[][] board = new Piece[8][8];

    private boolean whiteCanShortCastle;
    private boolean whiteCanLongCastle;
    private boolean blackCanShortCastle;
    private boolean blackCanLongCastle;

    public PieceType getPieceType(Square square) {
        return board[square.getRank()][square.getFile()].type;
    }
    public Piece getPiece(Square square) {
        return board[square.getRank()][square.getFile()];
    }

    public boolean isFree(Square square) {
        return board[square.getRank()][square.getFile()].type == PieceType.None;
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

    public void setBoard(PieceType[][] board) {
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

    public void setFlags(
            boolean whiteCanShortCastle,
            boolean whiteCanLongCastle,
            boolean blackCanShortCastle,
            boolean blackCanLongCastle
    ) {
        this.whiteCanShortCastle = whiteCanShortCastle;
        this.whiteCanLongCastle = whiteCanLongCastle;
        this.blackCanShortCastle = blackCanShortCastle;
        this.blackCanLongCastle = blackCanLongCastle;
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



    public boolean isChecked(Color color) {
        Square kingSquare = findKing(color);
        Piece king = getPiece(kingSquare);
        return king.isAttacked();
    }

    public List<Square> attackByColor(Color color) {
        List<Square> attacks = new ArrayList<>();

        for(int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j ++) {
                Piece piece = board[i][j];
                Square square = new Square(i, j);
                if(piece.type.color() == color) {
                    attacks.addAll(Objects.requireNonNull(piece.mechanics.attacks(this, square)));
                }
            }
        }
        return attacks;
    }

    private Square findKing(Color color) {
        PieceType king;
        if(color == Color.WHITE) {
            king = PieceType.WhiteKing;
        }
        else {
            king = PieceType.BlackKing;
        }

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(board[i][j].type == king) {
                    return new Square(i, j);
                }
            }
        }
        return null;
    }

    public boolean canCapture(Square square, Color attackerColor) {
        try {
            return getPiece(square).type.color() == attackerColor.reverse();
        }
        catch (Exception e) {
            return false;
        }
    }
}
