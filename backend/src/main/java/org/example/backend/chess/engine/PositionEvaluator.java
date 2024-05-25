package org.example.backend.chess.engine;

import org.example.backend.chess.logic.base.Color;
import org.example.backend.chess.logic.base.Piece;
import org.example.backend.chess.logic.base.Square;
import org.example.backend.chess.logic.position.Position;

public class PositionEvaluator {
    private Position position;

    public PositionEvaluator(Position position) {
        this.position = position;
    }

    public float evaluate(){
        float score = calculateMaterialScore(Color.WHITE) - calculateMaterialScore(Color.BLACK);

        return score;
    }

    private float calculateMaterialScore(Color color) {
        float score = 0f;
        for(int rank = 0; rank < 8; rank ++) {
            for(int file = 0; file < 8; file++) {
                Piece piece = position.getPiece(new Square(rank, file));
                if(piece.isColor(color)) {
                    score += getPieceMaterialScore(piece);
                }
            }
        }
        return score;
    }

    private float getPieceMaterialScore(Piece piece) {
        return switch (piece.type()){
            case Pawn -> Criteria.MaterialPawn.score;
            case Bishop -> Criteria.MaterialBishop.score;
            case Knight -> Criteria.MaterialKnight.score;
            case Rook -> Criteria.MaterialRook.score;
            case Queen -> Criteria.MaterialQueen.score;
            default -> 0;
        };
    }

    private enum Criteria {
        MaterialPawn(1.f),
        MaterialKnight(2.8f),
        MaterialBishop(3.2f),
        MaterialRook(5f),
        MaterialQueen(9f),
        Checkmate(1000f);

        public float score;

        Criteria(float i) {
            score = i;
        }
    }
}
