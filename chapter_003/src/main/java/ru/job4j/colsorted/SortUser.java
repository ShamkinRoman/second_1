package ru.job4j.colsorted;

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

}
