package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.Position;

import java.util.ArrayList;
import java.util.List;

public class QueenMechanics extends StraightMechanics implements PieceMechanics{


    public QueenMechanics(Position position, byte square) {
        super(position, square);
    }

    @Override
    List<MoveDirection> getMovingDirection() {
        List<MoveDirection> movingDirections = new ArrayList<>();
        movingDirections.addAll(MoveDirection.axialMoves);
        movingDirections.addAll(MoveDirection.diagonalMoves);
        return movingDirections;
    }
}
