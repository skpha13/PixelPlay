package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.PositionGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QueenMechanicsTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void attackingSquares() {
        PieceMechanics mechanics = new QueenMechanics(PositionGenerator.initialPosition(), (byte) 25);
        List<Integer> squares = new ArrayList<>(mechanics.attackingSquares().stream().map(Byte::intValue).toList());
        squares.sort(null);

        List<Integer> expected = new ArrayList<>(List.of(9,11,16,17,18,24,26,27,28,29,30,31,32,33,34,41,43,49,52));
        assertEquals(expected, squares);
    }
}