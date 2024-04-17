package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.base.Position
import com.example.pixelplay.chess.utils.PositionUtil
import com.example.pixelplay.chess.base.Square

abstract class LongRangeMechanics(
    position: Position,
    square: Square
) : PieceMechanics(
    position,
    square
) {
    abstract val movingDirection: List<Square>

    override fun attacks(): List<Square> {
        val attackingSquares: MutableList<Square> = ArrayList()
        for (moveDirection in movingDirection) {
            var nextSquare = square.move(moveDirection)
            while (PositionUtil.isOnBoard(nextSquare)) {
                attackingSquares.add(nextSquare)
                if (!position.isFree(nextSquare)) {
                    break
                }
                nextSquare = nextSquare.move(moveDirection)
            }
        }
        return attackingSquares
    }
}
