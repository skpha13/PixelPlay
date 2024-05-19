package org.example.backend.Models;

import org.springframework.lang.NonNull;

import java.util.Arrays;

public class TicTacToe {
    @NonNull
    public String[] board = new String[] {
            "---",
            "---",
            "---"
    };

    // winner can be either 'X' or 'O'
    public String winner = "-";
    public Boolean isTie = false;

    @NonNull
    public String[] getBoard() {
        return board;
    }

    public void setBoard(@NonNull String[] newBoard) {
        this.board = Arrays.copyOf(newBoard, newBoard.length);
    }

    public void checkWin() {
        // check each row
        for (var row: board) {
            if (row.equals("XXX")) {
                winner = "X";
                return;
            } else if (row.equals("OOO")) {
                winner = "O";
                return;
            }
        }

        // check diagonals
        if ((board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)) ||
            (board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0))) {

            if (board[1].charAt(1) == 'X') {
                winner = "X";
                return;
            } else if (board[1].charAt(1) == 'O') {
                winner = "O";
                return;
            }
        }

        winner = "-";
    }

    public void checkTie() {
        checkWin();
        if (!winner.equals("-")) {
            isTie = false;
            return;
        }

        for (var row: board) {
            if (row.contains("-")) {
                isTie = false;
                return;
            }
        }

        isTie = true;
    }
}
