package ru.job4j.bomberman;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BlankHero implements Runnable {

    private Map<String, Integer[]> mapMove = new HashMap<>(8); //Карта движений героя.
    private Integer[] newPosition = new Integer[2];
    private Integer[] oldPosition;
    private ReentrantLock[][] board;


    public BlankHero(Integer[] oldPosition, ReentrantLock[][] board) {
        this.oldPosition = oldPosition;
        this.board = board;
    }

//   Заполнение Карты движения героя.

    public void fillMapMove() {

        Integer[] upInteger = {0, -1};
        Integer[] leftInteger = {-1, 0};
        Integer[] downInteger = {0, 1};
        Integer[] rightInteger = {1, 0};

        mapMove.put("up", upInteger);
        mapMove.put("left", leftInteger);
        mapMove.put("down", downInteger);
        mapMove.put("right", rightInteger);
        mapMove.put("0", upInteger);
        mapMove.put("1", leftInteger);
        mapMove.put("2", downInteger);
        mapMove.put("3", rightInteger);
    }

    @Override
    public void run() {

        fillMapMove();

        String[] stanInput = {"up", "up", "right", "down", "left", "left", "left", "left", "up", "down", "right", "left", "up", "down", "right", "left"};

        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }

            // здесь у меня должен быть ввод даных с клавиатуры, н оон не требуется.
            //поэтомы значения беру из заранеее заданных.

            for (int i = 0; i < stanInput.length; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                moveFig(mapMove.get(stanInput[i]));

            }
        }

    }

    //Определяем, есть возможность сделать ход. true - да, false - нет.
    public boolean hasMove(Integer[] turn) {

        boolean result = true;

        try {
            if (board[turn[0]][turn[1]].isLocked()) {
                System.out.printf("%s  Хочу залочить ячейку %s %s, но она занята. \n", Thread.currentThread().getName(), turn[0], turn[1]);
                result = false;
            }
        } catch (ArrayIndexOutOfBoundsException w) {
            result = false;
            System.out.printf("%s  Хочу залочить ячейку %s %s, но нет такой. \n", Thread.currentThread().getName(), turn[0], turn[1]);
        }

        return result;
    }

    //Двигаем героя.
    public void moveFig(Integer[] turn) {

        try {

            newPosition[0] = oldPosition[0] + turn[0];
            newPosition[1] = oldPosition[1] + turn[1];


            if (hasMove(newPosition)) {

                if (board[newPosition[0]][newPosition[1]].tryLock(500, TimeUnit.MILLISECONDS)) {

                    System.out.printf("%s  Залочил ячейку %s %s \n", Thread.currentThread().getName(), newPosition[0], newPosition[1]);
                    Thread.sleep(1_000);
                    oldPosition = Arrays.copyOf(newPosition, 2);
                    board[oldPosition[0]][oldPosition[1]].unlock();

                }   //После unlock, ячейка на которой стоит еще герой,  уже не залочена.
            }
        } catch (InterruptedException e) {
            System.out.printf("%s был прерван !!! \n", Thread.currentThread().getName());
            Thread.currentThread().interrupt();
        }
    }
}
