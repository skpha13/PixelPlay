package com.example.pixelplay.chess.moving.promotion;

import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Piece;
import com.example.pixelplay.chess.base.PieceType;
import com.example.pixelplay.chess.moving.MoveHandler;
import com.example.pixelplay.chess.position.Position;

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
