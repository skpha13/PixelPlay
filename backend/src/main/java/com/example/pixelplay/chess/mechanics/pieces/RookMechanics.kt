package com.example.pixelplay.chess.mechanics.pieces

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.base.Square
import com.example.pixelplay.chess.mechanics.LongRangeMechanics

class RookMechanics(
    position: Position,
    square: Square,
) : LongRangeMechanics(
    position,
    square
) {
    override val movingDirection: List<Square>
        get() = Square.axialMoves
}
