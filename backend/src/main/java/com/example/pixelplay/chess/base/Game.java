package com.example.pixelplay.chess.base;

import com.example.pixelplay.chess.PositionGenerator;
import com.example.pixelplay.chess.controllers.AttackController;
import com.example.pixelplay.chess.controllers.CastlingController;

public class Game {
    private final Position position;

    private final CastlingController castlingController;

    private final AttackController attackController;

    public Game() {
        position = PositionGenerator.initialPosition();
        this.attackController = new AttackController(position);
        this.castlingController = new CastlingController(position, attackController, false);
    }

    public Position getPosition() {
        return position;
    }
}
