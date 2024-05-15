package org.example.backend.Controllers;

import org.example.backend.Models.TicTacToe;
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

        // TODO: implement service logic to play a move

        return newGameState;
    }
}
