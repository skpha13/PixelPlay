package cardGames.blackjack;

import cardGames.cardDeck.Card;
import java.util.ArrayList;


public class Player {

    // useless class for the moment, will rethink later
    private int score;
    private int scoreSplit;
    private boolean doubleDown;
    private boolean split;
    private ArrayList<Card> hand;
    private ArrayList<Card> handSplit;

    public Player() {
        this.score = 0;
        this.scoreSplit = 0;
        this.doubleDown = false;
        this.split = false;
        this.hand = new ArrayList<>();
        this.handSplit = new ArrayList<>();
    }

    public int getScore() {
        return score;
    }

    public int getScoreSplit() {
        return scoreSplit;
    }

    public boolean getDoubleDown() {
        return doubleDown;
    }

    public boolean getSplit() {
        return split;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Card> getHandSplit() {
        return handSplit;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setScoreSplit(int scoreSplit) {
        this.scoreSplit = scoreSplit;
    }

    public void setDoubleDown(boolean doubleDown) {
        this.doubleDown = doubleDown;
    }

    public void setSplit(boolean split) {
        this.split = split;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public void setHandSplit(ArrayList<Card> handSplit) {
        this.handSplit = handSplit;
    }

    public void calculateScore(ArrayList<Card> hand) {
        int score = 0;
        int aces = 0;
        for (Card card : hand) {
            if (card.getValue().equals("Jack") || card.getValue().equals("Queen") || card.getValue().equals("King")) {
                score += 10;
            } else if (card.getValue().equals("Ace")) {
                aces++;
            } else {
                score += Integer.parseInt(card.getValue());
            }
        }
        for (int i = 0; i < aces; i++) {
            if (score + 11 <= 21) {
                score += 11;
            } else {
                score += 1;
            }
        }
        this.score = score;

    }


}
