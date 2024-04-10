package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.PositionGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KnightMechanicsTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void attackingSquaresEdge() {
        PieceMechanics mechanics = new KnightMechanics(PositionGenerator.initialPosition(), (byte) 1);
        List<Byte> squares = mechanics.attackingSquares();
        squares.sort(null);
        assertEquals(squares, List.of((byte) 11, (byte) 16, (byte) 18));
    }

    @Test
    void attackingSquaresCenter() {
        PieceMechanics mechanics = new KnightMechanics(PositionGenerator.initialPosition(), (byte) 27);
        List<Byte> squares = mechanics.attackingSquares();
        squares.sort(null);
        assertEquals(squares, List.of((byte) 10, (byte) 12, (byte) 17, (byte) 21, (byte) 33, (byte) 37, (byte) 42, (byte) 44));
    }
}