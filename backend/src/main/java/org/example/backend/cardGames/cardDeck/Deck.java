package org.example.backend.cardGames.cardDeck;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        for (String suit : new String[] {"Hearts", "Diamonds", "Clubs", "Spades"}) {
            for (String value : new String[] {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"}) {
                this.cards.add(new Card(suit, value));
            }
        }
    }

    public ArrayList<Card> getCards() {
        return new ArrayList<>(this.cards);
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public Card draw() {
        if (this.cards.isEmpty()) {
            return null;
        }
        return this.cards.remove(this.cards.size() - 1);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Card card : this.cards) {
            result.append(card).append("\n");
        }
        return result.toString();
    }

    @Override
    public int hashCode() {
        return this.cards.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Deck deck = (Deck) obj;
        return cards.equals(deck.cards);
    }
}
