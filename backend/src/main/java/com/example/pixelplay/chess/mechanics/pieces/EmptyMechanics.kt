package com.example.pixelplay.chess.mechanics.pieces

import com.example.pixelplay.chess.base.Square
import com.example.pixelplay.chess.mechanics.PieceMechanics
import com.example.pixelplay.chess.position.Position

class EmptyMechanics(position: Position, square: Square) : PieceMechanics(position, square) {
    override fun attacks(): List<Square> {
        return mutableListOf()
    }
}