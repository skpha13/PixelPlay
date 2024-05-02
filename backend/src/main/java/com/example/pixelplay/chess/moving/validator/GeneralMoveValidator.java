package com.example.pixelplay.chess.moving.validator;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Piece;
import com.example.pixelplay.chess.base.Position;
import com.example.pixelplay.chess.exceptions.EmptySquareException;

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
        return new SimpleMoveValidator(position);
    }
}
