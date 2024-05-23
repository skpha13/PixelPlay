package org.example.backend.chess.logic.mechanics.pieces

import org.example.backend.chess.logic.base.Square
import org.example.backend.chess.logic.mechanics.LongRangeMechanics
import org.example.backend.chess.logic.position.Position

class BishopMechanics(
    position: Position,
    square: Square
) : LongRangeMechanics(
    position,
    square
) {
    override val movingDirection: List<Square>
        get() = Square.diagonalMoves
}
