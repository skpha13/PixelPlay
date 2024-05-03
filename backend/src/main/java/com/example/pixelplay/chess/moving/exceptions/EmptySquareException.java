package com.example.pixelplay.chess.moving.exceptions;

public class EmptySquareException extends RuntimeException {
    public EmptySquareException(String message) {
        super(message);
    }
}
