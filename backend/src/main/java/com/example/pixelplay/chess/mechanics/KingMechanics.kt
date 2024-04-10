package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.PositionUtil

class KingMechanics(private val position: Position, private val square: Byte) : PieceMechanics {

    override fun attackingSquares(): List<Byte> {
        var attackingSquares: List<Byte> = ArrayList()
        var rank = PositionUtil.getRank(square)
        var file = PositionUtil.getFile(square)
        for (moveDirection in MoveDirection.completeMoves){
            var newRank = rank + moveDirection.rank;
            var newFile = file + moveDirection.file;
            if(PositionUtil.isOnBoard(newRank, newFile)) {
                attackingSquares += PositionUtil.getSquare(newRank, newFile)
            }
        }
        return attackingSquares
    }
}
