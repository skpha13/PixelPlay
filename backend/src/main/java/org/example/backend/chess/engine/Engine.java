package org.example.backend.chess.engine;

import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.moving.MoveGenerator;
import org.example.backend.chess.logic.moving.generator.PositionMoveGenerator;
import org.example.backend.chess.logic.position.Position;

import java.util.Comparator;
import java.util.List;

import static java.lang.Math.*;

public class Engine {
    private static final int MAX_DEPTH = 1;
    private static int count = 0;



    public static Move getMove(Position position) {
        count = 0;
        List<Move> moves = getAllPossibleMoves(position);
        Move bestMove = moves.get(0);
        float maxScore = -10000f;
        for(Move move : moves) {
            Position futurePosition = position.clone();
            futurePosition.makeMove(move);

//            float score = alphaBeta( futurePosition, Float.MIN_VALUE, Float.MAX_VALUE, MAX_DEPTH, 1);
            float score = negaMax(MAX_DEPTH, futurePosition);
            if(score > maxScore) {
                maxScore = score;
                bestMove = move;
            }
        }
        System.out.println("Number:  " + count);
        return bestMove;
    }

    public static List<Move> getAllPossibleMoves(Position position) {
        MoveGenerator moveGenerator = new PositionMoveGenerator(position);
        List<Move> moves =  moveGenerator.getLegalMoves();
        moves.sort(new Comparator<>() {
            final PositionEvaluator evaluator = new PositionEvaluator(position);

            @Override
            public int compare(Move o1, Move o2) {
                return evaluator.evaluateMove(o1) > evaluator.evaluateMove(o2) ? 1 : -1;
            }
        });

        return moves.subList(0, min(1, moves.size()));
    }

    private static float negaMax(int depth, Position position) {
        count ++;
        if(depth == 0) {
            PositionEvaluator evaluator = new PositionEvaluator(position);
            return evaluator.evaluate();
        }
        float max = Float.MIN_VALUE;
        for(Move move : getAllPossibleMoves(position)) {
            Position futurePosition = position.clone();
            futurePosition.makeMove(move);
            float score = -negaMax(depth-1, futurePosition);
            if(score > max) {
                max = score;
            }
        }
        return max;
    }
}
