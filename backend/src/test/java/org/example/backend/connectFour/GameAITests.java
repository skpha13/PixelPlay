package org.example.backend.connectFour;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameAITests {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testAIMoveAfterInitialMove() {
        game.dropPiece(0);
        int aiMove = GameAI.minimax(game.getBoard(), 6, game.getPlayerTurn().value, Integer.MIN_VALUE, Integer.MAX_VALUE, true)[1];
        game.dropPiece(aiMove);
        assertNotEquals(0, aiMove);
    }

    @Test
    public void testAISequenceOfMoves() {
        playMoves(new int[]{4, 5, 3, 6});
        assertFalse(game.getIsTie());
        assertNull(game.getWinner());
    }

    @Test
    public void testAISequenceOfMovesWithRepeatedColumn() {
        playMoves(new int[]{5, 5, 5, 6, 6, 6, 4, 4, 4, 4});
        assertFalse(game.getIsTie());
        assertEquals(game.getWinner(), Player.YELLOW);
    }

    @Test
    public void testAISequenceInDifferentColumns() {
        playMoves(new int[]{3, 4, 2});
        assertFalse(game.getIsTie());
        assertNull(game.getWinner());
    }

    @Test
    public void testAIMoveWithDepthTwo() {
        playMoves(new int[]{2, 3, 3, 3, 3}, 2);
        assertFalse(game.getIsTie());
        assertNull(game.getWinner());
    }

    private void playMoves(int[] moves) {
        playMoves(moves, 2);
    }

    private void playMoves(int[] moves, int depth) {
        for (int move : moves) {
            game.dropPiece(move);
            int aiMove = GameAI.minimax(game.getBoard(), depth, game.getPlayerTurn().value, Integer.MIN_VALUE, Integer.MAX_VALUE, true)[1];
            game.dropPiece(aiMove);
        }
    }
}
