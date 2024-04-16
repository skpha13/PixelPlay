package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.PositionGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class QueenMechanicsTest {
    @BeforeEach
    fun setUp() {
    }

    @Test
    fun attackingIndexes() {
        val mechanics: PieceMechanics = QueenMechanics()
        val squares: MutableList<Square> = mechanics.attacks(PositionGenerator.initialPosition(), Square(3, 1)).toMutableList()
        squares.sortWith(compareBy { it.getIndex() })

        val indexes = squares.map { obj: Square -> obj.getIndex() }
        val expected: List<Int> =
            ArrayList(listOf(9, 11, 16, 17, 18, 24, 26, 27, 28, 29, 30, 31, 32, 33, 34, 41, 43, 49, 52))
        Assertions.assertEquals(expected, indexes)
    }
}