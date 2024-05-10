package com.example.pixelplay.chess.moving.validator;

import com.example.pixelplay.chess.PositionGenerator;
import com.example.pixelplay.chess.base.*;
import com.example.pixelplay.chess.exceptions.EmptySquareException;
import com.example.pixelplay.chess.exceptions.IncorrectTurnException;

public interface MoveValidator {
   
    boolean isValid(Move move);

    default void checkCurrentTurnColor(Piece piece, Color currentTurn) {
        if (piece.getColor() != currentTurn) {
            throw new IncorrectTurnException("It is " + currentTurn.toString() + "'s turn!");
        }
    }
}
