package com.example.pixelplay.chess.moving.generator;

import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.moving.MoveGenerator;
import com.example.pixelplay.chess.moving.MoveValidator;
import com.example.pixelplay.chess.moving.validator.GeneralMoveValidator;
import com.example.pixelplay.chess.position.Position;
import com.example.pixelplay.chess.position.PositionUtil;

import java.util.ArrayList;
import java.util.List;

public class KingMoveGenerator implements MoveGenerator {
    private final Position position;
    private final Square start;
    private final MoveValidator validator;

    public KingMoveGenerator(Position position, Square square) {
        this.position = position;
        this.start = square;
        this.validator = new GeneralMoveValidator(position);
    }

    public List<Move> getLegalMoves() {
        List<Move> moves = new ArrayList<>();

        moves.addAll(getSimpleMoves());
        moves.addAll(getCaptures());
        moves.addAll(getCastles());

        return moves;
    }

    private List<Move> getSimpleMoves() {
        List<Move> moves = new ArrayList<>();
        for(Square direction: scope) {
            Square end = start.move(direction);
            Move move = new Move(start, end);

            if(isLegalMoveToEmptySquare(move)) {
                moves.add(move);
            }
        }
        return moves;
    }

    private boolean isLegalMoveToEmptySquare(Move move) {
        try {
            return PositionUtil.isOnBoard(move.end())
                    && position.isFree(move.end())
                    && validator.isValid(move);
        } catch (IllegalArgumentException e) {
            return false;
        }

    }

    private List<Move> getCaptures() {
        List<Move> moves = new ArrayList<>();
        for(Square direction: scope) {
            Square end = start.move(direction);
            Move move = new Move(start, end);

            if(isLegalCapture(move)) {
                moves.add(move);
            }
        }
        return moves;
    }

    private boolean isLegalCapture(Move move) {
        try {
            return PositionUtil.isOnBoard(move.end())
                    && canCapture(move.end())
                    && validator.isValid(move);
        }
        catch (Exception e) {
            return false;
        }

    }


    private boolean canCapture(Square square) {
        boolean isNotFree = !position.isFree(square);
        Color current = position.getPiece(start).color();
        Color next = position.getPiece(square).color();
        boolean differentColors = !current.equals(next);

        return isNotFree && differentColors;
    }

    private List<Move> getCastles() {
        ArrayList<Move> moves = new ArrayList<>();
        Move shortCastle = new Move(start, start.move(shortCastleDirection, 2));
        if(validator.isValid(shortCastle)) {
            moves.add(shortCastle);
        }

        Move longCastle = new Move(start, start.move(longCastleDirection, 3));
        if(validator.isValid(longCastle)) {
            moves.add(longCastle);
        }

        return moves;
    }


    private static List<Square> scope = Square.Companion.getCompleteMoves();

    private static Square shortCastleDirection = PositionUtil.shortCastleDirection;
    private static Square longCastleDirection = PositionUtil.longCastleDirection;
}
