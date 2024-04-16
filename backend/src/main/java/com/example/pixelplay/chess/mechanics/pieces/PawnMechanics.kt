package com.example.pixelplay.chess.mechanics.pieces

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.mechanics.PieceMechanics
import com.example.pixelplay.chess.mechanics.Square

abstract class PawnMechanics() : PieceMechanics() {
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

        moves.tryMovingForward(position, square)
        moves.tryMovingForward2Squares(position, square)
        moves.tryCapture(position, square)

        return moves

    }

    private fun isOnStartingCell(square: Square): Boolean {
        return square.getRank() == startingRank
    }

    private fun MutableList<Square>.tryMovingForward(position: Position, square: Square) {
        val move = square.move(forwardMove)
        if(position.isFree(move)) {
            this@tryMovingForward.add(move)
        }
    }
    private fun MutableList<Square>.tryMovingForward2Squares(position: Position, square: Square) {
        if(isOnStartingCell(square)) {
            val move = square.move(forwardMove).move(forwardMove)
            if(position.isFree(move)) {
                this@tryMovingForward2Squares.add(move)
            }
        }

    }
    private fun MutableList<Square>.tryCapture(position: Position, square: Square) {
        var capture = square.move(leftDiagonalMove)
        val currentColor = position.getPieceType(square).color()
        if(position.canCapture(capture, currentColor)) {
            this@tryCapture.add(capture)
        }

        capture = square.move(rightDiagonalMove)
        if(position.canCapture(capture, currentColor)) {
            this@tryCapture.add(capture)
        }
    }
}
