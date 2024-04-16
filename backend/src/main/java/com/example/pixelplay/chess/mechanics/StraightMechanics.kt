package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.PositionUtil

abstract class StraightMechanics() {
    abstract val movingDirection: List<Square>

    fun attacks(position: Position, square: Square): List<Square> {
        val attackingSquares: MutableList<Square> = ArrayList()
        for (moveDirection in movingDirection) {
            var nextSquare = square.move(moveDirection);
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
