package org.example.backend.cardGames.blackjack;

import org.example.backend.cardGames.cardDeck.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackjackPVPGameTest {

    private final BlackjackPVPGame game = new BlackjackPVPGame();

    @Test
    void player1Hit() {

        game.player1Hit();
        assertEquals(3, game.getPlayer1().getHand().getNumberOfCards());
    }

    @Test
    void player2Hit() {

            game.player2Hit();
            assertEquals(3, game.getPlayer2().getHand().getNumberOfCards());
    }

    @Test
    void player1Stand() {

            game.player1Stand();
            assertTrue(game.isPlayer1Stand());
    }

    @Test
    void player2Stand() {

                game.player2Stand();
                assertTrue(game.isPlayer2Stand());
    }

    @Test
    void isPlayer1Bust() {

        game.getPlayer1().getHand().addCard(new Card("Hearts", "King"));
        game.getPlayer1().getHand().addCard(new Card("Hearts", "King"));
        game.getPlayer1().getHand().addCard(new Card("Hearts", "King"));
        assertTrue(game.isPlayer1Bust());
    }

    @Test
    void isPlayer2Bust() {

            game.getPlayer2().getHand().addCard(new Card("Hearts", "King"));
            game.getPlayer2().getHand().addCard(new Card("Hearts", "King"));
            game.getPlayer2().getHand().addCard(new Card("Hearts", "King"));
            assertTrue(game.isPlayer2Bust());
    }

    @Test
    void getPlayer1() {

            game.getPlayer1();
    }

    @Test
    void getPlayer2() {

                game.getPlayer2();
    }

    @Test
    void isPlayer1Stand() {

        game.player1Stand();
        assertTrue(game.isPlayer1Stand());
    }

    @Test
    void isPlayer2Stand() {

            game.player2Stand();
            assertTrue(game.isPlayer2Stand());
    }

    @Test
    void getGameState() {

            game.getGameState();
    }
}