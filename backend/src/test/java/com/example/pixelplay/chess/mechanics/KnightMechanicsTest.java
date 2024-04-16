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
    void attackingIndexesEdge() {
        PieceMechanics mechanics = new KnightMechanics();
        List< Square> squares = mechanics.attacks(PositionGenerator.initialPosition(),   new Square(0,1));
        squares.sort(null);
        List<Integer> indexes = squares.stream().map(Square::getIndex).toList();
        assertEquals(indexes, List.of(  11,   16,   18));
    }

    @Test
    void attackingIndexesCenter() {
        PieceMechanics mechanics = new KnightMechanics();
        List< Square> squares = mechanics.attacks(PositionGenerator.initialPosition(),   new Square(3, 3));
        squares.sort(null);
        List<Integer> indexes = squares.stream().map(Square::getIndex).toList();
        assertEquals(squares, List.of(  10,   12,   17,   21,   33,   37,   42,   44));
    }
}