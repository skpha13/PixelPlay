package com.example.pixelplay.chess.base;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NumberBoard {
    private short[][] board = new short[8][8];

    public static NumberBoard fromNumberMatrix(short[][] matrix) {
        NumberBoard board = new NumberBoard();
        for(int i = 0; i < 8; i ++) {
            for(int j = 0; j < 8; j ++) {
                board.board[i][j] = matrix[i][j];
            }
        }
        return board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberBoard that = (NumberBoard) o;
        return Objects.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    @Override
    public String toString() {
        return "NumberBoard{" +
                "board=" + Arrays.deepToString(board) +
                '}';
    }

    public void addAttacks(List<Square> attacks) {
        for(Square square : attacks) {
            board[square.getRank()][square.getFile()] ++;
        }
    }
}
