package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.base.Position
import com.example.pixelplay.chess.base.Square
import com.example.pixelplay.chess.utils.PositionUtil

abstract class ShortRangeMechanics : PieceMechanics() {
    abstract val movingDirection: List<Square>

    override fun attacks(position: Position, square: Square): List<Square> {
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