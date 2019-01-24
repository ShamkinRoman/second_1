package crudServlet;

import java.util.HashMap;
import java.util.Map;

public class ValidateStub implements Validate {
    private final Map<Integer, User> store = new HashMap<>();
    private int ids = 0;

    @Override
    public boolean add(User user) {
        this.store.put(++ids, user);
        return true;
    }
    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public Map<Integer, User> findAllMap() {
        return this.store;
    }
}
