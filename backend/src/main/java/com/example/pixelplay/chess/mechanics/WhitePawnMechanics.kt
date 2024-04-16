package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.PositionUtil

class WhitePawnMechanics() : PawnMechanics() {
    override fun isOnStartingCell(index: Byte): Boolean {
        return PositionUtil.getRank(index) == 1;
    }

    override fun attackingCells(position: Position, index: Byte): List<Byte> {
        val attackingIndexes: MutableList<Byte> = ArrayList()

        if (!isOnLeftEdge(index)) {
            attackingIndexes.add((index+ 7).toByte())
        }
        if (!isOnRightEdge(index)) {
            attackingIndexes.add((index+ 9).toByte())
        }

        return attackingIndexes
    }

    override fun moves(position: Position, index: Byte): List<Byte> {
        var moveIndexes: MutableList<Byte> = mutableListOf()

        val moveForwardIndex: Byte = (index + 8).toByte()
        if(position.isFree(moveForwardIndex)) {
            moveIndexes.add(moveForwardIndex)
        }

        if(isOnStartingCell(index)) {
            val moveForward2Index: Byte = (index + 16).toByte()
            if(position.isFree(moveForward2Index)) {
                moveIndexes.add(moveForward2Index)
            }
        }
        return moveIndexes

    }
}
