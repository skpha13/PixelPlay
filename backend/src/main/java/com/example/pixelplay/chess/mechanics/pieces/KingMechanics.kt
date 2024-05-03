package com.example.pixelplay.chess.mechanics.pieces

import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.base.Square
import com.example.pixelplay.chess.mechanics.ShortRangeMechanics

class KingMechanics(
    position: Position,
    square: Square
) : ShortRangeMechanics(
    position,
    square
) {
    override val movingDirection: List<Square>
        get() = Square.completeMoves

    private val shortCastleDirection = Square(0, -2)
    private val longCastleDirection = Square(0, 2)

    override fun moves(): List<Square> {
        val moves = super.moves().toMutableList()
//        moves.tryShortCastle(position, square)
//        moves.tryLongCastle(position, square)
//
//        moves.removeMovesInCheck(position, square)
        return moves
    }

//    private fun MutableList<Square>.tryShortCastle(position: Position, square: Square) {
//        val color = position.getPiece(square).type.color()
//        if(position.canShortCastle(color)) {
//            this.add(square.move(shortCastleDirection))
//        }
//    }
//
//    private fun MutableList<Square>.tryLongCastle(position: Position, square: Square) {
//        val color = position.getPiece(square).type.color()
//        if(position.canLongCastle(color)) {
//            this.add(square.move(longCastleDirection))
//        }
//    }
//
//    private fun MutableList<Square>.removeMovesInCheck(position: Position, square: Square) {
//        val color = getColor(position, square)
//        this.removeAll {
//            position.isAttackedBy(color.reverse(), it)
//        };
//    }
}
