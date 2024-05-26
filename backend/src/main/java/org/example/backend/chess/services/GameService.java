package org.example.backend.chess.services;

import org.example.backend.chess.Mapper;
import org.example.backend.chess.dtos.GameDto;
import org.example.backend.chess.dtos.SquareDto;
import org.example.backend.chess.engine.Engine;
import org.example.backend.chess.logic.Game;
import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.base.Square;
import org.example.backend.chess.logic.moving.MoveGenerator;
import org.example.backend.chess.logic.moving.generator.PieceMoveGenerator;
import org.example.backend.chess.repositories.GameRepository;

import java.util.List;
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

    public List<Square> getSquaresToMove(String id, int rank, int file) {
            Game game = gameRepository.getGame(UUID.fromString(id));
        MoveGenerator generator = new PieceMoveGenerator(game.getPosition(), new Square(rank, file));

        List<Square> squares = generator.getLegalMoves().stream().map(Move::end).toList();
        return squares;
    }

    public void makeMove(String id, Move move) {
        try {
            Game game = gameRepository.getGame(UUID.fromString(id));
            game.makeMove(move);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void makeEngineMove(String id) {
        try {
            Game game = gameRepository.getGame(UUID.fromString(id));
            Move engineMove = Engine.getMove(game.getPosition());
            System.out.println(engineMove.toString());
            game.makeMove(engineMove);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
