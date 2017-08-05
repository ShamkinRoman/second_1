package ru.job4j.collection;

import java.util.HashMap;

import java.util.List;


/**
 * Написать программу преобразования List в Map. [#10093]..
 */
public class UserConvert {
    /**
     * Метод который принимает в себя список пользователей и конвертирует его в Map.
     * с ключом Integer id и соответствующим ему User.
     *
     * @param list Коллекция с User
     * @return Коллекция Типа MapHash в которой ключ это id User  и User ему соответствующим
     */
    public HashMap<Integer, User> process(List<User> list) {

        HashMap<Integer, User> hashMap = new HashMap<>();

        for (User usersId : list) {
            hashMap.put(usersId.id, usersId);
        }
        return hashMap;
    }
}
