package org.example.backend.connectFour;

public enum Player {
    RED(1), YELLOW(2);
    public final int value;

    Player(int value) {
        this.value = value;
    }

    public static Player fromValue(int value) {
        for (Player player : Player.values()) {
            if (player.value == value) {
                return player;
            }
        }
        throw new IllegalArgumentException("Invalid Player value: " + value);
    }
}
