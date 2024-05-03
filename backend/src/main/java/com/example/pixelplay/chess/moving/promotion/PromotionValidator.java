package com.example.pixelplay.chess.moving.promotion;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Piece;
import com.example.pixelplay.chess.base.PieceType;
import com.example.pixelplay.chess.moving.MoveValidator;
import com.example.pixelplay.chess.position.Position;

public class PromotionValidator implements MoveValidator {
    private final Position position;

    public PromotionValidator(Position position) {
        this.position = position;
    }

    @Override
    public boolean isValid(Move move) {
        try {
            assertPawnMoved(move);
            assertMoveEndOnEdge(move);
            assertPromotionTypeIsCorrect(move);
            return true;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void assertPawnMoved(Move move) {
        Piece piece = position.getPiece(move.start());
        if(piece.type() != PieceType.Pawn) {
            throw new InvalidPromotionException("The moved piece is not a pawn");
        }
    }

    private void assertMoveEndOnEdge(Move move) {
        boolean isOnEdgeFile = (move.end().getRank() == 0 || move.end().getRank() == 7);
        if(!isOnEdgeFile) {
            throw new InvalidPromotionException("Cannot promote unless it reaches the last rank");
        }
    }
    private void assertPromotionTypeIsCorrect(Move move) {
        if(move.promotionType() == PieceType.King ||
                move.promotionType() == PieceType.Pawn ||
                move.promotionType() == PieceType.None)
            throw new InvalidPromotionException("You cannot promote to" + move.promotionType());
    }
}
