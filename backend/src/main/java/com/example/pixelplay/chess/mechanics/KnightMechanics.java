package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.Position;
import com.example.pixelplay.chess.PositionGenerator;
import com.example.pixelplay.chess.PositionUtil;

import java.util.ArrayList;
import java.util.List;

public class KnightMechanics implements PieceMechanics {
    private static final List<Jump> jumps = new ArrayList<>(
            List.of(
                    new Jump(2, 1),
                    new Jump(1, 2),
                    new Jump(-1, 2),
                    new Jump(-2, 1),
                    new Jump(-2, -1),
                    new Jump(-1, -2),
                    new Jump(1, -2),
                    new Jump(2, -1)

            )
    );

    private Position position;
    private byte square;

    public KnightMechanics(Position position, byte square) {
        this.position = position;
        this.square = square;
    }

    @Override
    public List<Byte> attackingSquares() {
        List<Byte> squares = new ArrayList<>();
        int rank = PositionUtil.getRank(square);
        int file = PositionUtil.getFile(square);
        for (Jump jump : jumps) {
            int newRank = jump.rank + rank;
            int newFile = jump.file + file;
            if(PositionUtil.isOnBoard(newRank, newFile)) {
                squares.add(PositionUtil.getSquare(newRank, newFile));
            }
        }
        return squares;
    }


     record Jump(int rank, int file) { }
}
