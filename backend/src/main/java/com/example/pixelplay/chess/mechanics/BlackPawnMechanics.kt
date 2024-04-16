package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position

class BlackPawnMechanics internal constructor() : PawnMechanics() {
    override val forwardIndex: Byte
        get() = -8
    override val leftDiagonalIndex: Byte
        get() = -9
    override val rightDiagonalIndex: Byte
        get() = -7
    override val startingRank: Byte
        get() = 6

}
