package com.example.pixelplay.chess.position;

import com.example.pixelplay.chess.base.*;
import com.example.pixelplay.chess.mechanics.PieceMechanics;
import com.example.pixelplay.chess.mechanics.PieceMechanicsFactory;

import java.util.ArrayList;
import java.util.List;

public class AttackCalculator {
    private final Position position;
    private NumberBoard whiteAttacks;
    private NumberBoard blackAttacks;

    public AttackCalculator(Position position) {
        this.position = position;
        initializeAttackBoards();
    }

    private void initializeAttackBoards() {
        whiteAttacks = new NumberBoard();
        whiteAttacks.addAttacks(attacksByColor(Color.WHITE));

        blackAttacks = new NumberBoard();
        blackAttacks.addAttacks(attacksByColor(Color.BLACK));
    }

    public NumberBoard getAttackBoard(Color color) {
        return switch (color) {
            case WHITE -> whiteAttacks;
            case BLACK -> blackAttacks;
        };
    }


    public boolean isAttackedBy(Color color, Square square) {
        return switch (color) {
            case WHITE -> whiteAttacks.getValue(square) > 0;
            case BLACK -> blackAttacks.getValue(square) > 0;
        };
    }


    public List<Square> attacksByColor(Color color) {
        List<Square> attacks = new ArrayList<>();

        for(int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j ++) {
                Piece piece =position.getPiece(new Square(i, j));
                if(piece.isColor(color)) {
                    PieceMechanics mechanics = PieceMechanicsFactory.getPieceMechanics(position, piece);
                    attacks.addAll(mechanics.attacks());
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
