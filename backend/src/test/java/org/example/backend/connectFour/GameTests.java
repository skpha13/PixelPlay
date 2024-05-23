package org.example.backend.connectFour;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testDropPiece() {
        game.dropPiece(0);
        assertEquals(1, game.getBoard()[5][0]);
        assertEquals(Player.YELLOW, game.getPlayerTurn());
    }

    @Test
    public void testWinHorizontally() {
        game.dropPiece(0);
        game.dropPiece(0);
        game.dropPiece(1);
        game.dropPiece(1);
        game.dropPiece(2);
        game.dropPiece(2);
        game.dropPiece(3);
        assertEquals(Player.RED, game.getWinner());
    }

    @Test
    public void testWinVertically() {
        game.dropPiece(0);
        game.dropPiece(1);
        game.dropPiece(0);
        game.dropPiece(1);
        game.dropPiece(0);
        game.dropPiece(1);
        game.dropPiece(0);
        assertEquals(Player.RED, game.getWinner());
    }

    @Test
    public void testWinDiagonallyUp() {
        game.dropPiece(3);
        game.dropPiece(4);
        game.dropPiece(4);
        game.dropPiece(5);
        game.dropPiece(5);
        game.dropPiece(6);
        game.dropPiece(5);
        game.dropPiece(6);
        game.dropPiece(6);
        game.dropPiece(0);
        game.dropPiece(6);
        System.out.println(Arrays.deepToString(game.getBoard()));
        assertEquals(Player.RED, game.getWinner());
    }

    @Test
    public void testWinDiagonallyDown() {
        game.dropPiece(0);
        game.dropPiece(1);
        game.dropPiece(1);
        game.dropPiece(2);
        game.dropPiece(2);
        game.dropPiece(3);
        game.dropPiece(2);
        game.dropPiece(3);
        game.dropPiece(3);
        game.dropPiece(0);
        game.dropPiece(3);
        assertEquals(Player.RED, game.getWinner());
    }

    @Test
    public void testWinYellow() {
        game.dropPiece(0);
        game.dropPiece(1);
        game.dropPiece(1);
        game.dropPiece(2);
        game.dropPiece(2);
        game.dropPiece(3);
        game.dropPiece(3);
        game.dropPiece(4);
        game.dropPiece(4);
        assertEquals(Player.YELLOW, game.getWinner());
    }
}
