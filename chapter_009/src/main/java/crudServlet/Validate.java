package crudServlet;

import java.util.Map;

public interface Validate {
    public boolean add(User user) ;
    public boolean update(User user);
    public boolean delete(User user);
    public Map<Integer, User> findAllMap();
}
