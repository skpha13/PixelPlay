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
    fun attackingSquares() {
        val mechanics: PieceMechanics = KingMechanics(PositionGenerator.initialPosition(), 16.toByte())
        val squares: MutableList<Int> = mechanics.attackingSquares()!!.map {
            it!!.toInt()
        }.toMutableList()
        squares.sort()

        val expected: List<Int> = ArrayList(listOf(8, 9, 17, 24, 25))
        assertEquals(expected, squares)
    }
}