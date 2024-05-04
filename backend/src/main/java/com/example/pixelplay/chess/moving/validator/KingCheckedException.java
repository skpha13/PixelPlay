package com.example.pixelplay.chess.moving.validator;

public class KingCheckedException extends RuntimeException {
    public KingCheckedException(String msg) {
        super(msg);
    }
}
