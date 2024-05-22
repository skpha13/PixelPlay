package org.example.backend.cardGames.blackjack;

import org.example.backend.cardGames.cardDeck.Card;
import org.example.backend.cardGames.cardDeck.Deck;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class BlackjackAIGame {

    private Deck deck;
    private Player player;
    private Dealer dealer;
    private boolean isPlayingSplitHand;

    public BlackjackAIGame() {
        this.deck = new Deck();
        this.deck.shuffle();
        this.player = new Player();
        this.dealer = new Dealer();
        this.isPlayingSplitHand = false;
    }

    public Player getPlayer() {
        return player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Deck getDeck() {
        return deck;
    }

    public void startGame() {
        dealInitialCards();
    }

    private void dealInitialCards() {
        player.getHand().addCard(deck.draw());
        player.getHand().addCard(deck.draw());
        dealer.getHand().addCard(deck.draw());
        dealer.getHand().addCard(deck.draw());
    }

    public void playerHit() {
        if (isPlayingSplitHand && player.isSplit()) {
            player.getSplitHand().addCard(deck.draw());
        } else {
            player.getHand().addCard(deck.draw());
        }
    }

    public void playerStand() {
        dealerPlay();
        determineWinner();
    }

    public void playerDoubleDown() {
        if (!player.isDoubleDown() && player.getHand().getHand().size() == 2) {
            player.setDoubleDown(true);
            player.getHand().addCard(deck.draw());
            if (isBust(player.getHand())) {
                System.out.println("Player busts! Dealer wins.");
            } else {
                playerStand();
            }
        } else {
            throw new IllegalStateException("Double down can only be performed before the first hit.");
        }
    }

    public void playerSplit() {
        try {
            Hand hand = player.getHand();
            Hand splitHand = new Hand();

            Field field = Hand.class.getDeclaredField("hand");
            field.setAccessible(true);
            ArrayList<Card> handList = (ArrayList<Card>) field.get(hand);

            splitHand.addCard(handList.remove(1));
            player.setSplitHand(splitHand);
            player.setSplit(true);
            player.getHand().addCard(deck.draw());
            player.getSplitHand().addCard(deck.draw());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void switchToSplitHand() {
        if (player.isSplit()) {
            this.isPlayingSplitHand = true;
        } else {
            throw new IllegalStateException("Cannot switch to split hand. No split hand exists.");
        }
    }

    public void switchToMainHand() {
        this.isPlayingSplitHand = false;
    }

    private void dealerPlay() {
        while (dealer.getHand().calculateScore() < 17) {
            dealer.getHand().addCard(deck.draw());
        }
    }

    private boolean isBust(Hand hand) {
        return hand.calculateScore() > 21;
    }

    private void determineWinner() {
        int playerScore = player.getHand().calculateScore();
        int dealerScore = dealer.getHand().calculateScore();

        if (isBust(player.getHand())) {
            System.out.println("Player busts! Dealer wins!");
        } else if (isBust(dealer.getHand())) {
            System.out.println("Dealer busts! Player wins!");
        } else if (playerScore > dealerScore) {
            System.out.println("Player wins!");
        } else if (dealerScore > playerScore) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }

        if (player.isSplit()) {
            int splitHandScore = player.getSplitHand().calculateScore();
            if (isBust(player.getSplitHand())) {
                System.out.println("Player's split hand busts! Dealer wins!");
            } else if (splitHandScore > dealerScore) {
                System.out.println("Player's split hand wins!");
            } else if (dealerScore > splitHandScore) {
                System.out.println("Dealer wins against player's split hand!");
            } else {
                System.out.println("Player's split hand ties with dealer!");
            }
        }
    }



    public GameState getGameState() {
        return new GameState(
                player.getHand(),
                player.getSplitHand(),
                dealer.getHand(),
                isBust(player.getHand()),
                player.isSplit() && isBust(player.getSplitHand()),
                isBust(dealer.getHand()),
                player.getHand().calculateScore(),
                player.isSplit() ? player.getSplitHand().calculateScore() : 0,
                dealer.getHand().calculateScore(),
                player.isSplit(),
                player.isDoubleDown(),
                isPlayingSplitHand
        );
    }

    public static class GameState {
        public Hand playerHand;
        public Hand playerSplitHand;
        public Hand dealerHand;
        public boolean playerBust;
        public boolean playerSplitBust;
        public boolean dealerBust;
        public int playerScore;
        public int playerSplitScore;
        public int dealerScore;
        public boolean playerSplit;
        public boolean playerDoubleDown;
        public boolean isPlayingSplitHand;

        public GameState(Hand playerHand, Hand playerSplitHand, Hand dealerHand, boolean playerBust, boolean playerSplitBust, boolean dealerBust, int playerScore, int playerSplitScore, int dealerScore, boolean playerSplit, boolean playerDoubleDown, boolean isPlayingSplitHand) {
            this.playerHand = playerHand;
            this.playerSplitHand = playerSplitHand;
            this.dealerHand = dealerHand;
            this.playerBust = playerBust;
            this.playerSplitBust = playerSplitBust;
            this.dealerBust = dealerBust;
            this.playerScore = playerScore;
            this.playerSplitScore = playerSplitScore;
            this.dealerScore = dealerScore;
            this.playerSplit = playerSplit;
            this.playerDoubleDown = playerDoubleDown;
            this.isPlayingSplitHand = isPlayingSplitHand;
        }
    }
}
