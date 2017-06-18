package ru.job4j.strategy;

/**
 * Created by Up on 18.06.2017.
 */
public class Paint {
    /**
     * Метод draw, рисует фигуру.
     * @param shape фигура
     */
    public void draw(Shape shape)   {
        System.out.println(shape.pic());
    }
}
