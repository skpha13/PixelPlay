package org.example.backend.chess.repositories;

import org.example.backend.chess.logic.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameRepository {
    private final Map<UUID, Game> games = new HashMap<>();

    private  GameRepository() {}

    public void addGame(UUID uuid, Game game) {
        games.put(uuid, game);
    }

    public Game getGame(UUID uuid) {
        return games.get(uuid);
    }

    private static GameRepository instance = null;

    public static GameRepository getInstance() {
        if(instance == null) {
            instance = new GameRepository();
        }
        return instance;
    }
}
