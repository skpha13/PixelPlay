package com.example.pixelplay.chess.moving.validator;

import com.example.pixelplay.chess.base.*;
import com.example.pixelplay.chess.exceptions.IncorrectMoveException;
import com.example.pixelplay.chess.mechanics.PieceMechanics;
import com.example.pixelplay.chess.mechanics.PieceMechanicsFactory;

import java.util.List;

public class SimpleMoveValidator implements MoveValidator {

    @Override
    public boolean isValid(Position position, Move move) {
        try {
            Color currentTurn = position.getTurn();
            Piece piece = tryToGetPiece(position, move);
            checkCurrentTurnColor(piece, currentTurn);
            checkMoveIsInPieceMoves(piece, move);
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    void checkMoveIsInPieceMoves(Piece piece, Move move) {
        PieceMechanics mechanics = PieceMechanicsFactory.getPieceMechanics(piece);
        List<Square> targetSquares = mechanics.moves();
        if (!targetSquares.stream().anyMatch(square -> square.equals(move.end))) {
            throw new IncorrectMoveException("Move does not respect piece rules");
        }
    }

}
