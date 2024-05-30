package org.example.backend.chess;

import org.example.backend.chess.logic.base.Color;
import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.base.Square;
import org.example.backend.chess.logic.moving.MoveHandler;
import org.example.backend.chess.logic.moving.handler.GeneralHandler;
import org.example.backend.chess.logic.position.AttackCalculator;
import org.example.backend.chess.logic.position.Position;
import org.example.backend.chess.logic.position.PositionGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    public void verifyCheck() {
        Position position = PositionGenerator.customPosition(positionWhiteCheckKnight);
        AttackCalculator attackCalculator = new AttackCalculator(position);
        boolean whiteChecked = attackCalculator.kingIsInCheck(Color.WHITE);
        assertTrue(whiteChecked);

        position = PositionGenerator.customPosition(positionWhiteCheckKnight);
        attackCalculator = new AttackCalculator(position);
        whiteChecked = attackCalculator.kingIsInCheck(Color.WHITE);
        assertTrue(whiteChecked);

    }

    @Test
    public void verifyNotCheck() {
        Position position = PositionGenerator.customPosition(positionWhiteCheckKnight);
        AttackCalculator attackCalculator = new AttackCalculator(position);
        boolean whiteChecked = attackCalculator.kingIsInCheck(Color.BLACK);
        assertFalse(whiteChecked);

        position = PositionGenerator.customPosition(positionWhiteCheckKnight);
        attackCalculator = new AttackCalculator(position);
        whiteChecked = attackCalculator.kingIsInCheck(Color.BLACK);
        assertFalse(whiteChecked);

    }

    @Test
    public void verifyEnPessantFlag() {
        Position position = PositionGenerator.customPosition(enPessantSetup);
        Move move = new Move(new Square(1, 3), new Square(3, 3));
        MoveHandler moveHandler = new GeneralHandler(position);
        moveHandler.makeMove(move);
        assertTrue(position.canEnPessant(new Square(2, 3)));
    }

    private static final String[] positionWhiteCheckKnight = new String[] {
            "........",
            ".k...q..",
            ".p......",
            "........",
            "....hP..",
            "....PP..",
            ".Q...K..",
            "........"
    };

    private static final String[] enPessantSetup = new String[] {
            "........",
            "...P....",
            "........",
            "..p.....",
            "........",
            "........",
            "........",
            "........"
    };

}