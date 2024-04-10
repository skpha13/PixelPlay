package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.PositionUtil
import java.util.ArrayList

class KingMechanics(private val position: Position, private val square: Byte) : PieceMechanics {

    override fun attackingSquares(): List<Byte> {
        val attackingSquares: MutableList<Byte> = ArrayList<Byte>().toMutableList()
        val rank = PositionUtil.getRank(square)
        val file = PositionUtil.getFile(square)
        for (moveDirection in MoveDirection.completeMoves){
            val newRank = rank + moveDirection.rank
            val newFile = file + moveDirection.file
            if(PositionUtil.isOnBoard(newRank, newFile)) {
                attackingSquares += PositionUtil.getSquare(newRank, newFile)
            }
        }
        return attackingSquares
    }
}
