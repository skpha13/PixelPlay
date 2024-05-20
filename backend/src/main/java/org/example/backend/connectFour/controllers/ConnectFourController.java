package org.example.backend.connectFour.controllers;

import org.example.backend.connectFour.Game;
import org.example.backend.connectFour.GameAI;
import org.example.backend.connectFour.GameRequest;
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
        int bestMove = GameAI.minimax(gameRequest.getBoard(), 2, gameRequest.getPlayerTurn(), Integer.MIN_VALUE, Integer.MAX_VALUE, true)[1];
        game.dropPiece(bestMove);
        return game.getBoard();
    }
}

