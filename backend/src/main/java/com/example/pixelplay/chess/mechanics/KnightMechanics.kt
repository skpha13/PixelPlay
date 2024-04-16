package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.PositionUtil

class KnightMechanics() : PieceMechanics() {
    override fun attacks(position: Position, square: Square): List<Square> {
        val attackingSquares: MutableList<Square> = ArrayList()
        for (moveDirection in Square.knightMoves) {
            val nextSquare = square.move(moveDirection)
            if (PositionUtil.isOnBoard(nextSquare)) {
                attackingSquares.add(nextSquare)
            }
        }
        return attackingSquares
    }
}
