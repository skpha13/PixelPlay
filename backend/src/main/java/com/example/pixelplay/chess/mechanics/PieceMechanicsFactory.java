package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.PieceType;
import com.example.pixelplay.chess.Position;

public class PieceMechanicsFactory {
    private PieceMechanicsFactory() {}

    public static PieceMechanics getPieceMechanics(PieceType piece, Position position, Byte index) {
        return switch (piece) {
            case PieceType.BlackKing, PieceType.WhiteKing -> new KingMechanics(position, index);
            case PieceType.BlackBishop, PieceType.WhiteBishop -> new BishopMechanics(position, index);
            case PieceType.BlackKnight, PieceType.WhiteKnight -> new KnightMechanics(position, index);
            case PieceType.BlackPawn -> new BlackPawnMechanics(position, index);
            case PieceType.BlackQueen, PieceType.WhiteQueen -> new QueenMechanics(position, index);
            case PieceType.BlackRook, PieceType.WhiteRook -> new RookMechanics(position, index);
            case PieceType.WhitePawn -> new WhitePawnMechanics(position, index);
            default -> null;
        };
    }
}
