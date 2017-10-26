package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {

    private class SimpleUser {

        private String name;
        private int children;
        Calendar birthday;

        public SimpleUser(String name, int children, Calendar birthday) {
            this.name = name;
            this.children = children;
            this.birthday = birthday;
        }
    }

    private class HashUser {

        private String name;
        private int children;
        Calendar birthday;

        public HashUser(String name, int children, Calendar birthday) {
            this.name = name;
            this.children = children;
            this.birthday = birthday;
        }


        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + children;
            result = 31 * result + birthday.hashCode();
            return result;
        }
    }

    private class EqualUser {

        private String name;
        private int children;
        Calendar birthday;

        public EqualUser(String name, int children, Calendar birthday) {
            this.name = name;
            this.children = children;
            this.birthday = birthday;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            EqualUser equalUser = (EqualUser) o;

            if (children != equalUser.children) return false;
            if (name != null ? !name.equals(equalUser.name) : equalUser.name != null) return false;
            return birthday != null ? birthday.equals(equalUser.birthday) : equalUser.birthday == null;
        }

    }

    private class HashEqualUser {

        private String name;
        private int children;
        Calendar birthday;

        public HashEqualUser(String name, int children, Calendar birthday) {
            this.name = name;
            this.children = children;
            this.birthday = birthday;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            HashEqualUser that = (HashEqualUser) o;

            if (children != that.children) return false;
            if (name != null ? !name.equals(that.name) : that.name != null) return false;
            return birthday != null ? birthday.equals(that.birthday) : that.birthday == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + children;
            result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
            return result;
        }
    }


    @Test
    public void whenNotOverradeHashAndEqual() {
        SimpleUser user1 = new SimpleUser("Roman", 2, new GregorianCalendar(1988, 12, 12));
        SimpleUser user2 = new SimpleUser("Roman", 2, new GregorianCalendar(1988, 12, 12));

        Map<SimpleUser, Object> map = new HashMap<>();

        map.put(user1, 1);
        map.put(user2, 2);

        assertThat(map.size(), is(2));

    }

    @Test
    public void whenOverradeHash() {
        HashUser user1 = new HashUser("Roman", 2, new GregorianCalendar(1988, 12, 12));
        HashUser user2 = new HashUser("Roman", 2, new GregorianCalendar(1988, 12, 12));

        Map<HashUser, Object> map = new HashMap<>();

        map.put(user1, 1);
        map.put(user2, 2);

        assertThat(map.size(), is(2));

    }

    @Test
    public void whenOverrideEqual() {

        EqualUser user1 = new EqualUser("Roman", 2, new GregorianCalendar(1988, 12, 12));
        EqualUser user2 = new EqualUser("Roman", 2, new GregorianCalendar(1988, 12, 12));

        Map<EqualUser, Object> map = new HashMap<>();

        map.put(user1, 1);
        map.put(user2, 2);

        assertThat(map.size(), is(2));


    }


    @Test
    public void whenOverradeHashAndEqual() {
        HashEqualUser user1 = new HashEqualUser("Roman", 2, new GregorianCalendar(1988, 12, 12));
        HashEqualUser user2 = new HashEqualUser("Roman", 2, new GregorianCalendar(1988, 12, 12));
        HashEqualUser user3 = new HashEqualUser("Roman", 2, new GregorianCalendar(1988, 12, 12));

        Map<HashEqualUser, Object> map = new HashMap<>();

        map.put(user1, 1);
        map.put(user2, 2);
        map.put(user3, 3);

        assertThat(map.size(), is(1));

    }

}

