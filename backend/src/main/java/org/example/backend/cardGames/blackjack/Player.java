package org.example.backend.cardGames.blackjack;

public class Player {

    private Hand hand;
    private Hand splitHand;
    private boolean isSplit;
    private boolean doubleDown;

    public Player() {
        this.hand = new Hand();
        this.splitHand = null;
        this.isSplit = false;
        this.doubleDown = false;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Hand getSplitHand() {
        return splitHand;
    }

    public void setSplitHand(Hand splitHand) {
        this.splitHand = splitHand;
    }

    public boolean isSplit() {
        return isSplit;
    }

    public void setSplit(boolean split) {
        isSplit = split;
    }

    public boolean isDoubleDown() {
        return doubleDown;
    }

    public void setDoubleDown(boolean doubleDown) {
        this.doubleDown = doubleDown;
    }
}
