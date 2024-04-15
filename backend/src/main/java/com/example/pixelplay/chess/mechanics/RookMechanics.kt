package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position

class RookMechanics(position: Position, index: Byte) : StraightMechanics(position, index), PieceMechanics {
    override val movingDirection: List<Cell>
        get() = Cell.axialMoves
}
