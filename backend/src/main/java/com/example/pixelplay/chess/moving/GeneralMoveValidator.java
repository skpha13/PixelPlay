package com.example.pixelplay.chess.moving;

import com.example.pixelplay.chess.base.*;
import com.example.pixelplay.chess.moving.exceptions.KingCheckedException;
import com.example.pixelplay.chess.position.AttackCalculator;
import com.example.pixelplay.chess.position.Position;
import com.example.pixelplay.chess.moving.exceptions.IncorrectTurnException;
import com.example.pixelplay.chess.moving.basic.SimpleMoveValidator;
import com.example.pixelplay.chess.moving.castle.CastleValidator;

import static java.lang.Math.abs;

public class GeneralMoveValidator implements MoveValidator{
    private final Position position;

    public GeneralMoveValidator(Position position) {
        this.position = position;
    }

    @Override
    public boolean isValid(Move move) {
        checkCurrentTurnColor(move);
        checkKingSafetyAfterMove(move);

        MoveValidator validator = getValidator(move);
        return validator.isValid(move);
    }

    void checkCurrentTurnColor(Move move) {
        Piece piece = position.getPiece(move.start());
        Color currentTurn = position.getTurn();
        if (piece.color() != currentTurn) {
            throw new IncorrectTurnException("It is " + currentTurn.toString() + "'s turn!");
        }
    }

    void checkKingSafetyAfterMove(Move move) {
        Piece[][] board = new Piece[8][8];
        for(int i = 0; i < 8; i ++) {
            for(int j = 0; j < 8; j ++) {
                board[i][j] = position.getPiece(new Square(i, j));
            }
        }
        Position futurePosition = new Position(board, position.getTurn());

        AttackCalculator attackCalculator = new AttackCalculator(futurePosition);
        if(attackCalculator.kingIsInCheck(position.getTurn())) {
            throw new KingCheckedException("King is checked after move");
        }
    }


    private MoveValidator getValidator(Move move) {
        if(isCastle(move)) {
            return new CastleValidator(position);
        }
        return new SimpleMoveValidator(position);
    }

    private boolean isCastle(Move move) {
        Piece piece = position.getPiece(move.start());
        boolean kingMoved = (piece.type() == PieceType.King);
        boolean movedTwoFiles = abs(move.direction().getFile()) == 2;

        return kingMoved && movedTwoFiles;
    }
}
