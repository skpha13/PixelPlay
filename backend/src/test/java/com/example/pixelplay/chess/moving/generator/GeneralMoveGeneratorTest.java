package com.example.pixelplay.chess.moving.generator;

import com.example.pixelplay.chess.position.Position;
import com.example.pixelplay.chess.position.PositionGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneralMoveGeneratorTest {
    private final Position position = PositionGenerator.customPosition(
            new String[] {
                    "R..K...R",
                    "PPPPPPPP",
                    "BH....HB",
                    "Q.....p..",
                    "........",
                    "r...r...",
                    "........",
                    "...k....",
            }
    );
}