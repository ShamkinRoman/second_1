package ru.job4j.bomberman;

import org.junit.Test;


public class GameTest {

    @Test
    public void startGame() {
        int[] sizeBoard = {5, 5};

        Game game = new Game(sizeBoard);
    }
}