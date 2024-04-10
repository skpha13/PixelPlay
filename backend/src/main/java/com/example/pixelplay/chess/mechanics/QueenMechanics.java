package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.Position;

import java.util.List;

public class QueenMechanics extends StraightMechanics implements PieceMechanics{
    private final static List<MoveDirection> moveDirections = List.of(
            new MoveDirection(-1, -1),
            new MoveDirection(-1, 0),
            new MoveDirection(-1, 1),
            new MoveDirection(0, 1),
            new MoveDirection(0, -1),
            new MoveDirection(1, -1),
            new MoveDirection(1, 0),
            new MoveDirection(1, 1)
    );

    public QueenMechanics(Position position, byte square) {
        super(position, square);
    }

    @Override
    List<MoveDirection> getMovingDirection() {
        return moveDirections;
    }
}
