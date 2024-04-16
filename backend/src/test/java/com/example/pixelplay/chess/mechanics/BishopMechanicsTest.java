package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.PositionGenerator;
import com.example.pixelplay.chess.mechanics.pieces.BishopMechanics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BishopMechanicsTest {

    private final PieceMechanics mechanics = new BishopMechanics();

    @BeforeEach
    void setUp() {
    }

    @Test
    void attackingIndexes() {
        List<Square> squares = new ArrayList<>(mechanics.attacks(PositionGenerator.initialPosition(),   new Square(3, 1)));
        squares.sort(null);

        List<Square> expected = new ArrayList<>(List.of(
                new Square(1, 3),
                new Square(2, 0),
                new Square(2, 2),
                new Square(4, 0),
                new Square(4, 2),
                new Square(5, 3),
                new Square(6, 4)
        ));
        assertEquals(expected, squares);
    }

    @Test
    void movesWithCapture() {
        List<Square> moves = mechanics.moves(PositionGenerator.customPosition(capture), new Square(3, 4));
        List<Square> expected = new ArrayList<>(List.of(
                new Square(2, 3),
                new Square(0, 7),
                new Square(1, 6),
                new Square(2, 5)
        ));

        moves.sort(null);
        expected.sort(null);

        assertEquals(expected, moves);
    }

    private final String[] capture = new String[] {
            ".......K",
            "........",
            "...R....",
            "....b...",
            "...p.p..",
            "........",
            "........",
            "........"
    };
}