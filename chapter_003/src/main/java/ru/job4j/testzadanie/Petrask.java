package ru.job4j.testzadanie;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Comparator;


/**
 * Created by Up on 14.08.2017.
 */
public class Petrask {

    /**
     * Метод для добавления необъявленных кодов в Справочнике.
     * Выбрано множество, для исключения дублтрующих элементов.
     * Строку преобразуем в массив символов.
     * Анализируем массив и делим на подстроки.
     * Разделителем является символ \.
     *
     * @param string строка которую зазбивают.
     * @return Множество строк полученных разбитием входящей строки.
     */

    public Set<String> setRazbivka(String string) {

        char[] start = string.toCharArray();
        Set<String> itog = new TreeSet<>();
        char divided = 92; //Разделитель на подстроки.

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < start.length; i++) {
            if (start[i] == divided) {

                itog.add(stringBuilder.toString());
                stringBuilder.append(start[i]);

            } else {
                stringBuilder.append(start[i]);
            }
        }

        itog.add(stringBuilder.toString()); // Необходимо добавить строку в множество, т.к. нет завершающего \ .

        return itog;


    }

    /**
     * Вспомагательный метод для сравнения в Компараторе.
     * Отличается от предыдущего в том, что делеит уже заполненный список.
     *
     * @param string Входящая строка.
     * @return Коллекция ЛИСТ с подстроками.
     */

    public List<String> razbivka(String string) {
        char[] start = string.toCharArray();
        List<String> itog = new ArrayList<>();
        char divided = 92;

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < start.length; i++) {
            if (start[i] == divided) {
                itog.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            } else {
                stringBuilder.append(start[i]);
            }
        }
        itog.add(stringBuilder.toString());


        return itog;
    }

    /**
     * Метод сравнения строк. Служит вспомогательным методом в методе setSortedHigh.
     * Если списки имеют равные размеры, сравниваются значения элементов с одинаковыми индексами.
     * Если первый больше то +1, если равны 0, если первый меньше второго -1.
     *
     * Если у списков разные размеры, при одинаковых значениях, возвращается -1 если размер первого меньше,
     * либо равна размеру второго
     * Если размер второго больше первого то +1.
     *
     * @param one Первый список.
     * @param two Второй список.
     * @return результат сравнения.
     */

    public int sravnenieHigh(List<String> one, List<String> two) {
        int sizeOne = one.size();
        int sizeTwo = two.size();

        int dlina = sizeOne <= sizeTwo ? sizeOne : sizeTwo;

        int rezult = 0;

        for (int i = 0; i < dlina; i++) {
            rezult = one.get(i).compareTo(two.get(i));
            if (rezult != 0) {
                break;
            }
        }

        if (rezult == 0) {
            rezult = sizeOne <= sizeTwo ? -1 : 1;
        }
        return rezult;
    }

    /**
     * Метод сравнения строк. Служит вспомогательным методом в методе setSortedLow.
     *
     * Незначительно переписанный предыдущий метод.
     *
     *  Если списки имеют равные размеры, сравниваются значения элементов с одинаковыми индексами.
     * Если первый больше то -1, если равны 0, если первый меньше второго +1.
     *
     * Если у списков разные размеры, при одинаковых значениях, возвращается +1 если размер первого меньше,
     * либо равна размеру второго
     * Если размер второго больше первого то -1.
     *
     * @param one Первый список.
     * @param two Второй список.
     * @return результат сравнения.
     */

    public int sravnenieLow(List<String> one, List<String> two) {
        int sizeOne = one.size();
        int sizeTwo = two.size();

        int dlina = sizeOne <= sizeTwo ? sizeOne : sizeTwo;

        int rezult = 0;
        int flag = 0;

        for (int i = 0; i < dlina; i++) {
            rezult = one.get(i).compareTo(two.get(i));
            if (rezult != 0) {
                break;
            }
            flag++;
        }
        if (rezult == 0 && flag != 0) {
            rezult = sizeOne <= sizeTwo ? 1 : -1;
        }
        return rezult;
    }

    /**
     * Метод для сортировки по убыванию.
     *
     * @param string Массив строк подлежащий сортировке.
     * @return коллекция Лист отсортированная по убыванию.
     */

    public List<String> setSortedLow(String[] string) {

        Set<String> itogov = new TreeSet<>();

        Set<String> temp = new TreeSet<>();

        //Пробегаем по элементам входящего массива.
        //Для получения подстрок в множество.
        for (String value : string) {
            temp = setRazbivka(value);

            //Вложенный цикл, для добавления элементов в множество itogov.
            for (String valueIn : temp) {
                itogov.add(valueIn);
            }
        }


        Iterator iterator = itogov.iterator();

        List<String> result = new ArrayList<>();


        while (iterator.hasNext()) {

            result.add(iterator.next().toString());

        }
        result.sort(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {

                        List<String> first = razbivka(o1);
                        List<String> second = razbivka(o2);

                        int otvet = sravnenieLow(first, second);
                        return otvet * -1;
                    }
                }
        );


        return result;
    }

    /**
     *  Метод для сортировки по возрастанию.
     *
     * @param string Массив строк подлежащий сортировке.
     * @return коллекция Лист отсортированная по Возрастанию.
     */

    public List<String> setSortedHigh(String[] string) {

        Set<String> itogov = new TreeSet<>();

        Set<String> temp = new TreeSet<>();

        for (String value : string) {
            temp = setRazbivka(value);

            for (String valueIn : temp) {
                itogov.add(valueIn);
            }
        }


        Iterator iterator = itogov.iterator();

        List<String> result = new ArrayList<>();


        while (iterator.hasNext()) {

            result.add(iterator.next().toString());

        }


        result.sort(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {

                        List<String> first = razbivka(o1);
                        List<String> second = razbivka(o2);

                        int otvet = sravnenieHigh(first, second);
                        return otvet;
                    }
                }
        );


        return result;
    }
}
