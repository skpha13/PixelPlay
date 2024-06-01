# Testing
![image](https://github.com/skpha13/PixelPlay/assets/71341569/13ac81e4-d9a0-422b-ba1e-90c20c341223)


During the development of the Chess module, we have constantly written tests to make sure the algorithm's behavior is as expected.

### Examples:

##### 1. PawnMoveGeneratorTest.getLegalMoves_enPassant()
This is a test for the class responsible for generating pawn moves in a specific position.
The role of the test is to make sure that, in a position where a special move called [en passant](https://en.wikipedia.org/wiki/En_passant) is returned among the other legal moves.

```
private final Position position = PositionGenerator.customPosition(
            new String[] {
                    ".....K..",
                    "PPPPP...",
                    ".R......",
                    "..p.....",
                    ".pP.....",
                    "...p....",
                    "ppp....P",
                    "k.....q.",
            }
    );

    @Test
    public void getLegalMoves_enPassant() {
        Square pawn = new Square(4, 2);
        position.setEnPassantSquare(new Square(5, 1));
        MoveGenerator moveGenerator = new PawnMoveGenerator(position, pawn);

        List<Move> moves = moveGenerator.getLegalMoves();
        List<Move> expected = new ArrayList<>(List.of(
                new Move(pawn, new Square(5, 1)),
                new Move(pawn, new Square(5, 2)),
                new Move(pawn, new Square(5, 3))
        ));

        Collections.sort(moves);
        Collections.sort(expected);

        assertEquals(expected, moves);
    }
```

##### 2. PositionTest.verifyCheck()
This is a test for the class responsible for holding the state of the game (the position on board and various flags).
The test verifies that in a specific position, the current turn's king is correctly identified as being checked.

```
    private static final String[] positionWhiteCheckKnight = new String[] {
            "........",
            ".k...q..",
            ".p......",
            "........",
            "....hP..",
            "....PP..",
            ".Q...K..",
            "........"
    };
    @Test
    public void verifyCheck() {
        Position position = PositionGenerator.customPosition(positionWhiteCheckKnight);
        AttackCalculator attackCalculator = new AttackCalculator(position);
        boolean whiteChecked = attackCalculator.kingIsInCheck(Color.WHITE);
        assertTrue(whiteChecked);

        position = PositionGenerator.customPosition(positionWhiteCheckKnight);
        attackCalculator = new AttackCalculator(position);
        whiteChecked = attackCalculator.kingIsInCheck(Color.WHITE);
        assertTrue(whiteChecked);

    }
```

##### 3. GameTest.makeMove_twoWhiteMoves()
This test makes sure that when we try to move white pieces two times in a row, the Game class throws an exception:

```
    @Test
    void makeMove_twoWhiteMoves() {
        Game game = new Game();
        Move pawnMove = new Move(new Square(1, 3), new Square(2, 3));
        Move knightMove = new Move(new Square(0, 1), new Square(2, 2));

        game.makeMove(pawnMove);
        assertThrows(RuntimeException.class, () -> game.makeMove(knightMove));
    }
```

##### 4. GameTest.makeMove_shortCastle()
This test verifies that after short castling the position is correctly updated (the rook is also moved).
```
    private final static String[] stringPosition = new String[]{
            "........",
            "PPPPPP.p",
            "........",
            "p.......",
            "........",
            "........",
            ".ppppppp",
            "r...k..r"
    };
    @Test
    void makeMove_shortCastle() {
        Position position = PositionGenerator.customPosition(stringPosition, Color.BLACK);
        Game game = new Game(position);

        Move shortCastle = new Move(new Square(7, 4), new Square(7, 6));
        game.makeMove(shortCastle);

        assertEquals(PieceType.King, position.getPiece(new Square(7, 6)).type());
        assertEquals(PieceType.Rook, position.getPiece(new Square(7, 5)).type());
    }
```

##### 5. GameTest.makeMove_shortCastle_failing()
This test fails because the game doesn't allow short castling in the initial position, and the exception thrown is not handled properly.
![image](https://github.com/skpha13/PixelPlay/assets/71341569/45b1d7f5-4188-4f03-a60e-b3ca2a00cab6)

```
    @Test
    void makeMove_shortCastle_failing() {
        Position position = PositionGenerator.initialPosition();
        Game game = new Game(position);

        Move shortCastle = new Move(new Square(7, 4), new Square(7, 6));
        game.makeMove(shortCastle);

        assertEquals(PieceType.King, position.getPiece(new Square(7, 6)).type());
        assertEquals(PieceType.Rook, position.getPiece(new Square(7, 5)).type());
    }
```
