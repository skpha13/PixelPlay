package org.example.backend.chess.logic.moving;

import org.example.backend.chess.logic.base.Move;

public interface MoveValidator {
   
    boolean isValid(Move move);
}
