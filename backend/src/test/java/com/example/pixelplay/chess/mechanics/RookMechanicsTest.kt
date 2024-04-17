package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.PositionGenerator
import com.example.pixelplay.chess.base.Position
import com.example.pixelplay.chess.base.Square
import com.example.pixelplay.chess.mechanics.pieces.RookMechanics
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class RookMechanicsTest {
    private val capture = arrayOf(
        ".......K",
        "........",
        "...RP...",
        "..K.r.Q.",
        "...p.p..",
        "........",
        "........",
        "........"
    )

    private val initialPosition: Position = PositionGenerator.initialPosition()
    private val capturePosition: Position = PositionGenerator.customPosition(capture)

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun attackingIndexes() {
        val mechanics = RookMechanics(initialPosition, Square(3, 1))
        val squares: MutableList<Square> = mechanics.attacks().toMutableList()
        squares.sort()
        val indexes = squares.map { obj: Square -> obj.getIndex() }.toList()

        val expected: List<Int> = ArrayList(listOf(9, 17, 24, 26, 27, 28, 29, 30, 31, 33, 41, 49))
        Assertions.assertEquals(expected, indexes)
    }

    @Test
    fun movesWithCapture() {
        val mechanics = RookMechanics(capturePosition, Square(3, 4))
        val moves: MutableList<Square> = mechanics.moves().toMutableList()
        val expected: MutableList<Square> = mutableListOf(
                Square(2, 4),
                Square(3, 2),
                Square(3, 3),
                Square(3, 5),
                Square(3, 6),
                Square(4, 4),
                Square(5, 4),
                Square(6, 4),
                Square(7, 4)
        )

        moves.sort()
        expected.sort()

        Assertions.assertEquals(expected, moves)
    }
}