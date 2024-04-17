package com.example.pixelplay.chess.controllers;

import com.example.pixelplay.chess.PositionGenerator;
import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Position;
import com.example.pixelplay.chess.base.Square;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveControllerTest {
    private final Position position = PositionGenerator.initialPosition();

    @Test
    void isValid() {
        AttackController attackController = new AttackController(position);
        CastlingController castlingController = new CastlingController(position, attackController, false);
        MoveController moveController = new MoveController(position, attackController, castlingController);

        Move pawnMove = new Move(new Square(1, 0), new Square(2, 0));
        Move pawnMove2 = new Move(new Square(1, 0), new Square(3, 0));
        Move knightMove = new Move(new Square(0, 1), new Square(2, 0));

        assertTrue(moveController.isValid(pawnMove, Color.WHITE));
        assertTrue(moveController.isValid(pawnMove2, Color.WHITE));
        assertTrue(moveController.isValid(knightMove, Color.WHITE));

        Move kingMove = new Move(new Square(0, 4), new Square(0, 5));
        assertFalse(moveController.isValid(kingMove, Color.WHITE));
    }
}