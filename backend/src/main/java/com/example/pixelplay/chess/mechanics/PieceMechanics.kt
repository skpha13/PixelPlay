package com.example.pixelplay.chess.mechanics

internal interface PieceMechanics {
    fun attackingSquares(): List<Byte?>?
}
