package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.PositionGenerator
import com.example.pixelplay.chess.Position
import com.example.pixelplay.chess.base.Square
import com.example.pixelplay.chess.mechanics.pieces.KingMechanics
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class KingMechanicsTest {
    private val capture = arrayOf(
        "..rk.r..",
        "..p.H...",
        "..p.p...",
        "........",
        "........",
        "........",
        "........",
        "........"
    )

    private val initialPosition: Position = PositionGenerator.initialPosition()
    private val capturePosition: Position = PositionGenerator.customPosition(capture);


    @BeforeEach
    fun setUp() {
    }

    @Test
    fun attackingIndexes() {
        val mechanics = KingMechanics(initialPosition, Square(2, 0))
        val squares: MutableList<Square> = mechanics.attacks().map {
            it
        }.toMutableList()
        squares.sortWith(compareBy({it.getRank()}, {it.getFile()}))

        val expected: List<Square> = mutableListOf(Square(1, 0), Square(1, 1), Square(2, 1), Square(3, 0), Square(3, 1))
        assertEquals(expected, squares)
    }

    @Test
    fun moveWithCaptures() {
        val mechanics = KingMechanics(capturePosition, Square(0,3 ))
        val moves: MutableList<Square> = mechanics.moves().toMutableList()
        moves.sort()

        val expected: MutableList<Square> = ArrayList(
            listOf(
                Square(0, 4),
                Square(1, 3),
                Square(1, 4),
            )
        )
        expected.sort()
        assertEquals(expected, moves)
    }



//    @Test
//    fun shortCastle() {
//        val moves: MutableList<Square> = mechanics.moves(PositionGenerator.customPosition(shortCastlePosition, false), Square(7, 3)).toMutableList()
//
//        val expected: MutableList<Square> = ArrayList(
//            listOf(
//                Square(7, 1),
//                Square(7, 2)
//            )
//        )
//        moves.sort()
//        expected.sort()
//        assertEquals(expected, moves)
//    }
//    private val shortCastlePosition = arrayOf(
//        "........",
//        "........",
//        "........",
//        "........",
//        "........",
//        "........",
//        "pppppppp",
//        "r..kq..r",
//    )
//
//    @Test
//    fun longCastle() {
//        val moves: MutableList<Square> = mechanics.moves(PositionGenerator.customPosition(longCastlePosition, false), Square(7, 3)).toMutableList()
//
//        val expected: MutableList<Square> = ArrayList(
//            listOf(
//                Square(7, 5),
//                Square(7, 4),
//            )
//        )
//        moves.sort()
//        expected.sort()
//        assertEquals(expected, moves)
//    }
//    private val longCastlePosition = arrayOf(
//        "........",
//        "........",
//        "........",
//        "........",
//        "........",
//        "........",
//        "pppppppp",
//        "r.bk...r",
//    )
//
//    @Test
//    fun castleCheck() {
//        val moves: MutableList<Square> = mechanics.moves(PositionGenerator.customPosition(castleCheckPosition, false), Square(7, 3)).toMutableList()
//
//        val expected: MutableList<Square> = ArrayList(
//            listOf(
//                Square(7, 4),
//            )
//        )
//        moves.sort()
//        expected.sort()
//        assertEquals(expected, moves)
//    }
//    private val castleCheckPosition = arrayOf(
//        "........",
//        "........",
//        "........",
//        "........",
//        "........",
//        "....B.H.",
//        "ppp..ppp",
//        "r..k...r",
//    )
}