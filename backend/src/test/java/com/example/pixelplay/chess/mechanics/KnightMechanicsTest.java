package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.PositionGenerator;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.mechanics.pieces.KnightMechanics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KnightMechanicsTest {
    private final PieceMechanics mechanics = new KnightMechanics();

    @BeforeEach
    void setUp() {
    }

    @Test
    void attackingIndexesEdge() {
        List<Square> squares = mechanics.attacks(PositionGenerator.initialPosition(),   new Square(0,1));
        squares.sort(null);
        List<Integer> indexes = squares.stream().map(Square::getIndex).toList();
        assertEquals(indexes, List.of(  11,   16,   18));
    }

    @Test
    void attackingIndexesCenter() {
        List< Square> squares = mechanics.attacks(PositionGenerator.initialPosition(),   new Square(3, 3));
        squares.sort(null);
        List<Integer> indexes = squares.stream().map(Square::getIndex).toList();
        assertEquals(squares, List.of(  10,   12,   17,   21,   33,   37,   42,   44));
    }

    @Test
    void moveWithCaptures() {
        List<Square> moves = mechanics.moves(PositionGenerator.customPosition(capture),   new Square(0, 3));
        moves.sort(null);

        List<Square> expected = new ArrayList<Square>(List.of(
                new Square(1, 1),
                new Square(1, 5)
        ));
        expected.sort(null);
        assertEquals(expected, moves);
    }

    private final String[] capture = new String[] {
            "...h....",
            ".....Q..",
            "..p.p...",
            "........",
            "........",
            "........",
            "........",
            "........"
    };
}