package org.example.backend.chess.dtos;

public record PieceDto(
        String type,
        int rank,
        int file
) {
}
