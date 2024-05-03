package com.example.pixelplay.chess.moving.validator;

import com.example.pixelplay.chess.PositionGenerator;
import com.example.pixelplay.chess.base.*;
import com.example.pixelplay.chess.exceptions.EmptySquareException;
import com.example.pixelplay.chess.exceptions.IncorrectTurnException;

public interface MoveValidator {
   
    boolean isValid(Move move);
}
