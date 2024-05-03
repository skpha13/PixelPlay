package com.example.pixelplay.chess;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.controllers.AttackController;
import com.example.pixelplay.chess.moving.MoveHandler;
import com.example.pixelplay.chess.moving.GeneralHandler;
import com.example.pixelplay.chess.moving.GeneralMoveValidator;
import com.example.pixelplay.chess.moving.MoveValidator;
import com.example.pixelplay.chess.position.Position;
import com.example.pixelplay.chess.position.PositionGenerator;

public class Game {
    private final Position position;


    private AttackController attackController;
    private MoveHandler moveHandler;
    private MoveValidator moveValidator;

    public Game() {
        position = PositionGenerator.initialPosition();
        init();
    }

    public Game(Position position) {
        this.position = position;
        init();
    }

    private void init() {
        this.attackController = new AttackController(position);
        this.moveValidator = new GeneralMoveValidator(position);
        this.moveHandler = new GeneralHandler(position);
    }

    public Position getPosition() {
        return position;
    }

    public void makeMove(Move move) {
        if(moveValidator.isValid(move)) {
            moveHandler.makeMove(move);
        }
        else {
            throw new RuntimeException("Trying to make invalid move");
        }
    }
}
