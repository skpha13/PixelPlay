package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.PositionUtil
import java.util.ArrayList

class KingMechanics() : PieceMechanics {

    override fun attackingCells(position: Position, index: Byte): List<Byte> {
        val attackingIndexes: MutableList<Byte> = ArrayList<Byte>().toMutableList()
        val currentCell = PositionUtil.getCell(index)
        for (moveDirection in Cell.completeMoves){
            val nextCell = currentCell.move(moveDirection)
            if(PositionUtil.isOnBoard(nextCell)) {
                attackingIndexes += PositionUtil.getindex(nextCell)
            }
        }
        return attackingIndexes
    }

    override fun moves(position: Position, index: Byte): List<Byte> {
        TODO("Not yet implemented")
    }
}
