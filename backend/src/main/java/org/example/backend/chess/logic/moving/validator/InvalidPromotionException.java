package org.example.backend.chess.logic.moving.validator;

public class InvalidPromotionException extends RuntimeException {
    public InvalidPromotionException(String ex) {
        super(ex);
    }
}
