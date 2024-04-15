package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position

class BlackPawnMechanics internal constructor() : PawnMechanics() {
    override fun attackingCells(position: Position, index: Byte): List<Byte> {
        val attackingIndexes: MutableList<Byte> = ArrayList()

        if (!isOnLeftEdge(index)) {
            attackingIndexes.add((index- 9).toByte())
        }
        if (!isOnRightEdge(index)) {
            attackingIndexes.add((index- 7).toByte())
        }

        return attackingIndexes
    }
}
