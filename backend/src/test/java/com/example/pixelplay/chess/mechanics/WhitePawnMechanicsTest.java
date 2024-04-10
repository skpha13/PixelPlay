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
    void attackingSquaresLeftEdge() {
        PieceMechanics pieceMechanics = new WhitePawnMechanics(PositionGenerator.initialPosition(), (byte) 8);
        List<Byte> squares = pieceMechanics.attackingSquares();
        List<Byte> reference = new ArrayList<>(List.of((byte) 17));

        assertEquals(reference, squares);
    }

    @Test
    void attackingSquaresRightEdge() {
        PieceMechanics pieceMechanics = new WhitePawnMechanics(PositionGenerator.initialPosition(), (byte) 15);
        List<Byte> squares = pieceMechanics.attackingSquares();
        List<Byte> reference = new ArrayList<>(List.of((byte) 22));

        assertEquals(reference, squares);
    }

    @Test
    void attackingSquaresCenter() {
        PieceMechanics pieceMechanics = new WhitePawnMechanics(PositionGenerator.initialPosition(), (byte) 10);
        List<Byte> squares = pieceMechanics.attackingSquares();
        List<Byte> reference = new ArrayList<>(List.of((byte) 17, (byte) 19));

        assertEquals(reference, squares);
    }
}