package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.Position;

import java.util.List;

public class RookMechanics extends StraightMechanics implements PieceMechanics {
    public RookMechanics(Position position, byte square) {
        super(position, square);
    }

    @Override
    List<MoveDirection> getMovingDirection() {
        return MoveDirection.axialMoves;
    }
}
