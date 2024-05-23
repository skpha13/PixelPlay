package org.example.backend.chess.logic.mechanics;

import org.example.backend.chess.logic.base.Color;
import org.example.backend.chess.logic.base.Piece;
import org.example.backend.chess.logic.base.PieceType;
import org.example.backend.chess.logic.base.Square;
import org.example.backend.chess.logic.mechanics.pieces.*;
import org.example.backend.chess.logic.position.Position;

public class PieceMechanicsFactory {
    private PieceMechanicsFactory() {}

    public static PieceMechanics getPieceMechanics(Position position, Square square, Piece piece) {
        return switch (piece.type()) {
            case King -> new KingMechanics(position, square);
            case Bishop -> new BishopMechanics(position, square);
            case Knight -> new KnightMechanics(position, square);
            case Queen -> new QueenMechanics(position, square);
            case Rook -> new RookMechanics(position, square);
            case Pawn -> {
                if(piece.isColor(Color.BLACK)) {
                    yield new BlackPawnMechanics(position, square);
                }
                else {
                    yield new WhitePawnMechanics(position, square);
                }
            }
            default -> new EmptyMechanics(position, square);
        };
    }
}
