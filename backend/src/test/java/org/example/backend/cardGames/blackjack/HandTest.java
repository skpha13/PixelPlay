package org.example.backend.cardGames.blackjack;

import org.example.backend.cardGames.cardDeck.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    private Hand hand = new Hand();
    @Test
    void getHand() {

        hand.getHand();

    }

    @Test
    void setHand() {

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card("Hearts", "Ace"));
        cards.add(new Card("Hearts", "2"));
        hand.setHand(cards);
    }

    @Test
    void addCard() {

        hand.addCard(new Card("Hearts", "Ace"));
        assertEquals(1, hand.getNumberOfCards());
    }

    @Test
    void clearHand() {

            hand.clearHand();
            assertEquals(0, hand.getNumberOfCards());
    }

    @Test
    void getNumberOfCards() {

        assertEquals(0, hand.getNumberOfCards());
        hand.addCard(new Card("Hearts", "Ace"));
        assertEquals(1, hand.getNumberOfCards());
    }

    @Test
    void calculateScore() {

            hand.addCard(new Card("Hearts", "Ace"));
            hand.addCard(new Card("Hearts", "2"));
            hand.addCard(new Card("Hearts", "3"));
            assertEquals(16, hand.calculateScore());
    }
}