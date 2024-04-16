package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.PositionGenerator;
import com.example.pixelplay.chess.mechanics.pieces.RookMechanics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RookMechanicsTest {
    private final PieceMechanics mechanics = new RookMechanics();

    @BeforeEach
    void setUp() {
    }

    @Test
    void attackingIndexes() {
        List<Square> squares = new ArrayList<>(mechanics.attacks(PositionGenerator.initialPosition(),   new Square(3, 1)));
        squares.sort(null);
        List<Integer> indexes = squares.stream().map(Square::getIndex).toList();

        List<Integer> expected = new ArrayList<>(List.of(9, 17, 24, 26, 27, 28, 29, 30, 31, 33, 41, 49));
        assertEquals(expected, indexes);
    }

    @Test
    void movesWithCapture() {
        List<Square> moves = mechanics.moves(PositionGenerator.customPosition(capture), new Square(3, 4));
        List<Square> expected = new ArrayList<>(List.of(
                new Square(2, 4),
                new Square(3, 2),
                new Square(3, 3),
                new Square(3, 5),
                new Square(3, 6),
                new Square(4, 4),
                new Square(5, 4),
                new Square(6, 4),
                new Square(7, 4)
        ));

        moves.sort(null);
        expected.sort(null);

        assertEquals(expected, moves);
    }

    private final String[] capture = new String[] {
            ".......K",
            "........",
            "...RP...",
            "..K.r.Q.",
            "...p.p..",
            "........",
            "........",
            "........"
    };
}