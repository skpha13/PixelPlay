package org.example.backend.tictactoe;

import org.example.backend.Models.TicTacToe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WinTests {
    private static final TicTacToe ticTacToe = new TicTacToe();

    @Test
    void rowsX() {
        ticTacToe.setBoard(new String[]{
                "XXX",
                "-O-",
                "--O"
        });
        ticTacToe.checkWin();

        assertEquals(ticTacToe.winner, "X");
    }

    @Test
    void rowsO() {
        ticTacToe.setBoard(new String[]{
                "X-X",
                "-O-",
                "OOO"
        });
        ticTacToe.checkWin();

        assertEquals(ticTacToe.winner, "O");
    }

    @Test
    void rowsAndColumnX() {
        ticTacToe.setBoard(new String[]{
                "XXX",
                "-OX",
                "OOX"
        });
        ticTacToe.checkWin();

        assertEquals(ticTacToe.winner, "X");
    }

    @Test
    void mainDiagonalX() {
        ticTacToe.setBoard(new String[]{
                "XOO",
                "-XX",
                "OOX"
        });
        ticTacToe.checkWin();

        assertEquals(ticTacToe.winner, "X");
    }

    @Test
    void secondaryDiagonalX() {
        ticTacToe.setBoard(new String[]{
                "XOX",
                "-XX"
                ,"XOO"
        });
        ticTacToe.checkWin();

        assertEquals(ticTacToe.winner, "X");
    }

    @Test
    void noWinnerEndOfGame() {
        ticTacToe.setBoard(new String[]{
                "XOX",
                "XOX",
                "OXO"
        });
        ticTacToe.checkWin();

        assertEquals(ticTacToe.winner, "-");
    }

    @Test
    void noWinnerPremature() {
        ticTacToe.setBoard(new String[]{
                "X--",
                "XOX",
                "OXO"
        });
        ticTacToe.checkWin();

        assertEquals(ticTacToe.winner, "-");
    }

    @Test
    void columnX() {
        ticTacToe.setBoard(new String[]{
                "X--",
                "XOX",
                "XXO"
        });
        ticTacToe.checkWin();

        assertEquals(ticTacToe.winner, "X");
    }
}
