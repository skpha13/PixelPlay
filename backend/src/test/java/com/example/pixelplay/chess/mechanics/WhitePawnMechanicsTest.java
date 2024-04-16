package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.PositionGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WhitePawnMechanicsTest {
    @BeforeEach
    void setUp() {
    }

    @Test
    void attackingIndexesLeftEdge() {
        PieceMechanics pieceMechanics = new WhitePawnMechanics();
        List<Square> squares = pieceMechanics.attacks(PositionGenerator.initialPosition(), new Square(1, 0)                                   );
        List<Square> reference = new ArrayList<>(List.of(  new Square(2, 1)));

        assertEquals(reference, squares);
    }

    @Test
    void attackingIndexesRightEdge() {
        PieceMechanics pieceMechanics = new WhitePawnMechanics();
        List< Square> squares = pieceMechanics.attacks(PositionGenerator.initialPosition(),   new Square(1, 7));
        List< Square> reference = new ArrayList<>(List.of(  new Square(2, 6)));

        assertEquals(reference, squares);
    }

    @Test
    void attackingIndexesCenter() {
        PieceMechanics pieceMechanics = new WhitePawnMechanics();
        List< Square> squares = pieceMechanics.attacks(PositionGenerator.initialPosition(),   new Square(1, 2));
        List< Square> reference = new ArrayList<>(List.of(
                new Square(2, 1),
                new Square(2, 3)
        ));

        assertEquals(reference, squares);
    }

    @Test
    void moveFromStartingCell() {
        PieceMechanics mechanics = new WhitePawnMechanics();
        List< Square> cells = mechanics.moves(PositionGenerator.initialPosition(),   new Square(1, 2));
        List< Square> reference = new ArrayList<>(List.of(new Square(2, 2), new Square(3, 2)));

        assertEquals(reference, cells);
    }

    @Test
    void moveFromNonStartingCell() {
        PieceMechanics mechanics = new WhitePawnMechanics();
        List< Square> cells = mechanics.moves(PositionGenerator.initialPosition(),   new Square(2, 2));
        List< Square> reference = new ArrayList<>(List.of(new Square(3, 2)));

        assertEquals(reference, cells);
    }
}