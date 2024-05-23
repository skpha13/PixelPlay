package org.example.backend.cardGames.blackjack;

import org.example.backend.cardGames.cardDeck.Card;

public class Dealer {

    private Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    @Override
    public String toString() {
        return "Dealer's hand: " + hand;
    }
}
