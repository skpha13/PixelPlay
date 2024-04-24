package com.example.pixelplay.chess.controllers;

import com.example.pixelplay.chess.base.*;
import com.example.pixelplay.chess.exceptions.EmptySquareException;
import com.example.pixelplay.chess.exceptions.IncorrectMoveException;
import com.example.pixelplay.chess.exceptions.IncorrectTurnException;
import com.example.pixelplay.chess.mechanics.PieceMechanics;
import com.example.pixelplay.chess.mechanics.PieceMechanicsFactory;

import java.util.List;

public class MoveController {
    private final Position position;

    public MoveController(
            Position position    ) {
        this.position = position;
    }

    public boolean isValid(Move move) {
        try {
            Color currentTurn = position.getTurn();
            Piece piece = tryToGetPiece(move);
            checkCurrentTurnColor(piece, currentTurn);
            checkMoveIsInPieceMoves(piece, move);
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private Piece tryToGetPiece(Move move) {
        Piece piece = position.getPiece(move.start);
        if(piece == null) {
            throw new EmptySquareException("No piece to move");
        }
        return piece;
    }

    private void checkMoveIsInPieceMoves(Piece piece, Move move) {
        PieceMechanics mechanics = PieceMechanicsFactory.getPieceMechanics(piece);
        List<Square> targetSquares = mechanics.moves();
        if(!targetSquares.stream().anyMatch(square -> square.equals(move.end))) {
            throw new IncorrectMoveException("Move does not respect piece rules");
        }
    }

    private void checkCurrentTurnColor(Piece piece, Color currentTurn) {
        if(piece.getColor() != currentTurn) {
            throw  new IncorrectTurnException("It is " + currentTurn.toString() + "'s turn!");
        }
    }
}
