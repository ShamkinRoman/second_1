package crudServlet;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryStore implements Store {
    private static final MemoryStore INSTANCE = new MemoryStore();
    private Map<Integer, User> storage = new ConcurrentHashMap<>();
    private volatile Integer id = 0;

    public MemoryStore getInstance(){
        return INSTANCE;
    }

    public Integer giveId(){
        return id++;
    }

    @Override
    public void add(User user) {
        storage.put(user.getId(),user);
    }

    @Override
    public boolean update(User user) {
        boolean result = false;
        if (storage.containsKey(user.getId())){
            storage.put(user.getId(),user);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        boolean result = false;
        if (storage.get(user.getId()).equals(user)) {

            storage.remove(user.getId());
            result =true;
        }
        return result;
    }

    @Override
    public Map<Integer, User> findAllInMap() {
        return storage;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }
}