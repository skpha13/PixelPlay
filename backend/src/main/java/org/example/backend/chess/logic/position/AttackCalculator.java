package org.example.backend.chess.logic.position;

import org.example.backend.chess.logic.base.Color;
import org.example.backend.chess.logic.base.NumberBoard;
import org.example.backend.chess.logic.base.Piece;
import org.example.backend.chess.logic.base.Square;
import org.example.backend.chess.logic.mechanics.PieceMechanics;
import org.example.backend.chess.logic.mechanics.PieceMechanicsFactory;

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
                Square square = new Square(i, j);
                Piece piece = position.getPiece(square);
                if(piece.isColor(color)) {
                    PieceMechanics mechanics = PieceMechanicsFactory.getPieceMechanics(position, square, piece);
                    attacks.addAll(mechanics.attacks());
                }
            }
        }
        return attacks;
    }

    public boolean kingIsInCheck(Color color) {
        Square kingSquare = position.findKing(color);
        return isAttackedBy(color.reverse(), kingSquare);
    }
}
