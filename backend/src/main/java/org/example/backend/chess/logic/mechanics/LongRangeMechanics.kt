package org.example.backend.chess.logic.mechanics

import org.example.backend.chess.logic.base.Square
import org.example.backend.chess.logic.position.Position
import org.example.backend.chess.logic.position.PositionUtil


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
