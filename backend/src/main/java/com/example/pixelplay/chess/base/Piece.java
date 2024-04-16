package com.example.pixelplay.chess.base;

import com.example.pixelplay.chess.mechanics.PieceMechanics;
import com.example.pixelplay.chess.mechanics.PieceMechanicsFactory;

import java.util.List;

public final class Piece {
    public final PieceType type;
    public final PieceMechanics mechanics;
    private final Position position;
    private final Square square;

    public Piece(PieceType type, Position position, Square square) {
        this.type = type;
        this.mechanics = PieceMechanicsFactory.getPieceMechanics(type, position, square);
        this.position = position;
        this.square = square;
    }

    public boolean isAttacked() {
        assert type.color() != null;
        List<Square> attacked = position.attackByColor(type.color().reverse());
        for(Square attackedSquare : attacked) {
            if(this.square.equals(attackedSquare)) {
                return true;
            }
        }
        return false;
    }
}
