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
        List<Byte> squares = pieceMechanics.attackingCells(PositionGenerator.initialPosition(), (byte) 48);
        List<Byte> reference = new ArrayList<>(List.of((byte) 41));

        assertEquals(reference, squares);
    }

    @Test
    void attackingIndexesRightEdge() {
        PieceMechanics pieceMechanics = new BlackPawnMechanics();
        List<Byte> squares = pieceMechanics.attackingCells(PositionGenerator.initialPosition(), (byte) 55);
        List<Byte> reference = new ArrayList<>(List.of((byte) 46));

        assertEquals(reference, squares);
    }

    @Test
    void attackingIndexesCenter() {
        PieceMechanics pieceMechanics = new BlackPawnMechanics();
        List<Byte> squares = pieceMechanics.attackingCells(PositionGenerator.initialPosition(), (byte) 50);
        List<Byte> reference = new ArrayList<>(List.of((byte) 41, (byte) 43));

        assertEquals(reference, squares);
    }
}