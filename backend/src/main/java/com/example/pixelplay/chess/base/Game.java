package com.example.pixelplay.chess.base;

import com.example.pixelplay.chess.PositionGenerator;
import com.example.pixelplay.chess.controllers.AttackController;
import com.example.pixelplay.chess.controllers.CastlingController;
import com.example.pixelplay.chess.controllers.MoveController;

public class Game {
    private final Position position;

    private final CastlingController castlingController;

    private final AttackController attackController;

    private final MoveController moveController;

    public Game() {
        position = PositionGenerator.initialPosition();
        this.attackController = new AttackController(position);
        this.castlingController = new CastlingController(position, attackController, false);
        moveController = new MoveController(position, attackController, castlingController);
    }

    public Position getPosition() {
        return position;
    }

    public void makeMove(Move move) {
        if(moveController.isValid(move)) {
            position.makeMove(move);
        }
        else {
            throw new RuntimeException("Trying to make invalid move");
        }
    }
}
