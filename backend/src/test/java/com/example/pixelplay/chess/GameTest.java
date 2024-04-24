package com.example.pixelplay.chess;

import com.example.pixelplay.chess.base.*;
import com.example.pixelplay.chess.exceptions.IncorrectTurnException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private static final Game game = new Game();

    @Test
    void makeMove() {
        Move pawnMove = new Move(new Square(1, 3), new Square(3, 3));
        Piece knight = game.getPosition().getPiece(pawnMove.start);
        game.makeMove(pawnMove);

        assertEquals(knight, game.getPosition().getPiece(pawnMove.end));
        assertEquals(PieceType.None, game.getPosition().getPiece(pawnMove.start).type);
    }

    @Test
    void makeFirstMove_black() {
        Game game = new Game();
        Move pawnMove = new Move(new Square(6, 3), new Square(5, 3));

        assertThrows(IncorrectTurnException.class, () -> game.makeMove(pawnMove));
    }

    @Test
    void makeMove_twoWhiteMoves() {
        Game game = new Game();
        Move pawnMove = new Move(new Square(1, 3), new Square(2, 3));
        Move knightMove = new Move(new Square(0, 1), new Square(2, 2));

        game.makeMove(pawnMove);
        assertThrows(IncorrectTurnException.class, () -> game.makeMove(knightMove));
    }

//    @Test
//    void checkPositionAfterInitialize() {
//        Position position = game.getPosition();
//        Position initialPosition = PositionGenerator.initialPosition();
//        assert(position.equals(initialPosition));
//    }
}