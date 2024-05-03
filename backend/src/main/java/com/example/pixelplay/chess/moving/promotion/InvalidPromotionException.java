package com.example.pixelplay.chess.moving.promotion;

public class InvalidPromotionException extends RuntimeException {
    public InvalidPromotionException(String ex) {
        super(ex);
    }
}
