package org.example.backend.Services;

import org.example.backend.Models.TicTacToe;

public class TicTacToeService {
    private final TicTacToe ticTacToe;

    public TicTacToeService(TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
        AIService.setTicTacToe(ticTacToe);
    }

    public TicTacToe playMove() {
        int[] move = AIService.getMove();
        int row = move[0];
        int col = move[1];

        try {
            ticTacToe.place(row, col, 'O');
        } catch (ArrayIndexOutOfBoundsException ignored) {}

        ticTacToe.checkWin();
        ticTacToe.checkTie();
        return ticTacToe;
    }
}
