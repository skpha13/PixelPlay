package com.example.pixelplay.chess.base;

import com.example.pixelplay.chess.base.attacks.AttackController;
import com.example.pixelplay.chess.base.attacks.NumberBoard;
import com.example.pixelplay.chess.mechanics.CastlingController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {
    private final Piece[][] board = new Piece[8][8];

    private final CastlingController castlingController;

    private final AttackController attackController;

    public Position(PieceType[][] board, boolean castlingFlags) {
        setBoard(board);

        castlingController = new CastlingController(this, castlingFlags);
        attackController = new AttackController(this);
    }


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


    public boolean isChecked(Color color) {
        Square kingSquare = findKing(color);
        assert kingSquare != null;
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
                    assert piece.mechanics != null;
                    attacks.addAll(Objects.requireNonNull(piece.mechanics.attacks(this, square)));
                }
            }
        }
        return attacks;
    }

    public NumberBoard getAttackBoardByColor(Color color) {
        return attackController.getAttackBoard(color);
    }

    public boolean isAttackedBy(Color attacker, Square square) {
        return attackController.isAttackedBy(attacker, square);
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
    public boolean canCapture(Square targetSquare, Square initialSquare) {
        try {
            return getPiece(targetSquare).type.color() == getPiece(initialSquare).type.color().reverse();
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean canShortCastle(Color color) {
        return castlingController.canShortCastle(color);
    }

    public boolean canLongCastle(Color color) {
        return castlingController.canLongCastle(color);
    }
}
