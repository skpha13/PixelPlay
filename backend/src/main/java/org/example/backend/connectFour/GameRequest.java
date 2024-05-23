package org.example.backend.connectFour;

public class GameRequest {
    private int[][] board;
    private int playerTurn;

    public int[][] getBoard() {
        return board;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }
}
