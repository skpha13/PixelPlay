package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position

class BishopMechanics() : StraightMechanics(), PieceMechanics {
    override val movingDirection: List<Cell>
        get() = Cell.diagonalMoves
}
