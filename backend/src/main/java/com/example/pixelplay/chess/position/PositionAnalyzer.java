package com.example.pixelplay.chess.position;

import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.moving.MoveGenerator;
import com.example.pixelplay.chess.moving.generator.PositionMoveGenerator;

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
