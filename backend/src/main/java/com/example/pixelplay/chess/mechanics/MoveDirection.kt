package com.example.pixelplay.chess.mechanics

class MoveDirection(@JvmField var rank: Int, @JvmField var file: Int) {
    companion object {
        @JvmField
        val diagonalMoves: List<MoveDirection> = listOf(
                MoveDirection(-1, -1),
                MoveDirection(-1, 1),
                MoveDirection(1, -1),
                MoveDirection(1, 1)
            )

        @JvmField
        val axialMoves: List<MoveDirection> = listOf(
            MoveDirection(-1, 0),
            MoveDirection(1, 0),
            MoveDirection(0, 1),
            MoveDirection(0, -1)
        )

        @JvmField
        val completeMoves: List<MoveDirection> = axialMoves + diagonalMoves
    }
}
