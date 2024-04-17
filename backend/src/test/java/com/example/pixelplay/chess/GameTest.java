package com.example.pixelplay.chess;

import com.example.pixelplay.chess.base.Game;
import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Piece;
import com.example.pixelplay.chess.base.Square;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private static final Game game = new Game();

    @Test
    void makeMove() {
        Move pawnMove = new Move(new Square(1, 3), new Square(3, 3));
        Piece knight = game.getPosition().getPiece(pawnMove.start);
        game.makeMove(pawnMove);

        assertEquals(knight, game.getPosition().getPiece(pawnMove.end));
        assertNull(game.getPosition().getPiece(pawnMove.start));
    }

//    @Test
//    void checkPositionAfterInitialize() {
//        Position position = game.getPosition();
//        Position initialPosition = PositionGenerator.initialPosition();
//        assert(position.equals(initialPosition));
//    }
}