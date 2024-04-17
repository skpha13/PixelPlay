package com.example.pixelplay.chess.base.attacks;

import com.example.pixelplay.chess.PositionGenerator;
import com.example.pixelplay.chess.base.Color;
import com.example.pixelplay.chess.base.NumberBoard;
import com.example.pixelplay.chess.base.Position;
import com.example.pixelplay.chess.controllers.AttackController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttackControllerTest {

    private static final String[] positionWhiteCheckKnight = new String[] {
            "........",
            ".k...q..",
            ".p......",
            "........",
            "....hP..",
            "....PP..",
            ".Q...K..",
            "........"
    };

    @Test
    public void attackedSquares() {
        Position position = PositionGenerator.customPosition(positionWhiteCheckKnight);
        AttackController attackController = new AttackController(position);
        NumberBoard attacked = attackController.getAttackBoard(Color.BLACK);
        NumberBoard reference = NumberBoard.fromNumberArray(new short[]{
                1, 1, 1, 0, 1, 1, 1, 0,
                2, 1, 3, 1, 1, 0, 1, 1,
                1, 1, 1, 1, 1, 2, 1, 0,
                0, 0, 1, 1, 0, 1, 1, 1,
                0, 0, 1, 0, 0, 1, 0, 0,
                0, 1, 1, 0, 0, 0, 1, 0,
                1, 0, 0, 1, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
        });

        assertEquals(reference.toString(),attacked.toString());
    }
}