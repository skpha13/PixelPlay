package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.Position;

import java.util.ArrayList;
import java.util.List;

public class WhitePawnMechanics extends PawnMechanics {

    public WhitePawnMechanics(Position position, byte square) {
        super(position, square);
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


}
