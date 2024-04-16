package com.example.pixelplay.chess.mechanics.pieces

import com.example.pixelplay.chess.base.Square
import com.example.pixelplay.chess.mechanics.LongRangeMechanics

class RookMechanics : LongRangeMechanics() {
    override val movingDirection: List<Square>
        get() = Square.axialMoves
}
