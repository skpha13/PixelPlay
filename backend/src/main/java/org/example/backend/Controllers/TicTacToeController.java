package org.example.backend.Controllers;

import org.example.backend.Models.TicTacToe;
import org.example.backend.Services.AIService;
import org.example.backend.Services.TicTacToeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")

public class TicTacToeController {

    @PostMapping("/tic-tac-toe/play-move")
    @ResponseBody
    public TicTacToe playMove(@Validated @RequestBody TicTacToe gameState) {
        TicTacToe newGameState = new TicTacToe();
        newGameState.setBoard(gameState.board);

        AIService AI = new AIService();
        TicTacToeService ticTacToeService = new TicTacToeService(newGameState, AI);

        return ticTacToeService.playMove();
    }
}
