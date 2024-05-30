package org.example.backend.chess.logic.mechanics

import org.example.backend.chess.logic.base.Square
import org.example.backend.chess.logic.mechanics.pieces.BlackPawnMechanics
import org.example.backend.chess.logic.position.Position
import org.example.backend.chess.logic.position.PositionGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class BlackPawnMechanicsTest {
    private val initialPosition: Position = PositionGenerator.initialPosition()
    private val capturePosition: Position = PositionGenerator.customPosition(capture)

    @Test
    fun attackingIndexesLeftEdge() {
        val mechanics = BlackPawnMechanics(initialPosition, Square(6, 0))
        val squares: List<Square> = mechanics.attacks()
        val reference: List<Square> = listOf(Square(5, 1))

        Assertions.assertEquals(reference, squares)
    }

    @Test
    fun attackingIndexesRightEdge() {
        val mechanics = BlackPawnMechanics(initialPosition, Square(6, 7))
        val squares: List<Square> = mechanics.attacks()
        val reference: List<Square> = listOf(Square(5, 6))

        Assertions.assertEquals(reference, squares)
    }

    @Test
    fun attackingIndexesCenter() {
        val mechanics = BlackPawnMechanics(initialPosition, Square(6, 2))
        val squares: List<Square> = mechanics.attacks()
        val reference: List<Square> = listOf(
                Square(5, 1),
                Square(5, 3)
        )

        Assertions.assertEquals(reference, squares)
    }

    @Test
    fun moveFromStartingCell() {
        val mechanics = BlackPawnMechanics(initialPosition, Square(6, 2))
        val cells: MutableList<Square> = mechanics.moves().toMutableList()
        val reference: MutableList<Square> = mutableListOf(Square(5, 2), Square(4, 2))

        cells.sort()
        reference.sort()
        Assertions.assertEquals(reference, cells)
    }

    @Test
    fun moveFromNonStartingCell() {
        val mechanics = BlackPawnMechanics(initialPosition, Square(5, 2))
        val cells: List<Square> = mechanics.moves()
        val reference: List<Square> = listOf(Square(4, 2))

        Assertions.assertEquals(reference, cells)
    }

    @Test
    fun capture() {
        val mechanics = BlackPawnMechanics(capturePosition, Square(2, 2))
        val moves: List<Square> = mechanics.moves()
        val reference: List<Square> = listOf(
                Square(1, 2),
                Square(1, 3)
        )

        Assertions.assertEquals(reference, moves)
    }


    companion object {
        val capture = arrayOf(
            "........",
            "...R....",
            "..p.....",
            "........",
            "........",
            "........",
            "........",
            "........"
        )
    }
}