package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position

class QueenMechanics(position: Position, square: Byte) : StraightMechanics(position, square), PieceMechanics {
    override val movingDirection: List<MoveDirection>
        get() = MoveDirection.completeMoves
}
