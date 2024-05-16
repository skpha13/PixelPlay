package org.example.backend.chess.logic.moving.validator;

public class IncorrectTurnException extends RuntimeException {
    public IncorrectTurnException(String message, Throwable err) {
        super(message, err);
    }
    public IncorrectTurnException(String message) {
        super(message);
    }
}
