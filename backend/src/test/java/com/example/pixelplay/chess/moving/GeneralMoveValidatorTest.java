package com.example.pixelplay.chess.moving;

import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.moving.castle.CastleValidator;
import com.example.pixelplay.chess.moving.exceptions.IncorrectMoveException;
import com.example.pixelplay.chess.moving.exceptions.KingCheckedException;
import com.example.pixelplay.chess.position.Position;
import com.example.pixelplay.chess.position.PositionGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneralMoveValidatorTest {
    private final Position position = PositionGenerator.initialPosition();

    @Test
    void isValid() {
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

    private final Position kingInCheckPosition = PositionGenerator.customPosition(
            new String[] {
                    "..KQ....",
                    "........",
                    "........",
                    "..r.....",
                    "........",
                    "........",
                    "........",
                    "........",
            }
    );

    private final String[] shortCastle = new String[]{
            "...R....",
            "........",
            "........",
            "........",
            "........",
            "........",
            "........",
            "r...k..r"
    };
    private final String[] longCastle = new String[]{
            "........",
            "........",
            "........",
            "........",
            "...B....",
            "...B....",
            "........",
            "r...k..r"
    };
    private final String[] noCastle = new String[]{
            "........",
            "........",
            "....Q...",
            "........",
            "........",
            "........",
            "........",
            "r...k..r"
    };

    private final Position shortCastlePosition = PositionGenerator.customPosition(shortCastle);
    private final Position longCastlePosition = PositionGenerator.customPosition(longCastle);
    private final Position noCastlePosition = PositionGenerator.customPosition(noCastle);

    @Test
    void canShortCastle() {
        CastleValidator castlingController = new CastleValidator(shortCastlePosition);
        assertTrue(castlingController.canShortCastle(Color.BLACK));
        assertFalse(castlingController.canLongCastle(Color.BLACK));
    }

    @Test
    void canLongCastle() {
        CastleValidator castlingController = new CastleValidator(longCastlePosition);
        assertTrue(castlingController.canLongCastle(Color.BLACK));
        assertFalse(castlingController.canShortCastle(Color.BLACK));
    }

    @Test
    void noCastle() {
        CastleValidator castlingController = new CastleValidator(noCastlePosition);
        assertFalse(castlingController.canShortCastle(Color.BLACK));
        assertFalse(castlingController.canLongCastle(Color.BLACK));
    }

    @Test
    void move_kingStillInCheck() {
        MoveValidator validator = new GeneralMoveValidator(kingInCheckPosition);
        Move move = new Move(new Square(0, 3), new Square(0, 4));
        assertThrows(KingCheckedException.class, () -> validator.isValid(move));
    }
}