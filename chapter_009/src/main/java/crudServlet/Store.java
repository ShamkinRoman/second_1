package crudServlet;

import java.util.List;

/*
This is interface for Storage class
 */
public interface Store {

    void add(User user);

    boolean update(User user);

    boolean delete(User user);

    List<User> findAll();

    User findById(int id);

}
