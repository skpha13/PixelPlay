package com.example.pixelplay.chess;

import com.example.pixelplay.chess.mechanics.PieceMechanics;
import com.example.pixelplay.chess.mechanics.PieceMechanicsFactory;

import java.util.List;

public final class Piece {
    public final PieceType type;
    public final PieceMechanics mechanics;
    private final Position position;
    private final byte index;

    public Piece(PieceType type, Position position, byte index) {
        this.type = type;
        this.mechanics = PieceMechanicsFactory.getPieceMechanics(type, position, index);
        this.position = position;
        this.index = index;
    }

    public boolean isAttacked() {
        assert type.color() != null;
        List<Byte> attackedIndexes = position.computeAttackedIndexes(type.color().reverse());
        for(byte attackedIndex : attackedIndexes) {
            if(index == attackedIndex) {
                return true;
            }
        }
        return false;
    }
}
