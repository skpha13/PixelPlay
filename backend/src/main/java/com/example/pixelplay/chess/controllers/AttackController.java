package com.example.pixelplay.chess.controllers;

import com.example.pixelplay.chess.base.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        whiteAttacks.addAttacks(attackByColor(Color.WHITE));

        blackAttacks = new NumberBoard();
        blackAttacks.addAttacks(attackByColor(Color.BLACK));
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


    public List<Square> attackByColor(Color color) {
        List<Square> attacks = new ArrayList<>();

        for(int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j ++) {
                Piece piece =position.getPiece(new Square(i, j));
                Square square = new Square(i, j);
                if(piece.type.color() == color) {
                    assert piece.mechanics != null;
                    attacks.addAll(Objects.requireNonNull(piece.mechanics.attacks(position, square)));
                }
            }
        }
        return attacks;
    }

    public boolean kingIsInCheck(Color color) {
        Square kingSquare = position.findKing(color);
        assert kingSquare != null;
        return isAttackedBy(color.reverse(), kingSquare);
    }
}
