package org.example.backend.chess;


import org.example.backend.chess.dtos.GameDto;
import org.example.backend.chess.dtos.PieceDto;
import org.example.backend.chess.dtos.SquareDto;
import org.example.backend.chess.logic.Game;
import org.example.backend.chess.logic.base.Color;
import org.example.backend.chess.logic.base.Piece;
import org.example.backend.chess.logic.base.PieceType;
import org.example.backend.chess.logic.base.Square;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    private Mapper() {}

    public static GameDto toGameDto(Game game) {
        List<PieceDto> pieces = new ArrayList<>();

        for(int rank=0; rank<8; rank++) {
            for(int file=0; file<8; file++) {
                Square square = new Square(rank, file);
                if(!game.getPosition().isFree(square)) {
                    PieceDto pieceDto = toPieceDto(game.getPosition().getPiece(square), square);
                    pieces.add(pieceDto);
                }
            }
        }
        GameDto gameDto = new GameDto(pieces);
        return gameDto;
    }

    public static PieceDto toPieceDto(Piece piece, Square square) {
        String type = switch (piece.type()) {
            case PieceType.King -> "k";
            case PieceType.Queen -> "q";
            case PieceType.Rook -> "r";
            case PieceType.Bishop -> "b";
            case PieceType.Knight -> "h";
            case PieceType.Pawn -> "p";
            default -> "";
        };
        if(piece.color() == Color.WHITE) {
            type = type.toUpperCase();
        }

        return new PieceDto(type, square.getRank(), square.getFile());

    }

    public static Square toSquare(SquareDto squareDto) {
        Square square = new Square(squareDto.rank(), squareDto.file());
        return square;
    }
}
