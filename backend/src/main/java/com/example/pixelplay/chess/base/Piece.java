package com.example.pixelplay.chess.base;

import com.example.pixelplay.chess.mechanics.PieceMechanics;
import com.example.pixelplay.chess.mechanics.PieceMechanicsFactory;

import java.util.List;

public final class Piece {
    private final PieceType type;
    private final Position position;
    private final Square square;

    public Piece(PieceType type, Position position, Square square) {
        this.type = type;
        this.position = position;
        this.square = square;
    }

    public Position getPosition() {
        return position;
    }

    public Square getSquare() {
        return square;
    }

    public PieceType getType() {
        return type;
    }

    public Color getColor() {
        return type.color();
    }

    public boolean isColor(Color color) {
        return type.color() == color;
    }
}
