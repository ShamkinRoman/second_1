package crudServlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
This is class singleton and used MemoryStore as storage.
Also used methods for adding, updating, deleting and finding users in storage, but for used methods check validation not null data.
Used pattern for check email.
 */
public class ValidateService implements Validate {
    private static final ValidateService instance = new ValidateService();
    //   private static final MemoryStore memory = new MemoryStore().getInstance();
    private static final DBStore memory = new DBStore().getINSTANCE();

    public static ValidateService getInstance() {
        return instance;
    }

    public boolean validateUser(User user) {
        boolean result = false;
        if (user.getLogin() != null && user.getName() != null && validateEmail(user)) {
            result = true;
        }
        return result;
    }

    public boolean validateEmail(User user) {
        Pattern pattern = Pattern.compile("\\A[^@]+@([^@\\.]+\\.)+[^@\\.]+\\z");
        Matcher match = pattern.matcher(user.getEmail());
        return match.matches();
    }

    public boolean add(User user) {
        boolean result = false;
        if (validateUser(user)) {
            memory.add(user);
            result = true;
        }
        return result;
    }

    public boolean update(User user) {
        return memory.update(user);
    }

    public boolean delete(User user) {
        return memory.delete(user);
    }

    public Map<Integer, User> findAllMap() {
        return memory.findAllInMap();
    }

    public List<User> findAll() {
        return memory.findAll();
    }

    public boolean addPasswordRole(User user, String password, String role) {

        return memory.addPasswordRole(user, password, role);
    }

    public String isCheckPass(String login, String password) {
        return memory.isCheckPass(login, password);
    }

    public String getRoleByLogin(String login) {
        return memory.getRolebyLogin(login);
    }

    public String getRoleById(String id) {
        return memory.getRolebyId(id);
    }

    public void close() {
        try {
            memory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

