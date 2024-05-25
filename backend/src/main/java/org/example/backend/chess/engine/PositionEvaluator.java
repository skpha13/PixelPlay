package org.example.backend.chess.engine;

import org.example.backend.chess.logic.base.Color;
import org.example.backend.chess.logic.base.Piece;
import org.example.backend.chess.logic.base.Square;
import org.example.backend.chess.logic.position.Position;
import org.example.backend.chess.logic.position.PositionAnalyzer;

public class PositionEvaluator {
    private Position position;

    public PositionEvaluator(Position position) {
        this.position = position;
    }

    public float evaluate(){
        if(isCheckmate() && position.getTurn()==Color.WHITE){
            return -Score.Checkmate.score;
        }
        if(isCheckmate() && position.getTurn()==Color.BLACK){
            return Score.Checkmate.score;
        }
        if(isStalemate()) {
            return 0f;
        }
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
        Checkmate(1000f);

        public float score;

        Score(float i) {
            score = i;
        }
    }
}
