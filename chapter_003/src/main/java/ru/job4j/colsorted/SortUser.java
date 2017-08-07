package ru.job4j.colsorted;


import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * .Организовать сортировку User [#10034]..
 */
public class SortUser {

    /**
     * Метод который будет возвращать TreeSet пользователей, отсортированных по возрасту в порядке возрастания.
     *
     * @param list входящая коллекция
     * @return множество с отсортированным списком.
     */
    public Set<User> sort(List<User> list) {
        Set<User> userModels = new TreeSet<>();
        userModels.addAll(list);
        return userModels;
    }

    /**
     * Метод для сортировки пользователей в порядке увеличения длины имени.
     *
     * @param list входящая коллекция
     * @return отсортированный списко
     */

    public List<User> sortNameLength(List<User> list) {
        list.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        int lengthOne = o1.getName().length();
                        int lengthTwo = o2.getName().length();
                        int result;
                        if (lengthOne > lengthTwo) {
                            result = 1;
                        } else if (lengthOne < lengthTwo) {
                            result = -1;
                        } else {
                            result = 0;
                        }
                        return result;
                    }
                }
        );
        return list;
    }

    /**
     * Метод для сортировки пользователей по двум полям. Сначала по длине имени, затем по возрасту.
     *
     * @param list Входящий список
     * @return отсортированный список
     */
    public List<User> sortByAllFiedlds(List<User> list) {

        list.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        int lengthOne = o1.getName().length();
                        int lengthTwo = o2.getName().length();
                        int ageOne = o1.getAge();
                        int ageTwo = o2.getAge();
                        int result;

                        if (lengthOne > lengthTwo) {
                            result = 1;
                        } else if (lengthOne < lengthTwo) {
                            result = -1;
                        } else if (ageOne > ageTwo) {
                            result = 1;
                        } else if (ageOne < ageTwo) {
                            result = -1;
                        } else {
                            result = 0;
                        }

                        return result;
                    }
                }
        );
        return list;

    }

}
