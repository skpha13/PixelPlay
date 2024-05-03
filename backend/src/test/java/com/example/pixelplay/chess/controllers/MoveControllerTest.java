package com.example.pixelplay.chess.controllers;

import com.example.pixelplay.chess.PositionGenerator;
import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.Position;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.exceptions.IncorrectMoveException;
import com.example.pixelplay.chess.moving.GeneralMoveValidator;
import com.example.pixelplay.chess.moving.MoveValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveControllerTest {
    private final Position position = PositionGenerator.initialPosition();

    @Test
    void isValid() {
        AttackController attackController = new AttackController(position);
        MoveValidator moveValidator = new GeneralMoveValidator(position);

        Move pawnMove = new Move(new Square(1, 0), new Square(2, 0));
        Move pawnMove2 = new Move(new Square(1, 0), new Square(3, 0));
        Move knightMove = new Move(new Square(0, 1), new Square(2, 0));

        assertTrue(moveValidator.isValid(pawnMove));
        assertTrue(moveValidator.isValid(pawnMove2));
        assertTrue(moveValidator.isValid(knightMove));

        Move kingMove = new Move(new Square(0, 4), new Square(0, 5));
        assertThrows(IncorrectMoveException.class,() -> moveValidator.isValid(kingMove));
    }
}