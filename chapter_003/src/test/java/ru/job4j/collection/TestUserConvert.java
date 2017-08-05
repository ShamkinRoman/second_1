package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Up on 05.08.2017.
 */

public class TestUserConvert {
    /**
     * Тест для проверки класса UserConvert..
     * Создаем коллекцию, в нее добавляем три User.
     * После проверяем результат.
     */
    @Test
    public void whenUserSomeBody() {
        UserConvert userConvert = new UserConvert();
        User user = new User(1, "Vova", "Moscow");
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(new User(2, "Pasha", "Eka"));
        list.add(new User(3, "Slava", "Tomsk"));

        HashMap<Integer, User> proverka = userConvert.process(list);
        for (Integer id : proverka.keySet()) {
            System.out.println(String.format(" id   %s     name %s   city %s", id, proverka.get(id).name, proverka.get(id).city));
        }
    }
}
