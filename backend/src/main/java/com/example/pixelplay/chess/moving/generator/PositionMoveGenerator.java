package com.example.pixelplay.chess.moving.generator;

import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.moving.MoveGenerator;
import com.example.pixelplay.chess.position.Position;

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
