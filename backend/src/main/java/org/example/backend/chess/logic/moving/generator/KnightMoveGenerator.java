package org.example.backend.chess.logic.moving.generator;

import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.moving.MoveGenerator;
import org.example.backend.chess.logic.moving.MoveValidator;
import org.example.backend.chess.logic.moving.validator.GeneralMoveValidator;
import org.example.backend.chess.logic.position.Position;
import org.example.backend.chess.logic.base.Square;

import java.util.ArrayList;
import java.util.List;

class KnightMoveGenerator implements MoveGenerator {
    private final Position position;
    private final Square square;
    private final MoveValidator validator;

    public KnightMoveGenerator(Position position, Square square) {
        this.position = position;
        this.square = square;
        this.validator = new GeneralMoveValidator(position);
    }

    @Override
    public List<Move> getLegalMoves() {
        List<Move> moves = new ArrayList<>();

        moves.addAll(getMovesToFreeSquares());
        moves.addAll(getCaptures());

        return moves;
    }

    private List<Move> getMovesToFreeSquares() {
        List<Move> moves = new ArrayList<>();

        for(Square direction: getScopes()) {
            Square end = square.move(direction);
            Move move = new Move(square, end);
            boolean isFree = position.isFree(end);
            boolean isValid = validator.isValid(move);

            if(isFree && isValid) {
                moves.add(move);
            }
        }
        return moves;
    }

    private List<Move> getCaptures() {
        List<Move> moves = new ArrayList<>();

        for(Square direction: getScopes()) {
            Square end = square.move(direction);
            Move move = new Move(square, end);
            boolean isNotFree = !position.isFree(end);
            boolean isEnemy = position.getPiece(end).color() != position.getPiece(square).color();
            boolean isValid = validator.isValid(move);

            if(isNotFree && isEnemy && isValid) {
                moves.add(move);
            }
        }
        return moves;
    }

    private List<Square> getScopes() {
        return Square.Companion.getKnightMoves();
    }
}
