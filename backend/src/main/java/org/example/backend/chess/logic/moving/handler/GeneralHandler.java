package org.example.backend.chess.logic.moving.handler;

import org.example.backend.chess.logic.moving.MoveHandler;
import org.example.backend.chess.logic.moving.util.MoveType;
import org.example.backend.chess.logic.position.Position;
import org.example.backend.chess.logic.base.Move;

public class GeneralHandler implements MoveHandler {
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
            case EN_PESSANT -> new EnPessantHandler(position);
        };
    }


}
