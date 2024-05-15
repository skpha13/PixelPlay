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

    public String winner = null;
    public Boolean isTie = false;

    @NonNull
    public String[] getBoard() {
        return board;
    }

    public void setBoard(@NonNull String[] newBoard) {
        this.board = Arrays.copyOf(newBoard, newBoard.length);
    }
}
