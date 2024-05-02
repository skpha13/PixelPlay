package com.example.pixelplay.chess.moving.validator;

import com.example.pixelplay.chess.base.*;
import com.example.pixelplay.chess.exceptions.EmptySquareException;
import com.example.pixelplay.chess.exceptions.IncorrectTurnException;

public interface MoveValidator {
    boolean isValid(Position position, Move move);

    default Piece tryToGetPiece(Position position, Move move) {
        Piece piece = position.getPiece(move.start);
        if (piece == null) {
            throw new EmptySquareException("No piece to move");
        }
        return piece;
    }

    default void checkCurrentTurnColor(Piece piece, Color currentTurn) {
        if (piece.getColor() != currentTurn) {
            throw new IncorrectTurnException("It is " + currentTurn.toString() + "'s turn!");
        }
    }
}
