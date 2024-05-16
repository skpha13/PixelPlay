package org.example.backend.chess.dtos;

import java.util.List;
import java.util.UUID;

public record GameDto(
    UUID uuid,
    List<PieceDto> pieces
) {

}
