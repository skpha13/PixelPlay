package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.PositionUtil

abstract class StraightMechanics() {
    abstract val movingDirection: List<Cell>

    fun attackingCells(position: Position, index: Byte): List<Byte> {
        val attackingIndexes: MutableList<Byte> = ArrayList()
        val cell = PositionUtil.getCell(index)
        for (moveDirection in movingDirection) {
            var nextCell = cell.move(moveDirection);
            while (PositionUtil.isOnBoard(nextCell)) {
                attackingIndexes.add(PositionUtil.getindex(nextCell))
                if (!position.isFree(PositionUtil.getindex(nextCell))) {
                    break
                }
                nextCell = nextCell.move(moveDirection)
            }
        }
        return attackingIndexes
    }
}
