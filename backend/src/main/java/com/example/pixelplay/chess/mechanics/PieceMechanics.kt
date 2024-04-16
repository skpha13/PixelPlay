package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position

internal interface PieceMechanics {
    fun attacks(position: Position, square: Square): List<Square>

    fun moves(position: Position, square: Square): List<Square>
}
