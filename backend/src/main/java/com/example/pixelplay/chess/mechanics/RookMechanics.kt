package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position

class RookMechanics() : StraightMechanics(), PieceMechanics {
    override val movingDirection: List<Cell>
        get() = Cell.axialMoves
}
