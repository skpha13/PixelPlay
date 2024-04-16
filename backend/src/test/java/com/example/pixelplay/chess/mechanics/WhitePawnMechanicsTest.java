package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.PositionGenerator;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.mechanics.pieces.WhitePawnMechanics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WhitePawnMechanicsTest {
    private final PieceMechanics mechanics = new WhitePawnMechanics();

    @BeforeEach
    void setUp() {
    }

    @Test
    void attackingIndexesLeftEdge() {
        List<Square> squares = mechanics.attacks(PositionGenerator.initialPosition(), new Square(1, 0));
        List<Square> reference = new ArrayList<>(List.of(new Square(2, 1)));

        assertEquals(reference, squares);
    }

    @Test
    void attackingIndexesRightEdge() {
        List<Square> squares = mechanics.attacks(PositionGenerator.initialPosition(), new Square(1, 7));
        List<Square> reference = new ArrayList<>(List.of(new Square(2, 6)));

        assertEquals(reference, squares);
    }

    @Test
    void attackingIndexesCenter() {
        List<Square> squares = mechanics.attacks(PositionGenerator.initialPosition(), new Square(1, 2));
        List<Square> reference = new ArrayList<>(List.of(
                new Square(2, 1),
                new Square(2, 3)
        ));

        assertEquals(reference, squares);
    }

    @Test
    void moveFromStartingCell() {
        List<Square> cells = mechanics.moves(PositionGenerator.initialPosition(), new Square(1, 2));
        List<Square> reference = new ArrayList<>(List.of(new Square(2, 2), new Square(3, 2)));

        assertEquals(reference, cells);
    }

    @Test
    void moveFromNonStartingCell() {
        List<Square> cells = mechanics.moves(PositionGenerator.initialPosition(), new Square(2, 2));
        List<Square> reference = new ArrayList<>(List.of(new Square(3, 2)));

        assertEquals(reference, cells);
    }

    @Test
    void capture() {
        List<Square> moves = mechanics.moves(PositionGenerator.customPosition(pawnCapture), new Square(1, 3));
        moves.sort(null);
        List<Square> reference = new ArrayList<>(List.of(
                new Square(2, 2),
                new Square(2, 3),
                new Square(3, 3)
        ));

        assertEquals(reference, moves);
    }


    private static final String[] pawnCapture = new String[]{
            "........",
            "...P....",
            "..r.....",
            "........",
            "........",
            "........",
            "........",
            "........"
    };
}