package com.example.pixelplay.chess.moving;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.moving.exceptions.KingCheckedException;
import com.example.pixelplay.chess.position.Position;
import com.example.pixelplay.chess.position.PositionGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneralMoveValidatorTest {
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

    @Test
    void move_kingStillInCheck() {
        MoveValidator validator = new GeneralMoveValidator(kingInCheckPosition);
        Move move = new Move(new Square(0, 3), new Square(0, 4));
        assertThrows(KingCheckedException.class, () -> validator.isValid(move));
    }
}