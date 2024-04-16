package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.PositionUtil

class WhitePawnMechanics() : PawnMechanics() {
    override val forwardIndex: Byte
        get() = 8
    override val leftDiagonalIndex: Byte
        get() = 7
    override val rightDiagonalIndex: Byte
        get() = 9
    override val startingRank: Byte
        get() = 1
}
