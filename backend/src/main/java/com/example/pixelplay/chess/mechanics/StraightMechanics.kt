package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.PositionUtil

abstract class StraightMechanics(protected var position: Position, protected var square: Byte) {
    abstract val movingDirection: List<MoveDirection>

    fun attackingSquares(): List<Byte> {
        val squares: MutableList<Byte> = ArrayList()
        val rank = PositionUtil.getRank(square)
        val file = PositionUtil.getFile(square)
        for (moveDirection in movingDirection) {
            var newRank = rank + moveDirection.rank
            var newFile = file + moveDirection.file
            while (PositionUtil.isOnBoard(newRank, newFile)) {
                squares.add(PositionUtil.getSquare(newRank, newFile))
                if (!position.isFree(PositionUtil.getSquare(newRank, newFile))) {
                    break
                }
                newRank += moveDirection.rank
                newFile += moveDirection.file
            }
        }
        return squares
    }
}
