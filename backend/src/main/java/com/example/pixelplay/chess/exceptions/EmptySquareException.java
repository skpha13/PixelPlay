package com.example.pixelplay.chess.exceptions;

public class EmptySquareException extends RuntimeException {
    public EmptySquareException(String message) {
        super(message);
    }
}
