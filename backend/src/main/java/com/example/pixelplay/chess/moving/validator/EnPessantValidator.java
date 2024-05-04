package com.example.pixelplay.chess.moving.validator;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.PieceType;
import com.example.pixelplay.chess.moving.MoveValidator;
import com.example.pixelplay.chess.position.Position;

public class EnPessantValidator implements MoveValidator {
    private final Position position;

    public EnPessantValidator(Position position) {
        this.position = position;
    }

    @Override
    public boolean isValid(Move move) {
        boolean isPawnMove = position.getPiece(move.start()).type() == PieceType.Pawn;
        boolean canEnPessant = position.canEnPessant(move.end());

        return isPawnMove && canEnPessant;
    }
}
