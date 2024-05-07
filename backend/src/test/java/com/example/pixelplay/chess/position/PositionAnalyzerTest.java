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

    private Position stalematePosition = PositionGenerator.customPosition(
            new String[] {
                    ".b....K.",
                    "..q.....",
                    "........",
                    ".k......",
                    ".....r.r",
                    "........",
                    "........",
                    "........",
            }
    );

    @Test
    public void stalemate() {
        PositionAnalyzer analyzer = new PositionAnalyzer(stalematePosition);

        assertTrue(analyzer.isStalemate());
    }

}