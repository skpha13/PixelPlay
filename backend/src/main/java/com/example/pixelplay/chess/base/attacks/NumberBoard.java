package com.example.pixelplay.chess.base.attacks;

import com.example.pixelplay.chess.base.Square;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NumberBoard {
    private short[] board = new short[64];

    public static NumberBoard fromNumberArray(short[] array) {
        NumberBoard board = new NumberBoard();
        for(int i = 0; i < 64; i ++) {
            board.board[i] = array[i];
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
        return Arrays.hashCode(board);
    }

    @Override
    public String toString() {
        return "NumberBoard{" +
                "board=" + Arrays.toString(board) +
                '}';
    }

    public void addAttacks(List<Square> attacks) {
        for(Square square : attacks) {
            board[square.getIndex()] ++;
        }
    }

    public int getValue(Square square) {
        return board[square.getIndex()];
    }
}
