package org.example.backend.chess.logic.mechanics.pieces

import org.example.backend.chess.logic.base.Square
import org.example.backend.chess.logic.mechanics.PieceMechanics
import org.example.backend.chess.logic.position.Position

class EmptyMechanics(position: Position, square: Square) : PieceMechanics(position, square) {
    override fun attacks(): List<Square> {
        return mutableListOf()
    }
}