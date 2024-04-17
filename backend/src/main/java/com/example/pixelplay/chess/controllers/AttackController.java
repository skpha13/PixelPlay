package com.example.pixelplay.chess.controllers;

import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.base.Position;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.base.NumberBoard;

public class AttackController {
    private final Position position;
    private NumberBoard whiteAttacks;
    private NumberBoard blackAttacks;

    public AttackController(Position position) {
        this.position = position;
        initializeAttackBoards();
    }

    private void initializeAttackBoards() {
        whiteAttacks = new NumberBoard();
        whiteAttacks.addAttacks(position.attackByColor(Color.WHITE));

        blackAttacks = new NumberBoard();
        blackAttacks.addAttacks(position.attackByColor(Color.BLACK));
    }

    public NumberBoard getAttackBoard(Color color) {
        return switch (color) {
            case WHITE -> whiteAttacks;
            case BLACK -> blackAttacks;
            default -> throw new IllegalStateException("Unexpected value: " + color);
        };
    }


    public boolean isAttackedBy(Color color, Square square) {
        return switch (color) {
            case WHITE -> whiteAttacks.getValue(square) > 0;
            case BLACK -> blackAttacks.getValue(square) > 0;
        };
    }
}
