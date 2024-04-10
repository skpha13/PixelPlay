package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.Position;
import com.example.pixelplay.chess.PositionUtil;

import java.util.ArrayList;
import java.util.List;

public class BishopMechanics extends StraightMechanics implements PieceMechanics {
    private static final List<MoveDirection> moveDirections = new ArrayList<>(
            List.of(
                    new MoveDirection(-1, -1),
                    new MoveDirection(-1, 1),
                    new MoveDirection(1, -1),
                    new MoveDirection(1, 1)
            )
    );

    public BishopMechanics(Position position, byte square) {
        super(position, square);
    }

    @Override
    protected List<MoveDirection> getMovingDirection() {
        return moveDirections;
    }
}
