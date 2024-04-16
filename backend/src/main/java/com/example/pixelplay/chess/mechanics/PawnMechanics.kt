package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position

abstract class PawnMechanics() : PieceMechanics {
    abstract val forwardMove: Square;
    abstract val leftDiagonalMove: Square
    abstract val rightDiagonalMove: Square
    abstract val startingRank: Int;

    protected fun isOnLeftEdge(square: Square): Boolean = square.getFile() == 0

    protected fun isOnRightEdge(square: Square): Boolean = square.getFile() == 7

    override fun attacks(position: Position, square: Square): List<Square> {
        val attackingIndexes: MutableList<Square> = ArrayList()

        if (!isOnLeftEdge(square)) {
            attackingIndexes.add((square.move(leftDiagonalMove)))
        }
        if (!isOnRightEdge(square)) {
            attackingIndexes.add((square.move(rightDiagonalMove)))
        }

        return attackingIndexes
    }

    override fun moves(position: Position, square: Square): List<Square> {
        var moves: MutableList<Square> = mutableListOf()

        val moveForward = square.move(forwardMove)
        if(position.isFree(moveForward)) {
            moves.add(moveForward)
        }

        val moveForward2Squares = square.move(forwardMove).move(forwardMove)
        if(isOnStartingCell(square) and position.isFree(moveForward2Squares)) {
            moves.add(moveForward2Squares)
        }
        return moves

    }

    fun isOnStartingCell(square: Square): Boolean {
        return square.getRank() == startingRank
    }
}
