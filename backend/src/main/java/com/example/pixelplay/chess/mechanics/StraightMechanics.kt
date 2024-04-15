package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.PositionUtil

abstract class StraightMechanics(protected var position: Position, protected var square: Byte) {
    abstract val movingDirection: List<Cell>

    fun attackingSquares(): List<Byte> {
        val squares: MutableList<Byte> = ArrayList()
        val cell = PositionUtil.getCell(square)
        for (moveDirection in movingDirection) {
            var nextCell = cell.move(moveDirection);
            while (PositionUtil.isOnBoard(nextCell)) {
                squares.add(PositionUtil.getSquare(nextCell))
                if (!position.isFree(PositionUtil.getSquare(nextCell))) {
                    break
                }
                nextCell = nextCell.move(moveDirection)
            }
        }
        return squares
    }
}
