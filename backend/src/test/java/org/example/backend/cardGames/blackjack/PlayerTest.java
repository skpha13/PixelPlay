package org.example.backend.cardGames.blackjack;

import org.junit.jupiter.api.Test;

class PlayerTest {

    private final Player player = new Player();

    @Test
    void getHand() {

        player.getHand();

    }

    @Test
    void setHand() {

        Hand hand = new Hand();
        player.setHand(hand);
    }

    @Test
    void getSplitHand() {

        player.getSplitHand();
    }

    @Test
    void setSplitHand() {

        Hand hand = new Hand();
        player.setSplitHand(hand);
    }

    @Test
    void isSplit() {

        player.isSplit();
    }

    @Test
    void setSplit() {

        player.setSplit(true);
    }

    @Test
    void isDoubleDown() {

        player.isDoubleDown();
    }

    @Test
    void setDoubleDown() {

        player.setDoubleDown(true);
    }
}