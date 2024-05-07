package com.example.pixelplay.chess.moving.generator;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.position.Position;
import com.example.pixelplay.chess.position.PositionGenerator;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KingPieceMoveGeneratorTest {
    private final Position position = PositionGenerator.customPosition(
            new String[] {
                    "R...K..R",
                    "PPPPPhPP",
                    "........",
                    "........",
                    "........",
                    "........",
                    "....k...",
                    "........",
            }
    );

    @Test
    public void getLegalMoves() {
        Square start = new Square(0, 4);
        KingMoveGenerator kingMoveGenerator = new KingMoveGenerator(position, start);
        ArrayList<Move> moves = (ArrayList<Move>) kingMoveGenerator.getLegalMoves();


        ArrayList<Move> expected = new ArrayList<>(List.of(
                new Move(start, new Square(0, 5)),
                new Move(start, new Square(0, 6)),
                new Move(start, new Square(1, 5))
        ));

        Collections.sort(moves);
        Collections.sort(expected);

        assertArrayEquals(expected.toArray(), moves.toArray());
    }



}