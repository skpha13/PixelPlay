package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.PositionUtil

class WhitePawnMechanics() : PawnMechanics() {
    override val forwardMove: Square
        get() = Square(1, 0)
    override val leftDiagonalMove: Square
        get() = Square(1, -1)
    override val rightDiagonalMove: Square
        get() = Square(1, 1)
    override val startingRank: Int
        get() = 1
}
