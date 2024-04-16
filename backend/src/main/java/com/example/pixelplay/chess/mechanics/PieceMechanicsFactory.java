package com.example.pixelplay.chess.mechanics;

import com.example.pixelplay.chess.PieceType;
import com.example.pixelplay.chess.Position;

public class PieceMechanicsFactory {
    private PieceMechanicsFactory() {}

    public static PieceMechanics getPieceMechanics(PieceType piece, Position position, Square square) {
        return switch (piece) {
            case PieceType.BlackKing, PieceType.WhiteKing -> new KingMechanics();
            case PieceType.BlackBishop, PieceType.WhiteBishop -> new BishopMechanics();
            case PieceType.BlackKnight, PieceType.WhiteKnight -> new KnightMechanics();
            case PieceType.BlackPawn -> new BlackPawnMechanics();
            case PieceType.BlackQueen, PieceType.WhiteQueen -> new QueenMechanics();
            case PieceType.BlackRook, PieceType.WhiteRook -> new RookMechanics();
            case PieceType.WhitePawn -> new WhitePawnMechanics();
            default -> null;
        };
    }
}
