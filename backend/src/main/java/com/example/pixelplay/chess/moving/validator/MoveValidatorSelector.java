package com.example.pixelplay.chess.moving.validator;

import com.example.pixelplay.chess.base.Move;

public class MoveValidatorSelector {
    private MoveValidatorSelector() {}

    public static MoveValidator getValidator(Move move) {
        return new SimpleMoveValidator();
    }
}
