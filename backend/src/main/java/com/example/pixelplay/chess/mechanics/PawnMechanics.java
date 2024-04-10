package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.Position;

abstract class PawnMechanics implements PieceMechanics {
    protected final Position position;
    protected final byte square;

    PawnMechanics(Position position, byte square) {
        this.position = position;
        this.square = square;
    }

    protected boolean isOnLeftEdge() {
        return square % 8 == 0;
    }

    protected boolean isOnRightEdge() {
        return (square - 7) % 8 == 0;
    }
}
