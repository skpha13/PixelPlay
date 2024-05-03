package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.base.Piece;
import com.example.pixelplay.chess.base.PieceType;
import com.example.pixelplay.chess.position.Position;
import com.example.pixelplay.chess.base.Square;
import com.example.pixelplay.chess.mechanics.pieces.*;

public class PieceMechanicsFactory {
    private PieceMechanicsFactory() {}

    public static PieceMechanics getPieceMechanics(Position position, Square square, Piece piece) {
        return switch (piece.type()) {
            case PieceType.King -> new KingMechanics(position, square);
            case PieceType.Bishop -> new BishopMechanics(position, square);
            case PieceType.Knight -> new KnightMechanics(position, square);
            case PieceType.Queen -> new QueenMechanics(position, square);
            case PieceType.Rook -> new RookMechanics(position, square);
            case PieceType.Pawn -> {
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
