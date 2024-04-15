package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position

internal interface PieceMechanics {
    fun attackingCells(position: Position, index: Byte): List<Byte>
}
