package crudServlet;

import java.util.List;

public interface Store {

    void add(User user);

    boolean update(User user);

    boolean delete(User user);

    List<User> findAll();

    User findById(int id);

}
