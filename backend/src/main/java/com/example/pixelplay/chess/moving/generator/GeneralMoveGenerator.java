package com.example.pixelplay.chess.moving.generator;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.moving.MoveGenerator;
import com.example.pixelplay.chess.position.Position;

import java.util.List;

public class GeneralMoveGenerator implements MoveGenerator {
    private final Position position;
    private final Square square;

    public GeneralMoveGenerator(Position position, Square square) {
        this.position = position;
        this.square = square;
    }


    @Override
    public List<Move> getLegalMoves() {
        return List.of();
    }
}
