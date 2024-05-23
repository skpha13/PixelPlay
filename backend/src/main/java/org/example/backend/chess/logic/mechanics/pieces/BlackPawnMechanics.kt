package org.example.backend.chess.logic.mechanics.pieces

import org.example.backend.chess.logic.base.Square
import org.example.backend.chess.logic.position.Position

class BlackPawnMechanics (
    position: Position,
    square: Square
) : PawnMechanics(
    position = position,
    square = square
) {
    override val forwardMove: Square
        get() = Square(-1, 0)
    override val leftDiagonalMove: Square
        get() = Square(-1, -1)
    override val rightDiagonalMove: Square
        get() = Square(-1, 1)
    override val startingRank: Int
        get() = 6

}
