package com.example.pixelplay.chess.mechanics.pieces

import com.example.pixelplay.chess.base.Square
import com.example.pixelplay.chess.mechanics.StraightMechanics

class QueenMechanics() : StraightMechanics() {
    override val movingDirection: List<Square>
        get() = Square.completeMoves
}
