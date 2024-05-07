package com.example.pixelplay.chess.moving.generator;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.moving.MoveGenerator;

import java.util.List;

public class EmptyMoveGenerator implements MoveGenerator
{
    @Override
    public List<Move> getLegalMoves() {
        return List.of();
    }
}
