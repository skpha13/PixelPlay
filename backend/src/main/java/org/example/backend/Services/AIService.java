package org.example.backend.Services;

import org.example.backend.Models.TicTacToe;

public class AIService {
    private static TicTacToe ticTacToe;
    private static final int  MAX_DEPTH = 8;

    public static void setTicTacToe(TicTacToe ticTacToe) {
        AIService.ticTacToe = (TicTacToe) ticTacToe.clone();
    }

    public static int evaluateBoard(int depth) {
        return ticTacToe.evaluate(depth);
    }

    public static boolean isTerminalNode() {
        String winner = ticTacToe.winner;
        boolean isTie = ticTacToe.isTie;

        if (winner.equals("X") || winner.equals("O"))
            return true;

        return isTie;
    }

    public static int miniMax(TicTacToe board, boolean isMax, int depth) {
        int boardValue = evaluateBoard(depth);

        if (depth == 0 || !board.areMovesPossible() || isTerminalNode())
            return boardValue;

        if (isMax) {
            int maxValue = Integer.MIN_VALUE;

            for (int row=0; row<3; row++)
                for (int col=0; col<3; col++)
                    if (board.canPlace(row, col)) {
                        // place a new move from player O
                        board.place(row, col, 'O');
                        maxValue = Math.max(maxValue, miniMax(board, false, depth-1));
                        // free the move played before
                        board.place(row, col, '-');
                    }

            return maxValue;

        } else {
            int minValue = Integer.MAX_VALUE;

            for (int row=0; row<3; row++)
                for (int col=0; col<3; col++)
                    if (board.canPlace(row, col)) {
                        // place a new move from player X
                        board.place(row, col, 'X');
                        minValue = Math.min(minValue, miniMax(board, true, depth-1));
                        // free the move played before
                        board.place(row, col, '-');
                    }

            return minValue;
        }
    }

    public static int[] getMove() {
        int[] move = new int[]{-1, -1};
        int bestValue = Integer.MIN_VALUE;

        for (int row=0; row<3; row++)
            for (int col=0; col<3; col++)
                if (ticTacToe.canPlace(row, col)) {
                    // place a new move from player 0
                    ticTacToe.place(row, col, 'O');
                    int moveValue =  miniMax(ticTacToe, false, MAX_DEPTH);
                    // free the move played before
                    ticTacToe.place(row, col, '-');

                    if (moveValue > bestValue) {
                        move[0] = row;
                        move[1] = col;
                        bestValue = moveValue;
                    }
                }

        return move;
    }
}
