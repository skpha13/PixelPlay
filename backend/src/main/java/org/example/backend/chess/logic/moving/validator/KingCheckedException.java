package org.example.backend.chess.logic.moving.validator;

public class KingCheckedException extends RuntimeException {
    public KingCheckedException(String msg) {
        super(msg);
    }
}
