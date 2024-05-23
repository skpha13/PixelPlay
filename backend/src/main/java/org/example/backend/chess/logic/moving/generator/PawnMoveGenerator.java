package org.example.backend.chess.logic.moving.generator;

import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.base.PieceType;
import org.example.backend.chess.logic.moving.MoveGenerator;
import org.example.backend.chess.logic.moving.MoveValidator;
import org.example.backend.chess.logic.moving.validator.GeneralMoveValidator;
import org.example.backend.chess.logic.position.Position;
import org.example.backend.chess.logic.base.Color;
import org.example.backend.chess.logic.base.Square;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

class PawnMoveGenerator implements MoveGenerator {
    private final Position position;
    private final Square square;
    private final MoveValidator validator;
    private final Square direction;

    public PawnMoveGenerator(Position position, Square square) {
        this.position = position;
        this.square = square;
        this.validator = new GeneralMoveValidator(position);
        direction = computeDirection();
    }

    private Square computeDirection() {
        return switch (position.getPiece(square).color()) {
            case BLACK -> new Square(-1, 0);
            case WHITE -> new Square(1, 0);
        };
    }

    @Override
    public List<Move> getLegalMoves() {
        List<Move> legalMoves = new ArrayList<Move>();

        legalMoves.addAll(getForwardMoves());
        if(canJumpForward()) {
            legalMoves.add(new Move(square, square.move(direction, 2)));
        }
        legalMoves.addAll(getCaptures());
        legalMoves.addAll(getEnPessantMoves());

        return legalMoves;
    }

    private List<Move> getForwardMoves() {
        List<Move> moves = new ArrayList<>();
        Square end = square.move(direction);
        boolean movesToLastRank = end.getRank() == getLastRank();
        if(canMoveForward() && movesToLastRank) {
            moves.addAll(getPromotionMoves(end));
        }
        if(canMoveForward() &&!movesToLastRank) {
            moves.add(new Move(square, end));
        }

        return moves;
    }

    private boolean canMoveForward() {
        Square end = square.move(direction);
        Move forward = new Move(square, end, PieceType.Queen);
        boolean isFree = position.isFree(end);
        boolean isValid = validator.isValid(forward);

        return isFree && isValid;
    }

    private boolean canJumpForward() {
        Square middle = square.move(direction);
        Square end = square.move(direction, 2);
        Move forward = new Move(square, end);
        boolean isFree = position.isFree(end) && position.isFree(middle);
        boolean isStartingFile = square.getRank() == getstartingRank();
        boolean isValid = validator.isValid(forward);

        return isFree && isStartingFile && isValid;
    }

    private int getstartingRank() {
        return switch (position.getPiece(square).color()) {
            case BLACK -> 6;
            case WHITE -> 1;
        };
    }

    private List<Move> getEnPessantMoves() {
        List<Move> moves = new ArrayList<>();

        List<Square> possibleSquares = getDiagonalSquares();

        for(Square end: possibleSquares) {
            Move enPessant = new Move(square, end);
            boolean isEnPessantSquare = position.canEnPessant(end);
            boolean isValid = validator.isValid(enPessant);

            if(isEnPessantSquare && isValid) {
                moves.add(enPessant);
            }
        }
        return moves;
    }

    private @NotNull List<Square> getDiagonalSquares() {
        List<Square> possibleSquares = new ArrayList<>(List.of(
                square.move(direction).move(new Square(0, -1)),
                square.move(direction).move(new Square(0, 1))
        ));
        return possibleSquares;
    }

    private List<Move> getPromotionMoves(Square end) {
        List<Move> moves = new ArrayList<>();

        moves.add(new Move(square, end, PieceType.Queen));
        moves.add(new Move(square, end, PieceType.Rook));
        moves.add(new Move(square, end, PieceType.Bishop));
        moves.add(new Move(square, end, PieceType.Knight));

        return moves;
    }

    private List<Move> getCaptures() {
        List<Move> moves = new ArrayList<>();
        List<Square> possibleSquares = getDiagonalSquares();

        for(Square end: possibleSquares) {
            Move capture = new Move(square, end);
            boolean isNotFree = !position.isFree(end);
            boolean isEnemy = position.getPiece(end).color() != position.getPiece(square).color();
            boolean isValid = validator.isValid(capture);
            boolean movesToLastRank = end.getRank() == getLastRank();

            if(isNotFree && isEnemy && movesToLastRank){
                moves.addAll(getPromotionMoves(end));
            }
            if(isNotFree && isEnemy && isValid && !movesToLastRank) {
                moves.add(capture);
            }
        }
        return moves;
    }

    private int getLastRank() {
        return switch (position.getPiece(square).color()){
            case WHITE -> 7;
            case BLACK -> 0;
        };
    }


}
