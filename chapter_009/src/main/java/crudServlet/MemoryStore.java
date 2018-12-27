package crudServlet;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/*
Storage class. This is singleton and used  <CopyOnWriteArrayList>
Class include some method for adding, updating, deleting and finding users in storage.
There are methods including from interface STORE
 */
public class MemoryStore implements Store {
    private static final MemoryStore INSTANCE = new MemoryStore();
    private List<User> users = new CopyOnWriteArrayList<>();

    public MemoryStore getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public boolean update(User user) {
        boolean result = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.set(i, user);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        boolean result = false;
        User findUser = findById(user.getId());
        if (users.contains(findUser)) {
            users.remove(findUser);
            result = true;
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(int id) {
        User result = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                result = users.get(i);
                break;
            }
        }
        return result;
    }
}