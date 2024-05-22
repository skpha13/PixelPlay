package org.example.backend.chess.dtos;

import java.util.ArrayList;
import java.util.List;

public record GameDto(
    List<PieceDto> pieces,
    boolean isCheckmate,
    boolean isStalemate
) {

}
