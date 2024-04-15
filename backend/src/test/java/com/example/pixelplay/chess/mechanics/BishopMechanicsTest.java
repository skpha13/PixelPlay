package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.PositionGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BishopMechanicsTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void attackingIndexes() {
        PieceMechanics mechanics = new BishopMechanics();
        List<Integer> squares = new ArrayList<>(mechanics.attackingCells(PositionGenerator.initialPosition(), (byte) 25).stream().map(Byte::intValue).toList());
        squares.sort(null);

        List<Integer> expected = new ArrayList<>(List.of(11, 16, 18, 32, 34, 43, 52));
        assertEquals(expected, squares);
    }
}