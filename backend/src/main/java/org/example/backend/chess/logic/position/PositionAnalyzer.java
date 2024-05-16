package org.example.backend.chess.logic.position;

import org.example.backend.chess.logic.base.Color;
import org.example.backend.chess.logic.moving.MoveGenerator;
import org.example.backend.chess.logic.moving.generator.PositionMoveGenerator;

public class PositionAnalyzer {
    private final Position position;
    private final AttackCalculator attackCalculator;

    public PositionAnalyzer(Position position) {
        this.position = position;
        this.attackCalculator = new AttackCalculator(position);
    }

    public boolean isCheckmate() {
        return kingChecked() && noLegalMoves();
    }

    private boolean kingChecked() {
        Color currentTurn = position.getTurn();
        return attackCalculator.kingIsInCheck(currentTurn);
    }

    private boolean noLegalMoves() {
        MoveGenerator generator = new PositionMoveGenerator(position);
        return generator.getLegalMoves().isEmpty();
    }

    public boolean isStalemate() {
        return !kingChecked() && noLegalMoves();
    }
}
