package org.example.backend.chess.logic.moving.generator;

import org.example.backend.chess.logic.moving.MoveGenerator;
import org.example.backend.chess.logic.base.Move;

import java.util.List;

public class EmptyMoveGenerator implements MoveGenerator
{
    @Override
    public List<Move> getLegalMoves() {
        return List.of();
    }
}
