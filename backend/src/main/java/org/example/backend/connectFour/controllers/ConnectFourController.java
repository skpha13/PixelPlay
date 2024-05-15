package org.example.backend.connectFour.controllers;

import org.example.backend.connectFour.Game;
import org.example.backend.connectFour.GameAI;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/connectFour")
public class ConnectFourController {

    @PostMapping("/generateMove")
    public int[][] generateMove(@RequestBody GameRequest gameRequest) {
        int[][] currentBoard = gameRequest.getBoard();
        int playerTurn = gameRequest.getPlayerTurn();
        Game game = new Game(currentBoard, playerTurn);
        int bestMove = GameAI.minimax(gameRequest.board, 5, gameRequest.playerTurn, Integer.MIN_VALUE, Integer.MAX_VALUE, false)[1];
        game.dropPiece(bestMove);
        return game.getBoard();
    }

    // Define a class to represent the request body
    static class GameRequest {
        private int[][] board;
        private int playerTurn;

        public int[][] getBoard() {
            return board;
        }

        public void setBoard(int[][] board) {
            this.board = board;
        }

        public int getPlayerTurn() {
            return playerTurn;
        }

        public void setPlayerTurn(int playerTurn) {
            this.playerTurn = playerTurn;
        }
    }
}

