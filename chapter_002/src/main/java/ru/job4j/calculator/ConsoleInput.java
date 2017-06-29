package ru.job4j.calculator;

import java.util.Scanner;

/**
 * Created by Up on 11.06.2017.
 */
public class ConsoleInput implements Input {
    /**Переменная scaner.*/
    private Scanner scanner = new Scanner(System.in);
    /**Ввод информации через консоль.
	*@param question question
	@return line
	*/
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /**.
     *
     * @param question question
     * @param range range
     * @return return
     */
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutExceptoin("Вы вышли за диапазон меню.");
        }

    }
}
