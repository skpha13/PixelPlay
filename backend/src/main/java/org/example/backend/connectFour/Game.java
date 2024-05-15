package org.example.backend.connectFour;

public class Game implements Cloneable{
    private int[][] board;
    private Player playerTurn;
    private Player winner;
    private boolean isTie;

    static final int ROWS = 6;
    static final int COLUMNS = 7;

    public Game() {
        board = new int[ROWS][COLUMNS];
        playerTurn = Player.RED;
        winner = null;
        isTie = false;
        initialiseBoard();
    }

    public Game(int[][] board, int playerTurn) {
        this.board = board;
        this.playerTurn = Player.fromValue(playerTurn);
        winner = null;
        isTie = false;
    }

    private void initialiseBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = 0;
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public boolean getIsTie() { return isTie; }

    public Player getPlayerTurn() {
        return playerTurn;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setPlayerTurn(Player playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void switchPlayer() {
        playerTurn = (playerTurn == Player.RED) ? Player.YELLOW : Player.RED;
    }

    public void dropPiece(int colIndex) {
        if (colIndex < 0 || colIndex >= COLUMNS) return;
        if (winner != null) return;
        for (int rowIndex = Game.ROWS - 1; rowIndex >= 0; rowIndex--) {
            if (board[rowIndex][colIndex] == 0) {
                board[rowIndex][colIndex] = playerTurn.value;
                if (checkWin(rowIndex, colIndex)) {
                    setWinner(getPlayerTurn());
                } else if (checkTie()) {
                    isTie = true;
                }
                else {
                    switchPlayer();
                }
                return;
            }
        }
    }

    private boolean checkWin(int rowIndex, int colIndex) {
        final int[][] dir = {{1, 0}, {0, 1}, {1, 1}, {-1, 1}};
        for (int[] direction : dir) {
            int dx = direction[0], dy = direction[1];
            int count = 1;
            count += countDirection(rowIndex, colIndex, dx, dy);
            count += countDirection(rowIndex, colIndex, -dx, -dy);
            if (count >= 4) return true;
        }
        return false;
    }

    private int countDirection(int rowIndex, int colIndex, int dx, int dy) {
        final int player = board[rowIndex][colIndex];
        int count = 0;
        int x = rowIndex + dx;
        int y = colIndex + dy;
        while (x >= 0 && x < Game.ROWS && y >= 0 && y < COLUMNS && board[x][y] == player) {
            count += 1;
            x += dx;
            y += dy;
        }
        return count;
    }

    private boolean checkTie() {
        for (int i = 0; i < COLUMNS; i++) {
            if (board[0][i] == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Game clonedGame = (Game) super.clone();
        clonedGame.board = new int[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            System.arraycopy(this.board[i], 0, clonedGame.board[i], 0, COLUMNS);
        }
        clonedGame.playerTurn = this.playerTurn;
        clonedGame.winner = this.winner;
        return clonedGame;
    }
}
