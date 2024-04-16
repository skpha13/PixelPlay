package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.PositionUtil

abstract class PawnMechanics() : PieceMechanics {
    abstract val forwardIndex: Byte;
    abstract val leftDiagonalIndex: Byte
    abstract val rightDiagonalIndex: Byte
    abstract val startingRank: Byte;

    protected fun isOnLeftEdge(index: Byte): Boolean = index% 8 == 0

    protected fun isOnRightEdge(index: Byte): Boolean = (index- 7) % 8 == 0

    override fun attackingCells(position: Position, index: Byte): List<Byte> {
        val attackingIndexes: MutableList<Byte> = ArrayList()

        if (!isOnLeftEdge(index)) {
            attackingIndexes.add((index+ leftDiagonalIndex).toByte())
        }
        if (!isOnRightEdge(index)) {
            attackingIndexes.add((index+ rightDiagonalIndex).toByte())
        }

        return attackingIndexes
    }

    override fun moves(position: Position, index: Byte): List<Byte> {
        var moveIndexes: MutableList<Byte> = mutableListOf()

        val moveForwardIndex: Byte = (index + forwardIndex).toByte()
        if(position.isFree(moveForwardIndex)) {
            moveIndexes.add(moveForwardIndex)
        }

        if(isOnStartingCell(index)) {
            val moveForward2Index: Byte = (index + 2 * forwardIndex).toByte()
            if(position.isFree(moveForward2Index)) {
                moveIndexes.add(moveForward2Index)
            }
        }
        return moveIndexes

    }

    fun isOnStartingCell(index: Byte): Boolean {
        return PositionUtil.getRank(index).toByte() == startingRank;
    }
}
