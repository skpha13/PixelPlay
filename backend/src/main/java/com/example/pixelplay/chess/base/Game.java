package com.example.pixelplay.chess.base;

import com.example.pixelplay.chess.PositionGenerator;

public class Game {
    private final Position position;

    public Game() {
        position = PositionGenerator.initialPosition();
    }

    public Position getPosition() {
        return position;
    }
}
