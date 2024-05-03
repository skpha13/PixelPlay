package com.example.pixelplay.chess.moving.basic;

import com.example.pixelplay.chess.position.Position;
import com.example.pixelplay.chess.base.*;
import com.example.pixelplay.chess.moving.exceptions.IncorrectMoveException;
import com.example.pixelplay.chess.mechanics.PieceMechanics;
import com.example.pixelplay.chess.mechanics.PieceMechanicsFactory;
import com.example.pixelplay.chess.moving.MoveValidator;
import com.example.pixelplay.chess.moving.PieceGetter;

import java.util.List;

public class SimpleMoveValidator implements MoveValidator {
    private final Position position;

    public SimpleMoveValidator(Position position) {
        this.position = position;
    }


    @Override
    public boolean isValid(Move move) {
        try {
            checkMoveIsInPieceMoves(move);
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

    private void checkMoveIsInPieceMoves(Move move) {
        Piece piece = getPiece(move);
        PieceMechanics mechanics = PieceMechanicsFactory.getPieceMechanics(position, move.start(), piece);
        List<Square> targetSquares = mechanics.moves();
        if (targetSquares.stream().noneMatch(square -> square.equals(move.end()))) {
            throw new IncorrectMoveException("Move does not respect piece rules");
        }
    }

}
