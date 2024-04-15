package com.example.pixelplay.chess;

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
}