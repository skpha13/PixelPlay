package com.example.pixelplay.chess.moving;

import com.example.pixelplay.chess.base.Move;

import java.util.List;

public interface PieceMoveGenerator {
    List<Move> getLegalMoves();
}
