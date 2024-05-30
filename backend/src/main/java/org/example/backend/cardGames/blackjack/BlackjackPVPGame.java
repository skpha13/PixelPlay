package org.example.backend.cardGames.blackjack;

import org.example.backend.cardGames.cardDeck.Deck;

public class BlackjackPVPGame {

    private final Deck deck;
    private final Player player1;
    private final Player player2;
    private boolean player1Stand;
    private boolean player2Stand;

    public BlackjackPVPGame() {
        this.deck = new Deck();
        this.deck.shuffle();
        this.player1 = new Player();
        this.player2 = new Player();
        this.player1Stand = false;
        this.player2Stand = false;
        startGame();
    }

    private void startGame() {
        // Deal initial cards to both players
        player1.getHand().addCard(deck.draw());
        player1.getHand().addCard(deck.draw());
        player2.getHand().addCard(deck.draw());
        player2.getHand().addCard(deck.draw());
    }

    public void player1Hit() {
        if (!player1Stand) {
            player1.getHand().addCard(deck.draw());
        }
    }

    public void player2Hit() {
        if (!player2Stand) {
            player2.getHand().addCard(deck.draw());
        }
    }

    public void player1Stand() {
        player1Stand = true;
        if (player2Stand) {
            determineWinner();
        }
    }

    public void player2Stand() {
        player2Stand = true;
        if (player1Stand) {
            determineWinner();
        }
    }

    public boolean isPlayer1Bust() {
        return player1.getHand().calculateScore() > 21;
    }

    public boolean isPlayer2Bust() {
        return player2.getHand().calculateScore() > 21;
    }

    private void determineWinner() {
        int player1Score = isPlayer1Bust() ? 0 : player1.getHand().calculateScore();
        int player2Score = isPlayer2Bust() ? 0 : player2.getHand().calculateScore();

        if (player1Score > player2Score) {
            System.out.println("Player 1 wins!");
        } else if (player2Score > player1Score) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public boolean isPlayer1Stand() {
        return player1Stand;
    }

    public boolean isPlayer2Stand() {
        return player2Stand;
    }

    public static class GameState {
        public Hand player1Hand;
        public Hand player2Hand;
        public boolean player1Bust;
        public boolean player2Bust;
        public int player1Score;
        public int player2Score;
        public boolean player1Stand;
        public boolean player2Stand;

        public GameState(Hand player1Hand, Hand player2Hand, boolean player1Bust, boolean player2Bust, int player1Score, int player2Score, boolean player1Stand, boolean player2Stand) {
            this.player1Hand = player1Hand;
            this.player2Hand = player2Hand;
            this.player1Bust = player1Bust;
            this.player2Bust = player2Bust;
            this.player1Score = player1Score;
            this.player2Score = player2Score;
            this.player1Stand = player1Stand;
            this.player2Stand = player2Stand;
        }
    }

    public GameState getGameState() {
        return new GameState(
                player1.getHand(),
                player2.getHand(),
                isPlayer1Bust(),
                isPlayer2Bust(),
                player1.getHand().calculateScore(),
                player2.getHand().calculateScore(),
                player1Stand,
                player2Stand
        );
    }
}
