package org.example.backend.connectFour;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class GameAITests {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testMove() {
        game.dropPiece(0);
        game.dropPiece(GameAI.minimax(game.getBoard(), 5, game.getPlayerTurn().value, Integer.MIN_VALUE, Integer.MAX_VALUE, false)[1]);
        game.dropPiece(0);
        game.dropPiece(GameAI.minimax(game.getBoard(), 5, game.getPlayerTurn().value, Integer.MIN_VALUE, Integer.MAX_VALUE, false)[1]);
        game.dropPiece(0);
        game.dropPiece(GameAI.minimax(game.getBoard(), 5, game.getPlayerTurn().value, Integer.MIN_VALUE, Integer.MAX_VALUE, false)[1]);
        game.dropPiece(1);
        game.dropPiece(GameAI.minimax(game.getBoard(), 5, game.getPlayerTurn().value, Integer.MIN_VALUE, Integer.MAX_VALUE, false)[1]);
        game.dropPiece(2);
        game.dropPiece(GameAI.minimax(game.getBoard(), 5, game.getPlayerTurn().value, Integer.MIN_VALUE, Integer.MAX_VALUE, false)[1]);
        game.dropPiece(3);
        game.dropPiece(GameAI.minimax(game.getBoard(), 5, game.getPlayerTurn().value, Integer.MIN_VALUE, Integer.MAX_VALUE, false)[1]);
        System.out.println(Arrays.deepToString(game.getBoard()));
    }
}
