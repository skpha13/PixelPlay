package org.example.backend.cardGames.blackjack.controllers;

import org.example.backend.cardGames.blackjack.BlackjackPVPGame;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/blackjackPVP")
public class BlackjackPVPController {

    private BlackjackPVPGame game;

    @PostMapping("/start")
    public BlackjackPVPGame.GameState startGame() {
        game = new BlackjackPVPGame();
        return game.getGameState();
    }

    @GetMapping("/state")
    public BlackjackPVPGame.GameState getGameState() {
        return game.getGameState();
    }

    @PostMapping("/player1/hit")
    public BlackjackPVPGame.GameState player1Hit() {
        game.player1Hit();
        return game.getGameState();
    }

    @PostMapping("/player2/hit")
    public BlackjackPVPGame.GameState player2Hit() {
        game.player2Hit();
        return game.getGameState();
    }

    @PostMapping("/player1/stand")
    public BlackjackPVPGame.GameState player1Stand() {
        game.player1Stand();
        return game.getGameState();
    }

    @PostMapping("/player2/stand")
    public BlackjackPVPGame.GameState player2Stand() {
        game.player2Stand();
        return game.getGameState();
    }
}
