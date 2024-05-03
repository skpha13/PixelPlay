package com.example.pixelplay.chess.mechanics.pieces

import com.example.pixelplay.chess.position.Position
import com.example.pixelplay.chess.mechanics.PieceMechanics
import com.example.pixelplay.chess.base.Square

abstract class PawnMechanics(
    position: Position,
    square: Square
) : PieceMechanics(
    position,
    square
) {
    abstract val forwardMove: Square
    abstract val leftDiagonalMove: Square
    abstract val rightDiagonalMove: Square
    abstract val startingRank: Int

    private fun isOnLeftEdge(): Boolean = square.getFile() == 0

    private fun isOnRightEdge(): Boolean = square.getFile() == 7

    override fun attacks(): List<Square> {
        val attackingIndexes: MutableList<Square> = ArrayList()

        if (!isOnLeftEdge()) {
            attackingIndexes.add((square.move(leftDiagonalMove)))
        }
        if (!isOnRightEdge()) {
            attackingIndexes.add((square.move(rightDiagonalMove)))
        }

        return attackingIndexes
    }

    override fun moves(): List<Square> {
        val moves: MutableList<Square> = mutableListOf()

        moves.tryMovingForward()
        moves.tryMovingForward2Squares()
        moves.tryCapture()

        return moves

    }

    private fun isOnStartingCell(): Boolean {
        return square.getRank() == startingRank
    }

    private fun MutableList<Square>.tryMovingForward() {
        val move = square.move(forwardMove)
        if(position.isFree(move)) {
            this@tryMovingForward.add(move)
        }
    }
    private fun MutableList<Square>.tryMovingForward2Squares() {
        if(isOnStartingCell()) {
            val move = square.move(forwardMove).move(forwardMove)
            if(position.isFree(move)) {
                this@tryMovingForward2Squares.add(move)
            }
        }

    }
    private fun MutableList<Square>.tryCapture() {
        var capture = square.move(leftDiagonalMove)
        if(canCapture(capture)) {
            this@tryCapture.add(capture)
        }

        capture = square.move(rightDiagonalMove)
        if(canCapture(capture)) {
            this@tryCapture.add(capture)
        }
    }
}
