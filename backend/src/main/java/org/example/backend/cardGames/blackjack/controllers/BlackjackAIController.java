package org.example.backend.cardGames.blackjack.controllers;

import org.example.backend.cardGames.blackjack.BlackjackAIGame;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/blackjackAI")
public class BlackjackAIController {

    private BlackjackAIGame game;

    @PostMapping("/start")
    public BlackjackAIGame.GameState startGame() {
        game = new BlackjackAIGame();
        game.startGame();
        return game.getGameState();
    }

    @GetMapping("/state")
    public BlackjackAIGame.GameState getGameState() {
        return game.getGameState();
    }

    @PostMapping("/hit")
    public BlackjackAIGame.GameState playerHit() {
        game.playerHit();
        return game.getGameState();
    }

    @PostMapping("/stand")
    public BlackjackAIGame.GameState playerStand() {
        game.playerStand();
        return game.getGameState();
    }

    @PostMapping("/doubleDown")
    public BlackjackAIGame.GameState playerDoubleDown() {
        try {
            game.playerDoubleDown();
        } catch (IllegalStateException e) {
            // Handle exception
        }
        return game.getGameState();
    }

    @PostMapping("/split")
    public BlackjackAIGame.GameState playerSplit() {
        try {
            game.playerSplit();
        } catch (IllegalStateException e) {
            // Handle exception
        }
        return game.getGameState();
    }

    @PostMapping("/switchToMainHand")
    public BlackjackAIGame.GameState switchToMainHand() {
        game.switchToMainHand();
        return game.getGameState();
    }

    @PostMapping("/switchToSplitHand")
    public BlackjackAIGame.GameState switchToSplitHand() {
        game.switchToSplitHand();
        return game.getGameState();
    }
}
