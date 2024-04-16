package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.PositionUtil

class KnightMechanics() : PieceMechanics {
    override fun attackingCells(position: Position, index: Byte): List<Byte> {
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

    override fun moves(position: Position, index: Byte): List<Byte> {
        TODO("Not yet implemented")
    }
}
