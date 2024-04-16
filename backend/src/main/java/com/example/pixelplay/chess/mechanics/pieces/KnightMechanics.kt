package com.example.pixelplay.chess.mechanics.pieces

import com.example.pixelplay.chess.base.Position
import com.example.pixelplay.chess.utils.PositionUtil
import com.example.pixelplay.chess.mechanics.PieceMechanics
import com.example.pixelplay.chess.base.Square
import com.example.pixelplay.chess.mechanics.ShortRangeMechanics

class KnightMechanics : ShortRangeMechanics() {
    override val movingDirection: List<Square>
        get() = Square.knightMoves
}
