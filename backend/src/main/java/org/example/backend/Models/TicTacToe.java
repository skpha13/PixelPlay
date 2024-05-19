package org.example.backend.Models;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.Locale;

public class TicTacToe implements Cloneable {
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

    @Override
    public Object clone() {
        TicTacToe cloned;
        try {
            cloned = (TicTacToe) super.clone();
        } catch (CloneNotSupportedException e) {
            cloned = new TicTacToe();
            cloned.winner = this.winner;
            cloned.isTie = this.isTie;
            cloned.board = this.board.clone();
        }

        cloned.board = this.board.clone();
        return cloned;
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

        // check each column
        for (int col = 0; col < 3; col++) {
            StringBuilder column = new StringBuilder();
            for (int row = 0; row < 3; row++) {
                column.append(board[row].charAt(col));
            }
            if (column.toString().equals("XXX")) {
                winner = "X";
                return;
            } else if (column.toString().equals("OOO")) {
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

    public int evaluate(int depth) {
        checkWin();
        if (winner.equals("-"))
            return 0;

        if (winner.equals("X"))
            return 10 + depth;

        if (winner.equals("O"))
            return -10 - depth;

        return 0;
    }

    public boolean areMovesPossible() {
        for (var row: board) {
            if (row.contains("-")) {
                return true;
            }
        }

        return false;
    }

    public boolean canPlace(int row, int col) {
        return board[row].charAt(col) == '-';
    }

    public void place(int row, int col, char player) {
        StringBuilder newRow = new StringBuilder(board[row]);
        newRow.setCharAt(col, player);
        board[row] = newRow.toString();
    }
}
