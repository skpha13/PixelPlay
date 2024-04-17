package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.PositionGenerator
import com.example.pixelplay.chess.base.Square
import com.example.pixelplay.chess.mechanics.pieces.QueenMechanics
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class QueenMechanicsTest {

    private val capture = arrayOf(
        "........",
        "........",
        "...HKP..",
        "....q...",
        "...ppp..",
        "........",
        "........",
        "........"
    )

    private val initialPosition = PositionGenerator.initialPosition()
    private val capturePosition = PositionGenerator.customPosition(capture)

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun attackingIndexes() {
        val mechanics = QueenMechanics(initialPosition, Square(3, 1))
        val squares: MutableList<Square> = mechanics.attacks().toMutableList()
        squares.sortWith(compareBy { it.getIndex() })

        val indexes = squares.map { obj: Square -> obj.getIndex() }
        val expected: List<Int> =
            ArrayList(listOf(9, 11, 16, 17, 18, 24, 26, 27, 28, 29, 30, 31, 32, 33, 34, 41, 43, 49, 52))
        Assertions.assertEquals(expected, indexes)
    }

    @Test
    fun movesWithCapture() {
        val mechanics = QueenMechanics(capturePosition, Square(3, 4))
        val moves: MutableList<Square> = mechanics.moves().toMutableList()
        val expected: MutableList<Square> = java.util.ArrayList(
            java.util.List.of(
                Square(2, 3),
                Square(2, 4),
                Square(2, 5),
                Square(3, 0),
                Square(3, 1),
                Square(3, 2),
                Square(3, 3),
                Square(3, 5),
                Square(3, 6),
                Square(3, 7),
            )
        )

        moves.sort()
        expected.sort()

        Assertions.assertEquals(expected, moves)
    }

}