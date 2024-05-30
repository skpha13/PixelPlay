package org.example.backend.chess.logic.mechanics

import org.example.backend.chess.logic.base.Square
import org.example.backend.chess.logic.mechanics.pieces.WhitePawnMechanics
import org.example.backend.chess.logic.position.Position
import org.example.backend.chess.logic.position.PositionGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class WhitePawnMechanicsTest {
    private val initialPosition: Position = PositionGenerator.initialPosition()
    private val capturePosition: Position = PositionGenerator.customPosition(BlackPawnMechanicsTest.capture)


    @Test
    fun attackingIndexesLeftEdge() {
        val mechanics = WhitePawnMechanics(initialPosition, Square(1,0 ))
        val squares = mechanics.attacks()
        val reference: List<Square> = listOf(Square(2, 1))

        Assertions.assertEquals(reference, squares)
    }

    @Test
    fun attackingIndexesRightEdge() {

        val mechanics = WhitePawnMechanics(initialPosition, Square(1,7 ))
        val squares = mechanics.attacks()
        val reference: List<Square> = listOf(Square(2, 6))

        Assertions.assertEquals(reference, squares)
    }

    @Test
    fun attackingIndexesCenter() {
        val mechanics = WhitePawnMechanics(initialPosition, Square(1, 2))
        val squares = mechanics.attacks()
        val reference: List<Square> = listOf(
                Square(2, 1),
                Square(2, 3)
        )

        Assertions.assertEquals(reference, squares)
    }

    @Test
    fun moveFromStartingCell() {

        val mechanics = WhitePawnMechanics(initialPosition, Square(1,2 ))
        val cells = mechanics.moves()
        val reference: List<Square> = listOf(Square(2, 2), Square(3, 2))

        Assertions.assertEquals(reference, cells)
    }

    @Test
    fun moveFromNonStartingCell() {

        val mechanics = WhitePawnMechanics(initialPosition, Square(2,2 ))
        val cells = mechanics.moves()
        val reference: List<Square> = listOf(Square(3, 2))

        Assertions.assertEquals(reference, cells)
    }

    @Test
    fun capture() {
        val mechanics = WhitePawnMechanics(capturePosition, Square(1,3 ))
        val moves = mechanics.moves().toMutableList()
        moves.sort()
        val reference: List<Square> = listOf(
                Square(2, 2),
                Square(2, 3),
                Square(3, 3)
        )

        Assertions.assertEquals(reference, moves)
    }
}