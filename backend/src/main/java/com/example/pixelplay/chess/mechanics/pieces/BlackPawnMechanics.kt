package com.example.pixelplay.chess.mechanics.pieces

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.base.Square

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
