package org.example.backend.chess.logic.moving.validator;

public class IncorrectCastlingException extends RuntimeException {
    public IncorrectCastlingException(String msg) {
        super(msg);
    }
}
