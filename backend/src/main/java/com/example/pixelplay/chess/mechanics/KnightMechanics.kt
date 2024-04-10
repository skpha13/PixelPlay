package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.PositionUtil

class KnightMechanics(private val position: Position, private val square: Byte) : PieceMechanics {
    override fun attackingSquares(): List<Byte> {
        val squares: MutableList<Byte> = ArrayList()
        val rank = PositionUtil.getRank(square)
        val file = PositionUtil.getFile(square)
        for (moveDirection in MOVE_DIRECTIONS) {
            val newRank = moveDirection.rank + rank
            val newFile = moveDirection.file + file
            if (PositionUtil.isOnBoard(newRank, newFile)) {
                squares.add(PositionUtil.getSquare(newRank, newFile))
            }
        }
        return squares
    }

    companion object {
        private val MOVE_DIRECTIONS: List<MoveDirection> = ArrayList(
            java.util.List.of(
                MoveDirection(2, 1),
                MoveDirection(1, 2),
                MoveDirection(-1, 2),
                MoveDirection(-2, 1),
                MoveDirection(-2, -1),
                MoveDirection(-1, -2),
                MoveDirection(1, -2),
                MoveDirection(2, -1)

            )
        )
    }
}
