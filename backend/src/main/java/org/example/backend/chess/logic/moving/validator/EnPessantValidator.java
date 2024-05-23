package org.example.backend.chess.logic.moving.validator;

import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.base.PieceType;
import org.example.backend.chess.logic.moving.MoveValidator;
import org.example.backend.chess.logic.position.Position;
import org.example.backend.chess.logic.base.Move;
import org.example.backend.chess.logic.base.PieceType;
import org.example.backend.chess.logic.position.Position;

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
