package com.example.pixelplay.chess.moving.validator;

public class InvalidPromotionException extends RuntimeException {
    public InvalidPromotionException(String ex) {
        super(ex);
    }
}
