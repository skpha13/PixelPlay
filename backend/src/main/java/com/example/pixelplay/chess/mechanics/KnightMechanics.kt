package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.PositionUtil

class KnightMechanics() : PieceMechanics {
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

    override fun moves(position: Position, square: Square): List<Square> {
        val attackingSquares: List<Square> = attacks(position, square);
        val moveSquares: MutableList<Square> = mutableListOf()
        for(attackingSquare in attackingSquares) {
            if(position.isFree(attackingSquare)) {
                moveSquares.add(attackingSquare)
            }
            else if(position.canCapture(attackingSquare, square)) {
                moveSquares.add(attackingSquare)
            }
        }
        return moveSquares
    }
}
