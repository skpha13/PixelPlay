package com.example.pixelplay.chess.moving.generator;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.moving.MoveGenerator;
import com.example.pixelplay.chess.moving.MoveValidator;
import com.example.pixelplay.chess.moving.validator.GeneralMoveValidator;
import com.example.pixelplay.chess.position.Position;

import java.util.ArrayList;
import java.util.List;

public class LongRangeMoveGenerator implements MoveGenerator {
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
            case Queen -> Square.Companion.getCompleteMoves();
            case Rook -> Square.Companion.getAxialMoves();
            case Bishop -> Square.Companion.getDiagonalMoves();
            default -> List.of();
        };
    }


}
