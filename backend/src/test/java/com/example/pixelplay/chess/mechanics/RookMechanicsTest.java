package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.PositionGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RookMechanicsTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void attackingIndexes() {
        PieceMechanics mechanics = new RookMechanics();
        List<Square> squares = new ArrayList<>(mechanics.attacks(PositionGenerator.initialPosition(),   new Square(3, 1)));
        squares.sort(null);
        List<Integer> indexes = squares.stream().map(Square::getIndex).toList();

        List<Integer> expected = new ArrayList<>(List.of(9, 17, 24, 26, 27, 28, 29, 30, 31, 33, 41, 49));
        assertEquals(expected, indexes);
    }
}