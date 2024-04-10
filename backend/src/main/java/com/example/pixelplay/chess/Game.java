package com.example.pixelplay.chess;

public class Game {
    private final Position position;

    public Game() {
        position = PositionGenerator.initialPosition();
    }

    public Position getPosition() {
        return position;
    }
}
