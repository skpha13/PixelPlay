package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.PositionUtil
import java.util.ArrayList

class KingMechanics(private val position: Position, private val square: Byte) : PieceMechanics {

    override fun attackingSquares(): List<Byte> {
        val attackingSquares: MutableList<Byte> = ArrayList<Byte>().toMutableList()
        val currentCell = PositionUtil.getCell(square)
        for (moveDirection in Cell.completeMoves){
            val nextCell = currentCell.move(moveDirection)
            if(PositionUtil.isOnBoard(nextCell)) {
                attackingSquares += PositionUtil.getSquare(nextCell)
            }
        }
        return attackingSquares
    }
}
