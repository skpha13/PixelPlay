package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position

class BlackPawnMechanics internal constructor(position: Position, square: Byte) : PawnMechanics(position, square) {
    override fun attackingSquares(): List<Byte> {
        val squares: MutableList<Byte> = ArrayList()

        if (!isOnLeftEdge) {
            squares.add((square - 9).toByte())
        }
        if (!isOnRightEdge) {
            squares.add((square - 7).toByte())
        }

        return squares
    }
}
