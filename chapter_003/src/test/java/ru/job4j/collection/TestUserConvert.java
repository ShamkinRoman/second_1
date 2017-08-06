package ru.job4j.collection;


import org.junit.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест для проверки метода process в Классе UserConvert.
 */
public class TestUserConvert {
    /**
     * Проверка метода process.
     */
    @Test
    public void whenUser() {
        List<User> proverka = new ArrayList<>();
        User user1 = new User(1, "Вова", "Благовещенск");
        User user2 = new User(2, "Олег", "Хабаровск");
        User user3 = new User(3, "Слава", "Севастополь");

        proverka.add(user1);
        proverka.add(user2);
        proverka.add(user3);

        UserConvert userConvert = new UserConvert();

        HashMap<Integer, User> hashMap = userConvert.process(proverka);


        assertThat(hashMap.get(1), is(user1));

        assertThat(hashMap.get(2), is(user2));

        assertThat(hashMap.get(3), is(user3));

    }


}
