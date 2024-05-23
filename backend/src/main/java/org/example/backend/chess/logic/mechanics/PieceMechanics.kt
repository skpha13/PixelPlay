package org.example.backend.chess.logic.mechanics

import org.example.backend.chess.logic.base.Color
import org.example.backend.chess.logic.base.Square
import org.example.backend.chess.logic.position.Position

abstract class PieceMechanics(
    protected val position: Position,
    protected val square: Square,
) {
    abstract fun attacks(): List<Square>

    open fun moves(): List<Square> {
        val attackingSquares: MutableList<Square> = attacks().toMutableList()
        val moveSquares: MutableList<Square> = mutableListOf()
        for(attackingSquare in attackingSquares) {
            if(position.isFree(attackingSquare)) {
                moveSquares.add(attackingSquare)
            }
            else if(canCapture(attackingSquare)) {
                moveSquares.add(attackingSquare)
            }
        }
        return moveSquares
    }

    fun getColor(): Color {
        return position.getPiece(square).color
    }

    fun canCapture(target: Square): Boolean {
        try {
            val isAnotherPiece = !position.isFree(target)
            val differentColor = position.getPiece(square).color != position.getPiece(target).color
            return isAnotherPiece && differentColor
        }
        catch (e: Exception) {
            return false
        }
    }
}
