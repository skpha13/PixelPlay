package com.example.pixelplay.chess.mechanics.pieces

import com.example.pixelplay.chess.base.Position
import com.example.pixelplay.chess.base.Square
import com.example.pixelplay.chess.mechanics.ShortRangeMechanics

class KingMechanics : ShortRangeMechanics() {
    override val movingDirection: List<Square>
        get() = Square.completeMoves

    private val shortCastleDirection = Square(0, 2)

    override fun moves(position: Position, square: Square): List<Square> {
        val moves = super.moves(position, square).toMutableList()
        moves.tryShortCastle(position, square)
        return moves
    }

    private fun MutableList<Square>.tryShortCastle(position: Position, square: Square) {
        val color = position.getPiece(square).type.color()
        if(position.canShortCastle(color)) {
            this.add(square.move(shortCastleDirection))
        }
    }
}
