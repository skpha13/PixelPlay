package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.Position;
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
        List<Byte> squares = pieceMechanics.attackingCells(PositionGenerator.initialPosition(), (byte) 8);
        List<Byte> reference = new ArrayList<>(List.of((byte) 17));

        assertEquals(reference, squares);
    }

    @Test
    void attackingIndexesRightEdge() {
        PieceMechanics pieceMechanics = new WhitePawnMechanics();
        List<Byte> squares = pieceMechanics.attackingCells(PositionGenerator.initialPosition(), (byte) 15);
        List<Byte> reference = new ArrayList<>(List.of((byte) 22));

        assertEquals(reference, squares);
    }

    @Test
    void attackingIndexesCenter() {
        PieceMechanics pieceMechanics = new WhitePawnMechanics();
        List<Byte> squares = pieceMechanics.attackingCells(PositionGenerator.initialPosition(), (byte) 10);
        List<Byte> reference = new ArrayList<>(List.of((byte) 17, (byte) 19));

        assertEquals(reference, squares);
    }

    @Test
    void moveFromStartingCell() {
        PieceMechanics mechanics = new WhitePawnMechanics();
        List<Byte> cells = mechanics.moves(PositionGenerator.initialPosition(), (byte) 10);
        List<Byte> reference = new ArrayList<>(List.of((byte) 18, (byte)26));

        assertEquals(reference, cells);
    }

    @Test
    void moveFromNonStartingCell() {
        PieceMechanics mechanics = new WhitePawnMechanics();
        List<Byte> cells = mechanics.moves(PositionGenerator.initialPosition(), (byte) 18);
        List<Byte> reference = new ArrayList<>(List.of((byte)26));

        assertEquals(reference, cells);
    }
}