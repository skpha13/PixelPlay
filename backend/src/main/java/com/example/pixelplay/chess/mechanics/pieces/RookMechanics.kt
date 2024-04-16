package com.example.pixelplay.chess.mechanics.pieces

import com.example.pixelplay.chess.mechanics.Square
import com.example.pixelplay.chess.mechanics.StraightMechanics

class RookMechanics() : StraightMechanics() {
    override val movingDirection: List<Square>
        get() = Square.axialMoves
}
