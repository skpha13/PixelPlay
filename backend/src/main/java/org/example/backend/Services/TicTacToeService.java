package org.example.backend.Services;

import org.example.backend.Models.TicTacToe;

public class TicTacToeService {
    private TicTacToe ticTacToe;
    private AIService AI;

    public TicTacToeService(TicTacToe ticTacToe, AIService AI) {
        this.ticTacToe = ticTacToe;
        this.AI = AI;
    }

    public TicTacToe playMove() {
        return new TicTacToe();
    }
}
