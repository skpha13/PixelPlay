package org.example.backend.chess.logic.moving.generator;

import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.base.Square;
import org.example.backend.chess.logic.moving.MoveGenerator;
import org.example.backend.chess.logic.position.Position;
import org.example.backend.chess.logic.position.PositionGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KnightMoveGeneratorTest {
    private final Position position = PositionGenerator.customPosition(
            new String[] {
                    "..K.....",
                    ".....p..",
                    ".......H",
                    "........",
                    "........",
                    "........",
                    "..k.....",
                    "........"
            }
    );

    @Test
    public void getLegaMoves() {
        Square square = new Square(2, 7);
        MoveGenerator generator = new KnightMoveGenerator(position, square);

        List<Move> moves = generator.getLegalMoves();
        List<Move> expected = new ArrayList<>(List.of(
                new Move(square, new Square(0, 6)),
                new Move(square, new Square(1, 5)),
                new Move(square, new Square(3, 5)),
                new Move(square, new Square(4, 6))
        ));

        Collections.sort(moves);
        Collections.sort(expected);

        assertEquals(expected, moves);
    }

}