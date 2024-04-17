package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.base.Position
import com.example.pixelplay.chess.base.Square
import com.example.pixelplay.chess.utils.PositionUtil

abstract class ShortRangeMechanics(
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
            val nextSquare = square.move(moveDirection)
            if(PositionUtil.isOnBoard(nextSquare)) {
                attackingSquares.add(nextSquare)
            }
        }
        return attackingSquares
    }
}