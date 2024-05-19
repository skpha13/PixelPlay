package org.example.backend.Services;

import org.example.backend.Models.TicTacToe;

public class TicTacToeService {
    private TicTacToe ticTacToe;

    public TicTacToeService(TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
        AIService.setTicTacToe(ticTacToe);
    }

    public TicTacToe playMove() {
        int[] move = AIService.getMove();
        int row = move[0];
        int col = move[1];

        ticTacToe.place(row, col, 'O');

        ticTacToe.checkWin();
        ticTacToe.checkTie();
        return ticTacToe;
    }
}
