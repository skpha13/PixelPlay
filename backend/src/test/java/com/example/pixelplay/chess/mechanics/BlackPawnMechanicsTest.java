package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.PositionGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlackPawnMechanicsTest {

    @Test
    void attackingIndexesLeftEdge() {
        PieceMechanics pieceMechanics = new BlackPawnMechanics();
        List<Square> squares = pieceMechanics.attacks(PositionGenerator.initialPosition(), new Square(6, 0));
        List<Square> reference = new ArrayList<>(List.of(new Square(5, 1)));

        assertEquals(reference, squares);
    }

    @Test
    void attackingIndexesRightEdge() {
        PieceMechanics pieceMechanics = new BlackPawnMechanics();
        List<Square> squares = pieceMechanics.attacks(PositionGenerator.initialPosition(), new Square(6, 7));
        List<Square> reference = new ArrayList<>(List.of(new Square(5, 6)));

        assertEquals(reference, squares);
    }

    @Test
    void attackingIndexesCenter() {
        PieceMechanics pieceMechanics = new BlackPawnMechanics();
        List<Square> squares = pieceMechanics.attacks(PositionGenerator.initialPosition(), new Square(6, 2));
        List<Square> reference = new ArrayList<>(List.of(
                new Square(5, 1),
                new Square(5, 3)
        ));

        assertEquals(reference, squares);
    }
}