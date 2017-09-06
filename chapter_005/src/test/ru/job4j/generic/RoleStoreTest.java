package ru.job4j.generic;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Тест для проверки класса RoleStore.
 */
public class RoleStoreTest {

    /**
     * тест №1, для проверки метода add.
     */
    @Test
    public void whenAddItem() {

        RoleStore roleStore = new RoleStore();

        Role role1 = new Role();
        Role role2 = new Role();

        roleStore.add(role1);
        role1.setId("1234");
        roleStore.add(role2);
        role2.setId("2222");


        assertThat(roleStore.get("1234"), is(role1));
        assertThat(roleStore.get("2222"), is(role2));

        try {
            roleStore.get("5");
        } catch (NoSuchElementException nsee) {
            assertThat(nsee.getMessage(), is("Не найдено"));
        }

    }

    /**
     * Тест №2, для проверки метода delete.
     */
    @Test
    public void whenDeleteItem() {

        RoleStore roleStore = new RoleStore();

        Role role1 = new Role();
        Role role2 = new Role();

        roleStore.add(role1);
        role1.setId("1234");
        roleStore.add(role2);
        role2.setId("2222");

        assertThat(roleStore.get("1234"), is(role1));
        assertThat(roleStore.get("2222"), is(role2));

        roleStore.delete("2222");

        try {
            roleStore.get("2222");
        } catch (NoSuchElementException nsee) {
            assertThat(nsee.getMessage(), is("Не найдено"));
        }

    }

    /**
     * Тест №3, для проверки метода update.
     */
    @Test
    public void whenUpdateItem() {

        RoleStore roleStore = new RoleStore();

        Role role1 = new Role();
        Role role2 = new Role();

        roleStore.add(role1);
        role1.setId("1234");

        assertThat(roleStore.get("1234"), is(role1));

        roleStore.update("1234", role2);

        assertThat(roleStore.get("1234"), is(role2));

    }

}