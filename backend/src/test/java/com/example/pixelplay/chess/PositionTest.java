package com.example.pixelplay.chess;

import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.controllers.AttackController;
import com.example.pixelplay.chess.moving.MoveHandler;
import com.example.pixelplay.chess.moving.GeneralHandler;
import com.example.pixelplay.chess.position.Position;
import com.example.pixelplay.chess.position.PositionGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    public void verifyCheck() {
        Position position = PositionGenerator.customPosition(positionWhiteCheckKnight);
        AttackController attackController = new AttackController(position);
        boolean whiteChecked = attackController.kingIsInCheck(Color.WHITE);
        assertTrue(whiteChecked);

        position = PositionGenerator.customPosition(positionWhiteCheckKnight);
        attackController = new AttackController(position);
        whiteChecked = attackController.kingIsInCheck(Color.WHITE);
        assertTrue(whiteChecked);

    }

    @Test
    public void verifyNotCheck() {
        Position position = PositionGenerator.customPosition(positionWhiteCheckKnight);
        AttackController attackController = new AttackController(position);
        boolean whiteChecked = attackController.kingIsInCheck(Color.BLACK);
        assertFalse(whiteChecked);

        position = PositionGenerator.customPosition(positionWhiteCheckKnight);
        attackController = new AttackController(position);
        whiteChecked = attackController.kingIsInCheck(Color.BLACK);
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