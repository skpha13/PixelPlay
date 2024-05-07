package com.example.pixelplay.chess.moving.generator;

import com.example.pixelplay.chess.base.Move;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.moving.MoveGenerator;
import com.example.pixelplay.chess.position.Position;

import java.util.List;

public class PieceMoveGenerator implements MoveGenerator {
    private final Position position;
    private final Square square;

    public PieceMoveGenerator(Position position, Square square) {
        this.position = position;
        this.square = square;
    }


    @Override
    public List<Move> getLegalMoves() {
        MoveGenerator generator = getGenerator();
        return generator.getLegalMoves();
    }

    private MoveGenerator getGenerator() {
        return switch (position.getPiece(square).type()) {
            case Pawn -> new PawnMoveGenerator(position, square);
            case Knight -> new KnightMoveGenerator(position, square);
            case Bishop, Rook, Queen -> new LongRangeMoveGenerator(position, square);
            case King -> new KingMoveGenerator(position, square);
            case None -> new EmptyMoveGenerator();
        };
    }
}
