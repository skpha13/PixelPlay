package org.example.backend.cardGames.blackjack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackjackAIGameTest {

    private final BlackjackAIGame game = new BlackjackAIGame();

    @Test
    void startGame() {

        game.startGame();
        assertEquals(2, game.getDealer().getHand().getNumberOfCards());
        assertEquals(2, game.getPlayer().getHand().getNumberOfCards());
    }

    @Test
    void playerHit() {

        game.playerHit();
        assertEquals(1, game.getPlayer().getHand().getNumberOfCards());
    }

    @Test
    void playerStand() {

        game.playerStand();
    }

    @Test
    void playerDoubleDown() {

        try {
            game.playerDoubleDown();
            assertEquals(3, game.getPlayer().getHand().getNumberOfCards());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void playerSplit() {

        try {
            game.playerSplit();
            assertTrue(game.getPlayer().isSplit());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void switchToSplitHand() {

        try {
            game.switchToSplitHand();
            assertTrue(game.getPlayer().isSplit());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void switchToMainHand() {

            game.switchToMainHand();
    }

    @Test
    void getGameState() {

            game.getGameState();
    }

    @Test
    void getPlayer() {

        game.getPlayer();
    }

    @Test
    void getDealer() {

        game.getDealer();
    }

    @Test
    void getDeck() {

        game.getDeck();
    }
}