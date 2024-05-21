package org.example.backend.cardGames.cardDeck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    private Card card1 = new Card("Hearts", "Ace");

    @Test
    void getSuit() {
        assertEquals("Hearts", card1.getSuit());

    }

    @Test
    void getValue() {
        assertEquals("Ace", card1.getValue());
    }

    @Test
    void hasSameSuit() {

        Card card2 = new Card("Hearts", "2");
        assertTrue(card1.hasSameSuit(card2));

        Card card3 = new Card("Spades", "2");
        assertFalse(card1.hasSameSuit(card3));
    }

    @Test
    void hasSameValue() {

        Card card2 = new Card("Hearts", "Ace");
        assertTrue(card1.hasSameValue(card2));

        Card card3 = new Card("Hearts", "2");
        assertFalse(card1.hasSameValue(card3));
    }

    @Test
    void testEquals() {

        Card card2 = new Card("Hearts", "Ace");
        assertTrue(card1.equals(card2));

        Card card3 = new Card("Hearts", "2");
        assertFalse(card1.equals(card3));
    }

}