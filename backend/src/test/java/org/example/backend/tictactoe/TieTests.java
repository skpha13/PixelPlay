package org.example.backend.tictactoe;

import org.example.backend.Models.TicTacToe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TieTests {
    private static final TicTacToe ticTacToe = new TicTacToe();

    @Test
    void allInvalidMoves() {
        ticTacToe.setBoard(new String[]{
                "---",
                "---",
                "---"
        });
        ticTacToe.checkTie();

        assertEquals(ticTacToe.isTie, false);
    }

    @Test
    void validTie() {
        ticTacToe.setBoard(new String[]{
                "XOX",
                "XOO",
                "OXO"
        });
        ticTacToe.checkTie();

        assertEquals(ticTacToe.isTie, true);
    }

    @Test
    void winnerNoTie() {
        ticTacToe.setBoard(new String[]{
                "XXX",
                "XXO",
                "OOX"
        });
        ticTacToe.checkTie();

        assertEquals(ticTacToe.isTie, false);
    }

    @Test
    void tieWithWinnerField() {
        ticTacToe.setBoard(new String[]{
                "XXO",
                "XXO",
                "OO-"
        });
        ticTacToe.checkWin();

        assertEquals(ticTacToe.winner, "-");
    }
}
