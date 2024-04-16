package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.PositionGenerator
import com.example.pixelplay.chess.base.Square
import com.example.pixelplay.chess.mechanics.pieces.KingMechanics
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class KingMechanicsTest {
    private val mechanics = KingMechanics()

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun attackingIndexes() {
        val squares: MutableList<Square> = mechanics.attacks(PositionGenerator.initialPosition(), Square(2, 0)).map {
            it
        }.toMutableList()
        squares.sortWith(compareBy({it.getRank()}, {it.getFile()}))

        val expected: List<Square> = mutableListOf(Square(1, 0), Square(1, 1), Square(2, 1), Square(3, 0), Square(3, 1))
        assertEquals(expected, squares)
    }

    @Test
    fun moveWithCaptures() {
        val moves: MutableList<Square> = mechanics.moves(PositionGenerator.customPosition(capture), Square(0, 3)).toMutableList()
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

    private val capture = arrayOf(
        "..rk.p..",
        "..p.Q...",
        "..p.p...",
        "........",
        "........",
        "........",
        "........",
        "........"
    )

    @Test
    fun shortCastle() {
        val moves: MutableList<Square> = mechanics.moves(PositionGenerator.customPosition(capture), Square(7, 3)).toMutableList()

        val expected: MutableList<Square> = ArrayList(
            listOf(
                Square(7, 1),
            )
        )
        assertEquals(expected, moves)
    }
    private val shortCastlePosition = arrayOf(
        "........",
        "........",
        "........",
        "........",
        "........",
        "........",
        "pppppppp",
        "r..kq..r",
    )
}