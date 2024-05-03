package com.example.pixelplay.chess.mechanics.pieces

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.base.Square
import com.example.pixelplay.chess.mechanics.ShortRangeMechanics

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
