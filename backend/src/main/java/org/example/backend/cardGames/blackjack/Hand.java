package org.example.backend.cardGames.blackjack;

import org.example.backend.cardGames.cardDeck.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private final ArrayList<Card> hand;

    public Hand() {
        this.hand = new ArrayList<>();
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }

    public void setHand(ArrayList<Card> newHand) {
        this.hand.clear();
        this.hand.addAll(newHand);
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void clearHand() {
        hand.clear();
    }

    public int getNumberOfCards() {
        return hand.size();
    }

    public int calculateScore() {
        int score = 0;
        int aces = 0;
        for (Card card : hand) {
            switch (card.getValue()) {
                case "2":
                    score += 2;
                    break;
                case "3":
                    score += 3;
                    break;
                case "4":
                    score += 4;
                    break;
                case "5":
                    score += 5;
                    break;
                case "6":
                    score += 6;
                    break;
                case "7":
                    score += 7;
                    break;
                case "8":
                    score += 8;
                    break;
                case "9":
                    score += 9;
                    break;
                case "10":
                case "Jack":
                case "Queen":
                case "King":
                    score += 10;
                    break;
                case "Ace":
                    aces++;
                    break;
            }
        }
        while (aces > 0) {
            if (score + 11 <= 21) {
                score += 11;
            } else {
                score += 1;
            }
            aces--;
        }
        return score;
    }
}
