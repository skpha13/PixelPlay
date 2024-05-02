package com.example.pixelplay.chess.moving.validator;

import com.example.pixelplay.chess.base.*;
import com.example.pixelplay.chess.exceptions.IncorrectMoveException;
import com.example.pixelplay.chess.mechanics.PieceMechanics;
import com.example.pixelplay.chess.mechanics.PieceMechanicsFactory;

import java.util.List;

class SimpleMoveValidator implements MoveValidator {
    private final Position position;

    public SimpleMoveValidator(Position position) {
        this.position = position;
    }


    @Override
    public boolean isValid(Move move) {
        try {
            Color currentTurn = position.getTurn();
            Piece piece = getPiece(move);
            checkCurrentTurnColor(piece, currentTurn);
            checkMoveIsInPieceMoves(piece, move);
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private Piece getPiece(Move move) {
        PieceGetter pieceGetter = new PieceGetter(position);
        return pieceGetter.tryToGetPiece(move);
    }

    private void checkMoveIsInPieceMoves(Piece piece, Move move) {
        PieceMechanics mechanics = PieceMechanicsFactory.getPieceMechanics(piece);
        List<Square> targetSquares = mechanics.moves();
        if (!targetSquares.stream().anyMatch(square -> square.equals(move.end))) {
            throw new IncorrectMoveException("Move does not respect piece rules");
        }
    }

}
