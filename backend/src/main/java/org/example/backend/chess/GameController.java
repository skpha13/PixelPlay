package org.example.backend.chess;

import org.example.backend.chess.dtos.GameDto;
import org.example.backend.chess.services.GameService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class GameController {
    private final GameService gameService = GameService.getInstance();

    @GetMapping("/chess")
    public String newGame() {
        return gameService.newGame();
    }

    @GetMapping("/chess/getGame")
    public GameDto getGame(@RequestParam(value = "id") String id) {
        return gameService.getBoard(id);
    }
}
