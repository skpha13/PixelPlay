package org.example.backend.cardGames.cardDeck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    private final Deck deck = new Deck();
    @Test
    void getCards() {

        assertEquals(52, deck.getCards().size());

    }

    @Test
    void setCards() {

        Deck deck2 = new Deck();
        deck2.shuffle();
        deck.setCards(deck2.getCards());
        assertEquals(deck2, deck);
    }

    @Test
    void shuffle() {

            Deck deck2 = new Deck();
            deck2.shuffle();
    }

    @Test
    void draw() {

            Card card = deck.draw();
            assertEquals(51, deck.getCards().size());
            assertNotNull(card);
    }
}