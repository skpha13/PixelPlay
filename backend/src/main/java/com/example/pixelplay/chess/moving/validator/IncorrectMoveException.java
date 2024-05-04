package com.example.pixelplay.chess.moving.validator;

public class IncorrectMoveException extends RuntimeException {
    public IncorrectMoveException(String s) {
        super(s);
    }
}
