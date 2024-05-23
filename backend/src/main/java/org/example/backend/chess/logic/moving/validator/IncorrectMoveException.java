package org.example.backend.chess.logic.moving.validator;

public class IncorrectMoveException extends RuntimeException {
    public IncorrectMoveException(String s) {
        super(s);
    }
}
