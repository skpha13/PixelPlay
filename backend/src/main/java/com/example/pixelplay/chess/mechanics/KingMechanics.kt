package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.PositionUtil

class KingMechanics() : PieceMechanics {

    override fun attacks(position: Position, square: Square): List<Square> {
        val attacks: MutableList<Square> = mutableListOf()
        for (moveDirection in Square.completeMoves){
            val nextSquare = square.move(moveDirection)
            if(PositionUtil.isOnBoard(nextSquare)) {
                attacks += nextSquare
            }
        }
        return attacks
    }

    override fun moves(position: Position, square: Square): List<Square> {
        TODO("Not yet implemented")
    }
}
