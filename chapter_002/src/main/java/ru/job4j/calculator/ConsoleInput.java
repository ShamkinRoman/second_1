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
}
