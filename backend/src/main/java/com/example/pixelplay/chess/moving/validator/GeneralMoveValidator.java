package com.example.pixelplay.chess.moving.validator;

import com.example.pixelplay.chess.base.*;
import com.example.pixelplay.chess.moving.MoveHandler;
import com.example.pixelplay.chess.moving.util.MoveType;
import com.example.pixelplay.chess.moving.MoveValidator;
import com.example.pixelplay.chess.moving.handler.GeneralHandler;
import com.example.pixelplay.chess.position.AttackCalculator;
import com.example.pixelplay.chess.position.Position;

public class GeneralMoveValidator implements MoveValidator {
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
        MoveHandler handler = new GeneralHandler(futurePosition);
        handler.makeMove(move);

        AttackCalculator attackCalculator = new AttackCalculator(futurePosition);
        if(attackCalculator.kingIsInCheck(position.getTurn())) {
            throw new KingCheckedException("King is checked after move");
        }
    }


    private MoveValidator getValidator(Move move) {
        MoveType moveType = MoveType.getMoveType(position, move);
        return switch (moveType) {
            case CASTLE -> new CastleValidator(position);
            case BASIC, PAWN_JUMP -> new SimpleMoveValidator(position);
            case PROMOTION -> new PromotionValidator(position);
            case EN_PESSANT -> new EnPessantValidator(position);
        };
    }
}
