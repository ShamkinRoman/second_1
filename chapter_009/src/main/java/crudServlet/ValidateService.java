package crudServlet;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
This is class singleton and used MemoryStore as storage.
Also used methods for adding, updating, deleting and finding users in storage, but for used methods check validation not null data.
Used pattern for check email.
 */
public class ValidateService {
    private static final ValidateService instance = new ValidateService();
    private static final MemoryStore memory = new MemoryStore().getINSTANCE();


    public static ValidateService getInstance() {
        return instance;
    }

    public boolean validateUser(User user) {
        boolean result = false;
        if (user.getId() != null && user.getDataCreate() != null && user.getLogin() != null && user.getName() != null && validateEmail(user)) {
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
        boolean result = false;
        if (validateUser(user)) {
            result = memory.update(user);
        }
        return result;
    }

    public boolean delete(User user) {
        boolean result = false;
        if (validateUser(user)) {
            result = memory.delete(user);
        }
        return result;
    }

    public User findById(Integer id) {
        return memory.findById(id);
    }

    public List<User> findAll() {
        return memory.findAll();
    }
}
