package org.example.backend.chess.logic.mechanics

import org.example.backend.chess.logic.base.Square
import org.example.backend.chess.logic.position.Position
import org.example.backend.chess.logic.position.PositionUtil

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