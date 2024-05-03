package com.example.pixelplay.chess.moving.validator;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.Position;
import com.example.pixelplay.chess.base.Piece;
import com.example.pixelplay.chess.base.PieceType;

import static java.lang.Math.abs;

public class GeneralMoveValidator implements MoveValidator{
    private final Position position;

    public GeneralMoveValidator(Position position) {
        this.position = position;
    }

    @Override
    public boolean isValid(Move move) {
        MoveValidator validator = getValidator(move);
        return validator.isValid(move);
    }


    private MoveValidator getValidator(Move move) {
        if(isCastle(move)) {
            return new CastleValidator(position);
        }
        return new SimpleMoveValidator(position);
    }

    private boolean isCastle(Move move) {
        Piece piece = position.getPiece(move.start);
        boolean kingMoved = (piece.getType() == PieceType.BlackKing) || (piece.getType() == PieceType.WhiteKing);
        boolean movedTwoFiles = abs(move.direction().getFile()) == 2;

        return kingMoved && movedTwoFiles;
    }
}
