package com.example.pixelplay.chess;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Position;
import com.example.pixelplay.chess.controllers.AttackController;
import com.example.pixelplay.chess.controllers.CastlingController;
import com.example.pixelplay.chess.moving.handler.MoveHandler;
import com.example.pixelplay.chess.moving.handler.MoveHandlerSelector;
import com.example.pixelplay.chess.moving.validator.GeneralMoveValidator;
import com.example.pixelplay.chess.moving.validator.MoveValidator;

public class Game {
    private final Position position;

    private CastlingController castlingController;

    private AttackController attackController;

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
        this.castlingController = new CastlingController(position, attackController, false);
    }

    public Position getPosition() {
        return position;
    }

    public void makeMove(Move move) {
        MoveValidator validator = new GeneralMoveValidator(position);
        if(validator.isValid(move)) {
            MoveHandler handler = MoveHandlerSelector.getMoveHandler(position, move);
            handler.makeMove(move);
        }
        else {
            throw new RuntimeException("Trying to make invalid move");
        }
    }
}
