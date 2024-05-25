package org.example.backend.chess.engine;

import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.moving.MoveGenerator;
import org.example.backend.chess.logic.moving.MoveHandler;
import org.example.backend.chess.logic.moving.generator.PositionMoveGenerator;
import org.example.backend.chess.logic.moving.handler.GeneralHandler;
import org.example.backend.chess.logic.position.Position;

import java.util.List;

public class Engine {
    private final Position position;
    private final PositionEvaluator positionEvaluator;
    private final MoveGenerator moveGenerator;

    public Engine(Position position) {
        this.position = position;
        this.positionEvaluator = new PositionEvaluator(position);
        moveGenerator = new PositionMoveGenerator(position);
    }

    public Move getMove() {
        List<Move> moves = getAllPossibleMoves();
        Move bestMove = moves.getFirst();
        float maxScore = -10000f;
        for(Move move : moves) {
            Position futurePosition = position.clone();
            futurePosition.makeMove(move);

            float score = positionEvaluator.evaluate();
            if(score > maxScore) {
                maxScore = score;
                bestMove = move;
            }
        }

        return bestMove;
    }

    public List<Move> getAllPossibleMoves() {
        return moveGenerator.getLegalMoves();
    }
}
