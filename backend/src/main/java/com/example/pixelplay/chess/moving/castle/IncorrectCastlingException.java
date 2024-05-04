package com.example.pixelplay.chess.moving.castle;

public class IncorrectCastlingException extends RuntimeException {
    public IncorrectCastlingException(String msg) {
        super(msg);
    }
}
