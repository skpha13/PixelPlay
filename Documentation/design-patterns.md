# Design Patterns

### 1. [Strategy Pattern](https://refactoring.guru/design-patterns/strategy)

In the chess module, there are classes responsible for various tasks related to piece moving:
- PieceMoveGenerator (calculated the possible moves in a specific position by a specific piece)
- GeneralHandler (handles moving and updates the position accordingly)
- GeneralMoveValidator (checks if a move is legal in a specific position)

Because in a chess game, each piece has it's own set of rules, these classes must have had implemented the logic for each piece type, which would have made 
the class very large, hard to understand and debug, and responsible for multiple sets of rules.

The solution was the Strategy Pattern.

Each piece type or special move has it's logic implemented in a different class
- move generating: KingMoveGenerator, PawnMoveGenerator, LongRangeMoveGenerator (for Queen, Rook and Bishop, as their moving rules are very similar)
- move handling: PromotionHandler, CastleHandler, SimpleMoveHandler
- move validation: EnPassantValidator, CastleValidator, SimpleMoveValidator

Now, our initial classes, only have to recognise what type of piece is moved or what type of move is being made and pass the responsability of handling the logic
to the right class.
![image](https://github.com/skpha13/PixelPlay/assets/71341569/354b5c32-1350-418b-b716-2cea74dee419)


### 2. [Abstract Factory](https://refactoring.guru/design-patterns/abstract-factory)

In the chess module, the PieceMechanics class is responsible for making different calculations related to each piece, like:
- where it can move
- what squares does it attack
- what pieces can it capture
  
Because the pieces have different rules, ecah has it's own class for handling this logic.

PieceMechanics class had become abstract, and PieceMechanicsFactory is the class that handles creating and returning the right object.

![image](https://github.com/skpha13/PixelPlay/assets/71341569/394e5e8b-8c91-4068-b28f-28884d42a830)


### 3. [Singleton](https://refactoring.guru/design-patterns/singleton)
In the chess module, GameRepository and GameService follow the Singleton pattern, as only one instance can exists during the runtime.

```
public class GameService {
    private final GameRepository gameRepository;

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

        return generator.getLegalMoves().stream().map(Move::end).toList();
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
```

  
