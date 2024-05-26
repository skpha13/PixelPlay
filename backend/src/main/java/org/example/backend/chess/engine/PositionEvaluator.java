package org.example.backend.chess.engine;

import org.example.backend.chess.logic.base.Color;
import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.base.Piece;
import org.example.backend.chess.logic.base.Square;
import org.example.backend.chess.logic.moving.MoveGenerator;
import org.example.backend.chess.logic.moving.generator.PositionMoveGenerator;
import org.example.backend.chess.logic.position.Position;
import org.example.backend.chess.logic.position.PositionAnalyzer;
import org.example.backend.chess.logic.position.PositionGenerator;

public class PositionEvaluator {
    private Position position;

    public PositionEvaluator(Position position) {
        this.position = position;
    }

    //positive means the current player has advantage
    public float evaluate(){
        if(isCheckmate()){
            return -Score.Checkmate.score;
        }
        if(isStalemate()) {
            return 0f;
        }
        float score = calculateMaterialScore() + calculateMobilityScore();

        return score;
    }

    public float evaluateMove(Move move) {
        Position futurePosition = position.clone();
        futurePosition.makeMove(move);
        PositionEvaluator evaluator = new PositionEvaluator(futurePosition);
        return evaluator.evaluate();
    }

    private float calculateMaterialScore() {
        float score = 0f;
        for(int rank = 0; rank < 8; rank ++) {
            for(int file = 0; file < 8; file++) {
                Piece piece = position.getPiece(new Square(rank, file));
                if(piece.isColor(position.getTurn())) {
                    score += getPieceMaterialScore(piece);
                }
                else {
                    score -= getPieceMaterialScore(piece);
                }
            }
        }
        return score;
    }

    private float calculateMobilityScore() {
        MoveGenerator generator = new PositionMoveGenerator(position);
        float score = generator.getLegalMoves().size() * Score.Mobility.score;
        position.swapTurn();
        score -= generator.getLegalMoves().size() * Score.Mobility.score;
        position.swapTurn();
        return score;
    }

    private float getPieceMaterialScore(Piece piece) {
        return switch (piece.type()){
            case Pawn -> Score.MaterialPawn.score;
            case Bishop -> Score.MaterialBishop.score;
            case Knight -> Score.MaterialKnight.score;
            case Rook -> Score.MaterialRook.score;
            case Queen -> Score.MaterialQueen.score;
            default -> 0;
        };
    }

    private boolean isCheckmate() {
        PositionAnalyzer analyzer = new PositionAnalyzer(position);
        return analyzer.isCheckmate();
    }

    private boolean isStalemate() {
        PositionAnalyzer analyzer = new PositionAnalyzer(position);
        return analyzer.isStalemate();
    }

    private enum Score {
        MaterialPawn(1.f),
        MaterialKnight(2.8f),
        MaterialBishop(3.2f),
        MaterialRook(5f),
        MaterialQueen(9f),
        Checkmate(1000f),
        Mobility(0.1f);

        public float score;

        Score(float i) {
            score = i;
        }
    }
}
