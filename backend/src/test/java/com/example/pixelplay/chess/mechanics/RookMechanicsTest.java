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
        List<Integer> squares = new ArrayList<>(mechanics.attackingCells(PositionGenerator.initialPosition(), (byte) 25).stream().map(Byte::intValue).toList());
        squares.sort(null);

        List<Integer> expected = new ArrayList<>(List.of(9, 17, 24, 26, 27, 28, 29, 30, 31, 33, 41, 49));
        assertEquals(expected, squares);
    }
}