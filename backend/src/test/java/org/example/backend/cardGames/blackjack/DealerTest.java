package org.example.backend.cardGames.blackjack;

import org.junit.jupiter.api.Test;

class DealerTest {

    private final Dealer dealer = new Dealer();

    @Test
    void getHand() {

        dealer.getHand();
    }

    @Test
    void setHand() {

            Hand hand = new Hand();
            dealer.setHand(hand);
    }

    @Test
    void testToString() {

            dealer.toString();
    }
}