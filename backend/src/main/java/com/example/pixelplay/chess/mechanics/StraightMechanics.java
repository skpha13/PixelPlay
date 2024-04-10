package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.Position;
import com.example.pixelplay.chess.PositionUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class StraightMechanics {
    protected Position position;
    protected byte square;

    public StraightMechanics(Position position, byte square) {
        this.position = position;
        this.square = square;
    }

    abstract List<MoveDirection> getMovingDirection();

    public List<Byte> attackingSquares() {
        List<Byte> squares = new ArrayList<>();
        int rank = PositionUtil.getRank(square);
        int file = PositionUtil.getFile(square);
        for(MoveDirection moveDirection : getMovingDirection()) {
            int newRank = rank + moveDirection.rank;
            int newFile = file + moveDirection.file;
            while(PositionUtil.isOnBoard(newRank, newFile)) {
                squares.add(PositionUtil.getSquare(newRank, newFile));
                if(!position.isFree(PositionUtil.getSquare(newRank, newFile))) {
                    break;
                }
                newRank = newRank + moveDirection.rank;
                newFile = newFile + moveDirection.file;
            }
        }
        return squares;
    }
}
