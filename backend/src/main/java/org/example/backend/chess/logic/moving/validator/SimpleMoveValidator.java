package org.example.backend.chess.logic.moving.validator;

import org.example.backend.chess.logic.base.Square;
import org.example.backend.chess.logic.mechanics.PieceMechanics;
import org.example.backend.chess.logic.position.Position;
import org.example.backend.chess.logic.mechanics.PieceMechanicsFactory;
import org.example.backend.chess.logic.moving.MoveValidator;
import org.example.backend.chess.logic.moving.util.PieceGetter;
import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.base.Piece;

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
//            System.out.println(e.getMessage());
            return false;
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
