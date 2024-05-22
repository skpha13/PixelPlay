package org.example.backend.chess.dtos;

public record MoveRequest(
        String id,
        SquareDto startSquare,
        SquareDto endSquare,
        String promotionType
) {
}
