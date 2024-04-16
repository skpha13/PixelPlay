package com.example.pixelplay.chess.mechanics

import com.example.pixelplay.chess.PositionGenerator
import com.example.pixelplay.chess.mechanics.pieces.KingMechanics
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
        val squares: MutableList<Square> = mechanics.attacks(PositionGenerator.initialPosition(), Square(2, 0)).map {
            it
        }.toMutableList()
        squares.sortWith(compareBy({it.getRank()}, {it.getFile()}))

        val expected: List<Square> = mutableListOf(Square(1, 0), Square(1, 1), Square(2, 1), Square(3, 0), Square(3, 1))
        assertEquals(expected, squares)
    }
}