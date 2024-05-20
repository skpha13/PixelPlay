package org.example.backend.chess.services;

import org.example.backend.chess.Mapper;
import org.example.backend.chess.dtos.GameDto;
import org.example.backend.chess.logic.Game;
import org.example.backend.chess.repositories.GameRepository;

import java.util.UUID;

public class GameService {
    private GameRepository gameRepository;

    private GameService() {
        gameRepository = GameRepository.getInstance();
    }

    public String newGame() {
        UUID uuid = UUID.randomUUID();
        Game game = new Game();
        gameRepository.addGame(uuid, game);

        return uuid.toString();
    }

    private static GameService instance = null;
    public static GameService getInstance() {
        if (instance == null) {
            instance = new GameService();
        }
        return instance;
    }

    public GameDto getBoard(String id) {
        UUID uuid = UUID.fromString(id);
        Game game = gameRepository.getGame(uuid);
        return Mapper.toGameDto(game);
    }
}
