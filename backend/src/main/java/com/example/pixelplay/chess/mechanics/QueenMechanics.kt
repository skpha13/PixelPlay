package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position

class QueenMechanics() : StraightMechanics() {
    override val movingDirection: List<Square>
        get() = Square.completeMoves
}
