package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position

abstract class PawnMechanics(protected val position: Position, @JvmField protected val square: Byte) : PieceMechanics {
    protected val isOnLeftEdge: Boolean
        get() = square % 8 == 0

    protected val isOnRightEdge: Boolean
        get() = (square - 7) % 8 == 0
}
