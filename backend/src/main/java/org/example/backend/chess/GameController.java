package org.example.backend.chess;

import org.example.backend.chess.dtos.GameDto;
import org.example.backend.chess.dtos.MoveRequest;
import org.example.backend.chess.dtos.SquareDto;
import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.base.Square;
import org.example.backend.chess.services.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping("/chess/getSquaresToMove")
    public List<Square> getSquaresToMove(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "rank") int rank,
            @RequestParam(value = "file") int file) {
        return gameService.getSquaresToMove(id, rank ,file);
    }

    @PutMapping("/chess/makeMove")
    public boolean makeMove(
        @RequestBody MoveRequest moveRequest
    ) {
        try {
            Move move = new Move(Mapper.toSquare(moveRequest.startSquare()), Mapper.toSquare(moveRequest.endSquare()));
            gameService.makeMove(moveRequest.id(), move);
            return true;
        }
        catch (Exception e) {
            return false;
        }

    }
}
