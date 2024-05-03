package com.example.pixelplay.chess.mechanics.pieces

import com.example.pixelplay.chess.position.Position
import com.example.pixelplay.chess.base.Square

class WhitePawnMechanics(
    position: Position,
    square: Square,
) : PawnMechanics(
    position,
    square
) {
    override val forwardMove: Square
        get() = Square(1, 0)
    override val leftDiagonalMove: Square
        get() = Square(1, -1)
    override val rightDiagonalMove: Square
        get() = Square(1, 1)
    override val startingRank: Int
        get() = 1
}
