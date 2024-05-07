package com.example.pixelplay.chess.moving.generator;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.PieceType;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.moving.MoveGenerator;
import com.example.pixelplay.chess.position.Position;
import com.example.pixelplay.chess.position.PositionGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PawnMoveGeneratorTest {
    private final Position position = PositionGenerator.customPosition(
            new String[] {
                    ".....K..",
                    "PPPPP...",
                    ".R......",
                    "..p.....",
                    ".pP.....",
                    "...p....",
                    "ppp....P",
                    "k.....q.",
            }
    );

    @Test
    public void getLegalMoves_enPessant() {
        Square pawn = new Square(4, 2);
        position.setEnPessantSquare(new Square(5, 1));
        MoveGenerator moveGenerator = new PawnMoveGenerator(position, pawn);

        List<Move> moves = (ArrayList<Move>)(moveGenerator.getLegalMoves());
        List<Move> expected = new ArrayList<Move>(List.of(
                new Move(pawn, new Square(5, 1)),
                new Move(pawn, new Square(5, 2)),
                new Move(pawn, new Square(5, 3))
        ));

        Collections.sort(moves);
        Collections.sort(expected);

        assertEquals(expected, moves);
    }

    @Test
    public void getLegalMoves_promotion() {
        Square pawn = new Square(6, 7);
        MoveGenerator moveGenerator = new PawnMoveGenerator(position, pawn);

        List<Move> moves = (ArrayList<Move>)(moveGenerator.getLegalMoves());
        List<Move> expected = new ArrayList<Move>(List.of(
                new Move(pawn, new Square(7, 6), PieceType.Queen),
                new Move(pawn, new Square(7, 6), PieceType.Rook),
                new Move(pawn, new Square(7, 6), PieceType.Bishop),
                new Move(pawn, new Square(7, 6), PieceType.Knight),
                new Move(pawn, new Square(7, 7),  PieceType.Queen),
                new Move(pawn, new Square(7, 7),  PieceType.Rook),
                new Move(pawn, new Square(7, 7),  PieceType.Bishop),
                new Move(pawn, new Square(7, 7),  PieceType.Knight)
        ));

        Collections.sort(moves);
        Collections.sort(expected);

        assertEquals(expected, moves);
    }

}