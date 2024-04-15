package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position

class BlackPawnMechanics internal constructor(position: Position, index: Byte) : PawnMechanics(position, index) {
    override fun attackingCells(): List<Byte> {
        val attackingIndexes: MutableList<Byte> = ArrayList()

        if (!isOnLeftEdge) {
            attackingIndexes.add((index- 9).toByte())
        }
        if (!isOnRightEdge) {
            attackingIndexes.add((index- 7).toByte())
        }

        return attackingIndexes
    }
}
