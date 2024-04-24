package com.example.pixelplay.chess.exceptions;

public class IncorrectMoveException extends RuntimeException {
    public IncorrectMoveException(String s) {
        super(s);
    }
}
