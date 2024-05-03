package com.example.pixelplay.chess.moving;

import com.example.pixelplay.chess.moving.promotion.PromotionHandler;
import com.example.pixelplay.chess.position.Position;
import com.example.pixelplay.chess.base.*;
import com.example.pixelplay.chess.moving.basic.SimpleMoveHandler;
import com.example.pixelplay.chess.moving.castle.CastleHandler;
import com.example.pixelplay.chess.moving.pawnjump.PawnJumpHandler;

import static java.lang.Math.abs;

public class GeneralHandler implements MoveHandler{
    private final Position position;

    public GeneralHandler(Position position) {
        this.position = position;
    }

    @Override
    public void makeMove(Move move) {
        MoveHandler handler = getMoveHandler(move);
        handler.makeMove(move);
    }

    private MoveHandler getMoveHandler(Move move) {
        MoveType moveType = MoveType.getMoveType(position, move);
        return switch (moveType) {
            case CASTLE -> new CastleHandler(position);
            case PAWN_JUMP -> new PawnJumpHandler(position);
            case PROMOTION -> new PromotionHandler(position);
            case BASIC -> new SimpleMoveHandler(position);
        };
    }


}
