package com.example.pixelplay.chess.mechanics.pieces

import com.example.pixelplay.chess.mechanics.Square
import com.example.pixelplay.chess.mechanics.StraightMechanics

class BishopMechanics() : StraightMechanics() {
    override val movingDirection: List<Square>
        get() = Square.diagonalMoves
}
