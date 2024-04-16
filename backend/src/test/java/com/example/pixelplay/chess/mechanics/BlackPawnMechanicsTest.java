package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.PositionGenerator;
import com.example.pixelplay.chess.mechanics.pieces.BlackPawnMechanics;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlackPawnMechanicsTest {
    private final PieceMechanics mechanics = new BlackPawnMechanics();

    @Test
    void attackingIndexesLeftEdge() {
        List<Square> squares = mechanics.attacks(PositionGenerator.initialPosition(), new Square(6, 0));
        List<Square> reference = new ArrayList<>(List.of(new Square(5, 1)));

        assertEquals(reference, squares);
    }

    @Test
    void attackingIndexesRightEdge() {
        List<Square> squares = mechanics.attacks(PositionGenerator.initialPosition(), new Square(6, 7));
        List<Square> reference = new ArrayList<>(List.of(new Square(5, 6)));

        assertEquals(reference, squares);
    }

    @Test
    void attackingIndexesCenter() {
        List<Square> squares = mechanics.attacks(PositionGenerator.initialPosition(), new Square(6, 2));
        List<Square> reference = new ArrayList<>(List.of(
                new Square(5, 1),
                new Square(5, 3)
        ));

        assertEquals(reference, squares);
    }

    @Test
    void moveFromStartingCell() {
        List<Square> cells = mechanics.moves(PositionGenerator.initialPosition(), new Square(6, 2));
        List<Square> reference = new ArrayList<>(List.of(new Square(5, 2), new Square(4, 2)));

        cells.sort(null);
        reference.sort(null);
        assertEquals(reference, cells);
    }

    @Test
    void moveFromNonStartingCell() {
        List<Square> cells = mechanics.moves(PositionGenerator.initialPosition(), new Square(5, 2));
        List<Square> reference = new ArrayList<>(List.of(new Square(4, 2)));

        assertEquals(reference, cells);
    }

    @Test
    void capture() {
        List<Square> moves = mechanics.moves(PositionGenerator.customPosition(pawnCapture), new Square(2, 2));
        List<Square> reference = new ArrayList<>(List.of(
                new Square(1, 2),
                new Square(1, 3)
        ));

        assertEquals(reference, moves);
    }


    private static final String[] pawnCapture = new String[]{
            "........",
            "...R....",
            "..p.....",
            "........",
            "........",
            "........",
            "........",
            "........"
    };
}