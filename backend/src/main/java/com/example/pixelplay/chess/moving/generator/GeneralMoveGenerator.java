package com.example.pixelplay.chess.moving.generator;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.moving.PieceMoveGenerator;
import com.example.pixelplay.chess.position.Position;

import java.util.List;

public class GeneralMoveGenerator implements PieceMoveGenerator {
    private final Position position;

    public GeneralMoveGenerator(Position position) {
        this.position = position;
    }


    @Override
    public List<Move> getLegalMoves() {
        return List.of();
    }


}
