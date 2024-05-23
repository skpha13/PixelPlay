package org.example.backend.cardGames.blackjack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DealerTest {

    private Dealer dealer = new Dealer();

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