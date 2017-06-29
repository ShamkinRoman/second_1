package ru.job4j.calculator;

/**
 * Created by Up on 29.06.2017.
 */
public class ValidateInput extends ConsoleInput {
    /**.
     *
     * @param question question
     * @param range range
     * @return return
     */
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutExceptoin gggg) {
                System.out.println("Введите число из диапазона!");


            } catch (NumberFormatException lala) {
                System.out.println("Введите число, а не буквы!!!");

            }
        } while (invalid);
        return value;
    }
}
