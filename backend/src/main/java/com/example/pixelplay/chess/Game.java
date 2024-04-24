package com.example.pixelplay.chess;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Position;
import com.example.pixelplay.chess.controllers.AttackController;
import com.example.pixelplay.chess.controllers.CastlingController;
import com.example.pixelplay.chess.moving.MoveValidator;
import com.example.pixelplay.chess.moving.handler.MoveHandler;
import com.example.pixelplay.chess.moving.handler.MoveHandlerSelector;
import com.example.pixelplay.chess.moving.handler.SimpleMoveHandler;

public class Game {
    private final Position position;

    private final CastlingController castlingController;

    private final AttackController attackController;

    private final MoveValidator moveController;

    public Game() {
        position = PositionGenerator.initialPosition();
        this.attackController = new AttackController(position);
        this.castlingController = new CastlingController(position, attackController, false);
        moveController = new MoveValidator(position);
    }

    public Position getPosition() {
        return position;
    }

    public void makeMove(Move move) {
        if(moveController.isValid(move)) {
            MoveHandler handler = MoveHandlerSelector.getMoveHandler(position, move);
            handler.makeMove(move);
        }
        else {
            throw new RuntimeException("Trying to make invalid move");
        }
    }
}
