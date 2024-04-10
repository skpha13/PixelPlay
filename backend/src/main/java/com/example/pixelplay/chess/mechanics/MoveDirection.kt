package com.example.pixelplay.chess.mechanics;

import java.util.ArrayList;
import java.util.List;

public class MoveDirection {
    public int rank;
    public int file;
    public MoveDirection(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }

    public final static List<MoveDirection> diagonalMoves = new ArrayList<>(
            List.of(
                    new MoveDirection(-1, -1),
                    new MoveDirection(-1, 1),
                    new MoveDirection(1, -1),
                    new MoveDirection(1, 1)
            )
    );

    public final static List<MoveDirection> axialMoves = List.of(
            new MoveDirection(-1, 0),
            new MoveDirection(1, 0),
            new MoveDirection(0, 1),
            new MoveDirection(0, -1)
    );
}
