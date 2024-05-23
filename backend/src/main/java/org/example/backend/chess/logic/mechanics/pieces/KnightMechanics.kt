package org.example.backend.chess.logic.mechanics.pieces

import org.example.backend.chess.logic.base.Square
import org.example.backend.chess.logic.mechanics.ShortRangeMechanics
import org.example.backend.chess.logic.position.Position

class KnightMechanics(
    position: Position,
    square: Square,
) : ShortRangeMechanics(
    position,
    square
) {
    override val movingDirection: List<Square>
        get() = Square.knightMoves
}
