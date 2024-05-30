package org.example.backend.chess.logic.moving.handler;

import org.example.backend.chess.logic.moving.MoveHandler;
import org.example.backend.chess.logic.base.Color;
import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.base.Piece;
import org.example.backend.chess.logic.base.PieceType;
import org.example.backend.chess.logic.position.Position;

public class PromotionHandler implements MoveHandler {
    private final Position position;

    public PromotionHandler(Position position) {
        this.position = position;
    }

    @Override
    public void makeMove(Move move) {
        Piece newPiece = createNewPiece(move);
        Piece emptyPiece = new Piece();
        position.setPiece(move.end(), newPiece);
        position.setPiece(move.start(), emptyPiece);
        position.swapTurn();
    }



    private Piece createNewPiece(Move move) {
        Color color = position.getPiece(move.start()).color();
        PieceType type = move.promotionType();
        return new Piece(type, color);
    }
}
