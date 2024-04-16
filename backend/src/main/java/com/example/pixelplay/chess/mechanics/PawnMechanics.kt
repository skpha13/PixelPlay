package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position

abstract class PawnMechanics() : PieceMechanics {
    protected fun isOnLeftEdge(index: Byte): Boolean = index% 8 == 0

    protected fun isOnRightEdge(index: Byte): Boolean = (index- 7) % 8 == 0

    abstract fun isOnStartingCell(index: Byte): Boolean
}
