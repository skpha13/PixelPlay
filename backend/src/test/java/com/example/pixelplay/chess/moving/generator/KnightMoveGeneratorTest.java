package com.example.pixelplay.chess.moving.generator;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.moving.MoveGenerator;
import com.example.pixelplay.chess.position.Position;
import com.example.pixelplay.chess.position.PositionGenerator;
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