package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position

class BishopMechanics(position: Position, square: Byte) : StraightMechanics(position, square), PieceMechanics {
    override val movingDirection: List<Cell>
        get() = Cell.diagonalMoves
}
