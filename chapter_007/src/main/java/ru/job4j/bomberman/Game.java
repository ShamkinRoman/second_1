package ru.job4j.bomberman;


import java.util.concurrent.locks.ReentrantLock;

/**
 * Реализовать игру бомбермен. Без графики. Без меню и без пользовательского ввода. Интересует только логика и дизайн.
 * 1. Есть игровое поле. Представляющее из себя массив ReentrantLock[][] board.
 * 2. Есть две нити. Нить эмулирует поведение героя. То есть герой стоит на клетке board. Клетка в этом случае должно быть заблокирована lock.lock();
 * 3. Герой должен каждую секунду двигаться на новую клетку. При движении надо занять новую клетку. то есть tryLock() - если не получилось в течении 500 мс. то изменить движение на другую клетку.
 * 4. Избежать появление deadlock.
 * 5. Поле board при движении героя не должно блокироваться целиком. Блокируется только ячейка.
 * 6. В коде использовать только immutable объекты. то есть все поле обозначит final.
 */

public class Game {

    final private ReentrantLock[][] board; // Массив блокировок.
    final private long timeGame = 7_000L; //long format. Сколько длится игра.

    public Game(int[] size) {

        this.board = new ReentrantLock[size[0]][size[1]];

        startGame();
    }

    public void startGame() {

        fiilBoard();    //инициализируем board.
        Integer[] startPosition = {2, 2}; //стартовая позиция фигуры(героя).

        Thread hero = new Thread(new BlankHero(startPosition, board));
        startPosition = new Integer[]{2, 1};
        Thread heroTwo = new Thread(new BlankHero(startPosition, board));

        heroTwo.setName("two");
        hero.setName("one");

        hero.start();
        heroTwo.start();

        try {
            Thread.sleep(timeGame);  //запускаем секундомер, через сколько остановить движение героя.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        endGame(hero); //Прерываем поток движения героя.
        endGame(heroTwo); //Прерываем поток движения героя.

        try {
            hero.join();
            heroTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End Game");
    }

    //    окончание потока.
    public void endGame(Thread thread) {

        thread.interrupt();

    }

    //Заполняем поле блокировок(инициализируем).
    public void fiilBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }

}
