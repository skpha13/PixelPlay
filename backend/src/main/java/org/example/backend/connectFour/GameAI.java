package org.example.backend.connectFour;

import java.util.Arrays;

public class GameAI {
//    returns a 2-element array: [score, bestColumn]
    public static int[] minimax(int[][] board, int depth, int playerTurn, int alpha, int beta, boolean maximisingPlayer) {
        Game game = new Game(board, playerTurn);
        if (depth == 0 || isTerminalNode(game)) {
            if (isTerminalNode(game)) {
                if (game.getWinner().value == 2) {
                    return new int[]{10000000, -1};
                } else if (game.getWinner().value == 1) {
                    return new int[]{-10000000, -1};
                }
//                Board is full
                else {
                    return new int[]{0, -1};
                }
            }
            return new int[]{evaluateBoard(game), -1};
        }

        if (maximisingPlayer) {
            int bestScore = Integer.MIN_VALUE;
            int column = 0;
            for (int col = 0; col < Game.COLUMNS; col++) {
                Game clonedGame;
                try {
                    clonedGame = (Game) game.clone();
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
                clonedGame.dropPiece(col);
//                if that column is already full
                if (Arrays.deepEquals(clonedGame.getBoard(), game.getBoard())) {
                    continue;
                }
                int newScore = minimax(clonedGame.getBoard(), depth - 1, clonedGame.getPlayerTurn().value, alpha, beta, false)[0];
                if (newScore > bestScore) {
                    bestScore = newScore;
                    column = col;
                }
                alpha = Math.max(alpha, bestScore);
                if (alpha >= beta) {
                    break;
                }
            }
            return new int[]{bestScore, column};
        } else {
            int bestScore = Integer.MAX_VALUE;
            int column = 0;
            for (int col = 0; col < Game.COLUMNS; col++) {
                Game clonedGame;
                try {
                    clonedGame = (Game) game.clone();
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
                clonedGame.dropPiece(col);
                // if that column is already full
                if (Arrays.deepEquals(clonedGame.getBoard(), game.getBoard())) {
                    continue;
                }
                int newScore = minimax(clonedGame.getBoard(), depth - 1, clonedGame.getPlayerTurn().value, alpha, beta, true)[0];
                if (newScore < bestScore) {
                    bestScore = newScore;
                    column = col;
                }
                beta = Math.min(beta, bestScore);
                if (alpha >= beta) {
                    break;
                }
            }
            return new int[]{bestScore, column};
        }
    }


    private static boolean isTerminalNode(Game game) {
        return game.getWinner() != null || game.getIsTie();
    }

    private static int evaluateWindow(int[] window) {
        int score = 0;
        int player = 2;
        int opponent = 1;

        int cntOpponent = 0, cntPlayer = 0;

        for (int j : window) {
            if (j == opponent) {
                cntOpponent++;
            } else if (j == player) {
                cntPlayer++;
            }
        }

        if (cntPlayer == 4) {
            score += 1000;
        } else if (cntPlayer == 3 && cntOpponent == 0) {
            score += 5;
        } else if (cntPlayer == 2 && cntOpponent == 0) {
            score += 2;
        }

        if (cntOpponent == 4) {
            score -= 1000;
        } else if (cntOpponent == 3 && cntPlayer == 0) {
            score -= 20;
        } else if (cntOpponent == 2 && cntPlayer == 0) {
            score -= 2;
        }

        return score;
    }

    private static int evaluateBoard(Game game) {
        int score = 0;

//        Score center column
        int player = 2;
        int opponent = 1;
        int cntOpponent = 0, cntPlayer = 0;
        int centerColumn = Game.COLUMNS / 2;
        int[][] board = game.getBoard();
        for (int row = 0; row < Game.ROWS; row++) {
            if (board[row][centerColumn] == player) {
                cntPlayer++;
            } else if (board[row][centerColumn] == opponent) {
                cntOpponent++;
            }
        }
        score += cntPlayer * 3;
        score -= cntOpponent * 3;

//        Score Horizontal
        for (int row = 0; row < Game.ROWS; row++) {
            for (int col = 0; col < Game.COLUMNS - 3; col++) {
                int[] arr = new int[4];
                System.arraycopy(board[row], col, arr, 0, 4);
                score += evaluateWindow(arr);
            }
        }

//        Score Vertical
        for (int col = 0; col < Game.COLUMNS; col++) {
            for (int row = 0; row < Game.ROWS - 3; row++) {
                int[] arr = new int[4];
                for (int i = 0; i < 4; i++) {
                    arr[i] = board[row + i][col];
                }
                score += evaluateWindow(arr);
            }
        }

//        Score Diagonal \ (i+1, j+1)
        for (int row = 0; row < Game.ROWS - 3; row++) {
            for (int col = 0; col < Game.COLUMNS - 3; col++) {
                int[] arr = new int[4];
                for (int i = 0; i < 4; i++) {
                    arr[i] = board[row + i][col + i];
                }
                score += evaluateWindow(arr);
            }
        }

//        Score Diagonal / (i-1, j+1)
        for (int row = 0; row < Game.ROWS - 3; row++) {
            for (int col = Game.COLUMNS - 3 - 1; col < Game.COLUMNS; col++) {
                int[] arr = new int[4];
                for (int i = 0; i < 4; i++) {
                    arr[i] = board[row + i][col - i];
                }
                score += evaluateWindow(arr);
            }
        }

        return score;
    }
}
