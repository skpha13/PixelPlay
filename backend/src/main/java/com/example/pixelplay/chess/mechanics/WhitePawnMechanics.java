package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.Position;

import java.util.ArrayList;
import java.util.List;

public class WhitePawnMechanics implements PieceMechanics {
    private final Position position;
    private final byte square;

    public WhitePawnMechanics(Position position, byte square) {
        this.position = position;
        this.square = square;
    }

    @Override
    public List<Byte> attackingSquares() {
        List<Byte> squares = new ArrayList<>();

        if(!isOnLeftEdge()) {
            squares.add((byte) (square + 7));
        }
        if(!isOnRightEdge()) {
            squares.add((byte) (square + 9));
        }

        return squares;
    }

    private boolean isOnLeftEdge() {
        return square % 8 == 0;
    }

    private boolean isOnRightEdge() {
        return (square - 7) % 8 == 0;
    }
}
