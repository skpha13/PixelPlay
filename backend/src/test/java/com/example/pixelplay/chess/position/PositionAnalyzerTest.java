package com.example.pixelplay.chess.position;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionAnalyzerTest {
    private Position checkmatePosition = PositionGenerator.customPosition(
            new String[] {
                    ".r....K.",
                    ".....PPP",
                    "........",
                    ".k......",
                    "........",
                    "........",
                    "........",
                    "........",
            }
    );

    @Test
    public void checkmate() {
        PositionAnalyzer analyzer = new PositionAnalyzer(checkmatePosition);

        assertTrue(analyzer.isCheckmate());
    }

}