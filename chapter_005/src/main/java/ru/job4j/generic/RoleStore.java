package ru.job4j.generic;

import java.util.NoSuchElementException;

public class RoleStore<Role extends Base> implements Store<Role> {


    SimpleArray<Role> simpleArray;

    final int defaultSize=10;

    public RoleStore() {
        this.simpleArray = new SimpleArray<>(defaultSize);

    }


    public void add (Role role) {
        simpleArray.add(role);
        //role.setId(String.valueOf(System.currentTimeMillis()));
    }

    public void update (String id, Role role) {

        simpleArray.update(findById(id), role);

    }

    public void delete (String id) {

        simpleArray.delete(findById(id));

    }

    public Integer findById(String id) {
Integer result = null;
        for (int i = 0; i < defaultSize; i++) {

            if (simpleArray.get(i) != null && simpleArray.get(i).equals(id)) {
                result = i;
                break;
            } else throw new NoSuchElementException("Не найдено");
        }
        return result;

    }

}
