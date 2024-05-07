package com.example.pixelplay.chess.moving.generator;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.moving.PieceMoveGenerator;
import com.example.pixelplay.chess.position.Position;
import com.example.pixelplay.chess.position.PositionGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LongRangePieceMoveGeneratorTest {
    private final Position position = PositionGenerator.customPosition(
            new String[] {
                    "..k.....",
                    "........",
                    "...b....",
                    ".p.R....",
                    "...K....",
                    "........",
                    "........",
                    "........",
            }
    );

    @Test
    public void getLegalMoves() {
        Square square = new Square(3, 3);
        PieceMoveGenerator pieceMoveGenerator = new LongRangeMoveGenerator(position, square);

        List<Move> moves = pieceMoveGenerator.getLegalMoves();
        List<Move> expected = new ArrayList<>(List.of(
                new Move(square, new Square(2,3 )),
                new Move(square, new Square(3,1 )),
                new Move(square, new Square(3,2 )),
                new Move(square, new Square(3,4 )),
                new Move(square, new Square(3,5 )),
                new Move(square, new Square(3,6 )),
                new Move(square, new Square(3,7 ))
        ));

        Collections.sort(moves);
        Collections.sort(expected);

        assertEquals(expected, moves);
    }

}