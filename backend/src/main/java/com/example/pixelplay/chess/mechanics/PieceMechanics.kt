package com.example.pixelplay.chess.mechanics

internal interface PieceMechanics {
    fun attackingCells(): List<Byte?>?
}
