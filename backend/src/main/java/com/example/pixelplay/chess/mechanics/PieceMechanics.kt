package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.base.Color
import com.example.pixelplay.chess.base.Position
import com.example.pixelplay.chess.base.Square
import kotlin.math.sqrt

abstract class PieceMechanics {
    abstract fun attacks(position: Position, square: Square): List<Square>

    open fun moves(position: Position, square: Square): List<Square> {
        val attackingSquares: MutableList<Square> = attacks(position, square).toMutableList()
        val moveSquares: MutableList<Square> = mutableListOf()
        for(attackingSquare in attackingSquares) {
            if(position.isFree(attackingSquare)) {
                moveSquares.add(attackingSquare)
            }
            else if(canCapture(position, square, attackingSquare)) {
                moveSquares.add(attackingSquare)
            }
        }
        return moveSquares
    }

    fun getColor(position: Position, square: Square): Color {
        return position.getPiece(square).type.color()
    }

    fun canCapture(position: Position, start: Square, end: Square): Boolean {
        val isAnotherPiece = !position.isFree(end)
        val differentColor = position.getPieceColor(start) != position.getPieceColor(end)
        return isAnotherPiece && differentColor
    }
}
