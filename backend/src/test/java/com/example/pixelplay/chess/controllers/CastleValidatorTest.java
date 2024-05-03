package com.example.pixelplay.chess.controllers;

import com.example.pixelplay.chess.position.PositionGenerator;
import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.position.Position;
import com.example.pixelplay.chess.moving.castle.CastleValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CastleValidatorTest {
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
}