package org.example.backend.chess.logic.moving.util;

public class EmptySquareException extends RuntimeException {
    public EmptySquareException(String message) {
        super(message);
    }
}
