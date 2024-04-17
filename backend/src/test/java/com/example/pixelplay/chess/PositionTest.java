package com.example.pixelplay.chess;

import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.base.NumberBoard;
import com.example.pixelplay.chess.base.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    public void verifyCheck() {
        Position position = PositionGenerator.customPosition(positionWhiteCheckKnight);
        boolean whiteChecked = position.isChecked(Color.WHITE);
        assertTrue(whiteChecked);

        position = PositionGenerator.customPosition(positionWhiteCheckKnight);
        whiteChecked = position.isChecked(Color.WHITE);
        assertTrue(whiteChecked);

    }

    @Test
    public void verifyNotCheck() {
        Position position = PositionGenerator.customPosition(positionWhiteCheckKnight);
        boolean whiteChecked = position.isChecked(Color.BLACK);
        assertFalse(whiteChecked);

        position = PositionGenerator.customPosition(positionWhiteCheckKnight);
        whiteChecked = position.isChecked(Color.BLACK);
        assertFalse(whiteChecked);

    }

    private static final String[] positionWhiteCheckRook = new String[] {
            "........",
            ".....r..",
            "..k.....",
            "........",
            "....P...",
            "........",
            ".....KB.",
            "........"
    };

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

    @Test
    public void attackedSquares() {
        Position position = PositionGenerator.customPosition(positionWhiteCheckKnight);
        NumberBoard attacked = position.getAttackBoardByColor(Color.BLACK);
        NumberBoard reference = NumberBoard.fromNumberMatrix(new short[][]{
                {1, 1, 1, 0, 1, 1, 1, 0},
                {2, 1, 3, 1, 1, 0, 1, 1},
                {1, 1, 1, 1, 1, 2, 1, 0},
                {0, 0, 1, 1, 0, 1, 1, 1},
                {0, 0, 1, 0, 0, 1, 0, 0},
                {0, 1, 1, 0, 0, 0, 1, 0},
                {1, 0, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
        });

        assertEquals(reference.toString(),attacked.toString());
    }
}