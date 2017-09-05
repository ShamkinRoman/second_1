package ru.job4j.generic;

import org.junit.Test;

import static org.junit.Assert.*;

public class RoleStoreTest {
    @Test
    public void addItem()  {

        RoleStore roleStore = new RoleStore();

        Role role1 = new Role();
        Role role2 = new Role();

        roleStore.add(role1);
        role1.setId("1234");
        roleStore.add(role2);
        role2.setId("2222");

        System.out.println(roleStore.toString());







    }


}