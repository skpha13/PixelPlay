package org.example.backend.chess.logic.moving.generator;

import org.example.backend.chess.logic.moving.MoveGenerator;
import org.example.backend.chess.logic.base.Color;
import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.base.Square;
import org.example.backend.chess.logic.position.Position;

import java.util.ArrayList;
import java.util.List;

public class PositionMoveGenerator implements MoveGenerator {
    private final Position position;

    public PositionMoveGenerator(Position position) {
        this.position = position;
    }

    @Override
    public List<Move> getLegalMoves() {
        List<Move> moves = new ArrayList<>();

        for(Square square: getCurrentTurnPieceSquares()) {
            MoveGenerator generator = new PieceMoveGenerator(position, square);
            moves.addAll(generator.getLegalMoves());
        }

        return moves;
    }

    private List<Square> getCurrentTurnPieceSquares() {
        Color currentTurn = position.getTurn();
        return position.getPieceSquares(currentTurn);
    }
}
