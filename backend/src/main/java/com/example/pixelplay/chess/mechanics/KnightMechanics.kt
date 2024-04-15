package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.PositionUtil

class KnightMechanics(private val position: Position, private val square: Byte) : PieceMechanics {
    override fun attackingSquares(): List<Byte> {
        val squares: MutableList<Byte> = ArrayList()
        val cell = PositionUtil.getCell(square);
        for (moveDirection in Cell.knightMoves) {
            val nextCell = cell.move(moveDirection)
            if (PositionUtil.isOnBoard(nextCell)) {
                squares.add(PositionUtil.getSquare(nextCell))
            }
        }
        return squares
    }
}
