package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.PositionGenerator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class KingMechanicsTest {

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun attackingIndexes() {
        val mechanics: PieceMechanics = KingMechanics()
        val squares: MutableList<Int> = mechanics.attackingCells(PositionGenerator.initialPosition(), 16.toByte()).map {
            it.toInt()
        }.toMutableList()
        squares.sort()

        val expected: List<Int> = ArrayList(listOf(8, 9, 17, 24, 25))
        assertEquals(expected, squares)
    }
}