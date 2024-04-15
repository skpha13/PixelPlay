package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.PositionUtil

class KnightMechanics(private val position: Position, private val index: Byte) : PieceMechanics {
    override fun attackingCells(): List<Byte> {
        val attackingIndexes: MutableList<Byte> = ArrayList()
        val cell = PositionUtil.getCell(index);
        for (moveDirection in Cell.knightMoves) {
            val nextCell = cell.move(moveDirection)
            if (PositionUtil.isOnBoard(nextCell)) {
                attackingIndexes.add(PositionUtil.getindex(nextCell))
            }
        }
        return attackingIndexes
    }
}
