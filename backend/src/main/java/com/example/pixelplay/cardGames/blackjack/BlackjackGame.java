package cardGames.blackjack;

import cardGames.cardDeck.Card;
import cardGames.cardDeck.Deck;

import java.util.ArrayList;

public class BlackjackGame {

    //it works for hit, stand, double down, the logic is good. problem is with split. also, maybe will reformat later to look better
    private Deck deck;
    private int playerScore;
    private int playerScoreSplit;
    private ArrayList<Card> playerHand;
    private ArrayList<Card> playerHandSplit;
    boolean doubleDown;
    boolean split;
    private int dealerScore;
    private ArrayList<Card> dealerHand;

    public BlackjackGame() {
        this.deck = new Deck();
        this.deck.shuffle();
        this.playerScore = 0;
        this.playerScoreSplit = 0;
        this.playerHand = new ArrayList<>();
        this.playerHandSplit = new ArrayList<>();
        this.doubleDown = false;
        this.split = false;
        this.dealerScore = 0;
        this.dealerHand = new ArrayList<>();
    }

    public Deck getDeck() {
        return deck;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getPlayerScoreSplit() {
        return playerScoreSplit;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public ArrayList<Card> getPlayerHandSplit() {
        return playerHandSplit;
    }

    public boolean getDoubleDown() {
        return doubleDown;
    }

    public boolean getSplit() {
        return split;
    }

    public int getDealerScore() {
        return dealerScore;
    }

    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public void setPlayerScoreSplit(int playerScoreSplit) {
        this.playerScoreSplit = playerScoreSplit;
    }

    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public void setPlayerHandSplit(ArrayList<Card> playerHandSplit) {
        this.playerHandSplit = playerHandSplit;
    }

    public void setDoubleDown(boolean doubleDown) {
        this.doubleDown = doubleDown;
    }

    public void setSplit(boolean split) {
        this.split = split;
    }

    public void setDealerScore(int dealerScore) {
        this.dealerScore = dealerScore;
    }

    public void setDealerHand(ArrayList<Card> dealerHand) {
        this.dealerHand = dealerHand;
    }

    public void deal() {
        this.playerHand.add(this.deck.draw());
        this.playerHand.add(this.deck.draw());
        this.playerScore = calculateScore(this.playerHand);
        this.dealerHand.add(this.deck.draw());
        this.dealerHand.add(this.deck.draw());
        this.dealerScore = calculateScore(this.dealerHand);
    }

    public int calculateScore(ArrayList<Card> hand) {
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
        return score;
    }

    public void hit() {
        if (this.doubleDown==false && this.split==false) {
            this.doubleDown = true;
            this.split = true;
            this.playerHand.add(this.deck.draw());
            this.playerScore = calculateScore(this.playerHand);
        }
        else
        {
            this.playerHand.add(this.deck.draw());
            this.playerScore = calculateScore(this.playerHand);
        }

    }

    public void split() {
        this.playerHandSplit.add(this.playerHand.remove(1));
        this.playerHand.add(this.deck.draw());
        this.playerHandSplit.add(this.deck.draw());
        this.playerScore = calculateScore(this.playerHand);
        this.playerScoreSplit = calculateScore(this.playerHandSplit);
        this.split = true;
        //The split is not ready for the moment, will find a solution soon
    }

    public void doubleDown() {
        if (this.doubleDown==false) {
            this.playerHand.add(this.deck.draw());
            this.playerScore = calculateScore(this.playerHand);
            this.stand();
        }
    }

    public void stand() {
        while (this.dealerScore < 17) {
            this.dealerHand.add(this.deck.draw());
            this.dealerScore = calculateScore(this.dealerHand);
        }
        this.checkWinner();
    }

    public String checkWinner() {
        if (this.playerScore > 21) {
            return "Player busts! Dealer wins!";
        } else if (this.dealerScore > 21) {
            return "Dealer busts! Player wins!";
        } else if (this.playerScore > this.dealerScore) {
            return "Player wins!";
        } else if (this.dealerScore > this.playerScore) {
            return "Dealer wins!";
        } else {
            return "It's a tie!";
        }
    }

}
