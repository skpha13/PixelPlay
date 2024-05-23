package org.example.backend.chess.logic.moving.generator;

import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.moving.MoveGenerator;
import org.example.backend.chess.logic.moving.MoveValidator;
import org.example.backend.chess.logic.moving.validator.GeneralMoveValidator;
import org.example.backend.chess.logic.position.Position;
import org.example.backend.chess.logic.base.PieceType;
import org.example.backend.chess.logic.base.Square;

import java.util.ArrayList;
import java.util.List;

class LongRangeMoveGenerator implements MoveGenerator {
    private final Position position;
    private final Square square;
    private final MoveValidator validator;

    public LongRangeMoveGenerator(Position position, Square square) {
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
            while(position.isFree(end)) {
                Move move = new Move(square, end);
                if(validator.isValid(move)) {
                    moves.add(move);
                }
                end = end.move(direction);
            }
        }

        return moves;
    }

    private List<Move> getCaptures() {
        List<Move> moves = new ArrayList<>();
        for(Square direction: getScopes()) {
            Square end = square.move(direction);
            while(position.isFree(end)) {
                end = end.move(direction);
            }

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
        return switch (position.getPiece(square).type()) {
            case PieceType.Queen -> Square.Companion.getCompleteMoves();
            case PieceType.Rook -> Square.Companion.getAxialMoves();
            case PieceType.Bishop -> Square.Companion.getDiagonalMoves();
            default -> List.of();
        };
    }


}
