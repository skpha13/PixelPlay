package com.example.pixelplay.chess.controllers;

import com.example.pixelplay.chess.PositionGenerator;
import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.base.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CastlingControllerTest {
    private String[] shortCastle = new String[]{
            ".....R..",
            "........",
            "........",
            "........",
            "........",
            "........",
            "........",
            "r..k...r"
    };
    private String[] longCastle = new String[]{
            "........",
            "........",
            "........",
            "........",
            "....B...",
            "....B...",
            "........",
            "r..k...r"
    };
    private String[] noCastle = new String[]{
            "........",
            "........",
            "...Q....",
            "........",
            "........",
            "........",
            "........",
            "r..k...r"
    };

    private final Position shortCastlePosition = PositionGenerator.customPosition(shortCastle, false);
    private final Position longCastlePosition = PositionGenerator.customPosition(longCastle, false);
    private final Position noCastlePosition = PositionGenerator.customPosition(noCastle, false);

    @Test
    void canShortCastle() {
        AttackController attackController = new AttackController(shortCastlePosition);
        CastlingController castlingController = new CastlingController(shortCastlePosition, attackController, false);
        assertTrue(castlingController.canShortCastle(Color.BLACK));
        assertFalse(castlingController.canLongCastle(Color.BLACK));
    }

    @Test
    void canLongCastle() {
        AttackController attackController = new AttackController(longCastlePosition);
        CastlingController castlingController = new CastlingController(longCastlePosition, attackController, false);
        assertTrue(castlingController.canLongCastle(Color.BLACK));
        assertFalse(castlingController.canShortCastle(Color.BLACK));
    }

    @Test
    void noCastle() {
        AttackController attackController = new AttackController(noCastlePosition);
        CastlingController castlingController = new CastlingController(noCastlePosition, attackController, false);
        assertFalse(castlingController.canShortCastle(Color.BLACK));
        assertFalse(castlingController.canLongCastle(Color.BLACK));
    }
}