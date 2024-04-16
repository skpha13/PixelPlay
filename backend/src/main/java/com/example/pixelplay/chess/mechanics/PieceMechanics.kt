package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position

abstract class PieceMechanics {
    abstract fun attacks(position: Position, square: Square): List<Square>

    open fun moves(position: Position, square: Square): List<Square> {
        val attackingSquares: MutableList<Square> = attacks(position, square).toMutableList()
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
