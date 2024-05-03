package com.example.pixelplay.chess.controllers;

import com.example.pixelplay.chess.PositionGenerator;
import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.Position;
import com.example.pixelplay.chess.moving.validator.CastleValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CastleValidatorTest {
    private String[] shortCastle = new String[]{
            "...R....",
            "........",
            "........",
            "........",
            "........",
            "........",
            "........",
            "r...k..r"
    };
    private String[] longCastle = new String[]{
            "........",
            "........",
            "........",
            "........",
            "...B....",
            "...B....",
            "........",
            "r...k..r"
    };
    private String[] noCastle = new String[]{
            "........",
            "........",
            "....Q...",
            "........",
            "........",
            "........",
            "........",
            "r...k..r"
    };

    private final Position shortCastlePosition = PositionGenerator.customPosition(shortCastle, false);
    private final Position longCastlePosition = PositionGenerator.customPosition(longCastle, false);
    private final Position noCastlePosition = PositionGenerator.customPosition(noCastle, false);

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
}