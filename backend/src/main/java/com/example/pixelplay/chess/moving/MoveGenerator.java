package com.example.pixelplay.chess.moving;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Square;

import java.util.List;

public interface MoveGenerator {
    List<Move> getLegalMoves();
}
