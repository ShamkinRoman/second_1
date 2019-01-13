package crudServlet;

import java.util.List;
import java.util.Map;

/*
This is interface for Storage class
 */
public interface Store {

    boolean add(User user);

    boolean update(User user);

    boolean delete(User user);

    List<User> findAll();

    User findById(int id);

    default Map<Integer, User> findAllInMap(){
        return null;
    }

}
