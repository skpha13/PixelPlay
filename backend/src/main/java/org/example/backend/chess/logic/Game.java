package org.example.backend.chess.logic;

import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.moving.MoveHandler;
import org.example.backend.chess.logic.moving.handler.GeneralHandler;
import org.example.backend.chess.logic.moving.validator.GeneralMoveValidator;
import org.example.backend.chess.logic.moving.MoveValidator;
import org.example.backend.chess.logic.position.Position;
import org.example.backend.chess.logic.position.PositionAnalyzer;
import org.example.backend.chess.logic.position.PositionGenerator;
import org.example.backend.chess.logic.position.Position;
import org.example.backend.chess.logic.position.PositionAnalyzer;
import org.example.backend.chess.logic.position.PositionGenerator;

public class Game {
    private final Position position;
    private PositionAnalyzer analyzer;
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

    public boolean isCheckmate() {
        PositionAnalyzer analyzer = new PositionAnalyzer(position);
        return analyzer.isCheckmate();
    }

    public boolean isStalemate() {
        PositionAnalyzer analyzer = new PositionAnalyzer(position);
        return analyzer.isStalemate();
    }
}
