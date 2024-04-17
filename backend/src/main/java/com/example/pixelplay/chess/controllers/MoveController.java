package com.example.pixelplay.chess.controllers;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Piece;
import com.example.pixelplay.chess.base.Position;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.mechanics.PieceMechanics;
import com.example.pixelplay.chess.mechanics.PieceMechanicsFactory;

import java.util.List;

public class MoveController {
    private final Position position;
    private final AttackController attackController;
    private final CastlingController castlingController;

    public MoveController(
            Position position,
            AttackController attackController,
            CastlingController castlingController
    ) {
        this.position = position;
        this.attackController = attackController;
        this.castlingController = castlingController;
    }

    public boolean isValid(Move move) {
        try {
            Piece piece = tryToGetPiece(move);
            return checkMoveIsInPieceMoves(piece, move);

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private Piece tryToGetPiece(Move move) {
        Piece piece = position.getPiece(move.start);
        if(piece == null) {
            throw new EmptySquareException("No piece to move");
        }
        return piece;
    }

    private boolean checkMoveIsInPieceMoves(Piece piece, Move move) {
        PieceMechanics mechanics = PieceMechanicsFactory.getPieceMechanics(piece);
        List<Square> targetSquares = mechanics.moves();
        return targetSquares.stream().anyMatch(square -> square.equals(move.end));
    }

    private static class EmptySquareException extends RuntimeException {
        public EmptySquareException(String message) {}
    }
}
