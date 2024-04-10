package com.example.pixelplay.chess;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.metal.MetalIconFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private static Game game = new Game();

    @Test
    void checkPositionAfterInitialize() {
        Position position = game.getPosition();
        Position initialPosition = PositionGenerator.initialPosition();
        assert(position.equals(initialPosition));
    }
}