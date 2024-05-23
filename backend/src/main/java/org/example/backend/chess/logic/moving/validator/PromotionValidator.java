package org.example.backend.chess.logic.moving.validator;

import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.base.Piece;
import org.example.backend.chess.logic.base.PieceType;
import org.example.backend.chess.logic.moving.MoveValidator;
import org.example.backend.chess.logic.position.Position;
import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.base.Piece;
import org.example.backend.chess.logic.base.PieceType;
import org.example.backend.chess.logic.position.Position;

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
