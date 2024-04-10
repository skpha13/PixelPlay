package com.example.pixelplay.chess;

public class Game {
    private Position position;

    public Game() {
        position = PositionGenerator.initialPosition();
    }

    public Position getPosition() {
        return position;
    }
}
