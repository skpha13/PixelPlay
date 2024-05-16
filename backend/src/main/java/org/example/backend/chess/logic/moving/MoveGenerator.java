package org.example.backend.chess.logic.moving;

import org.example.backend.chess.logic.base.Move;

import java.util.List;

public interface MoveGenerator {
    List<Move> getLegalMoves();
}
