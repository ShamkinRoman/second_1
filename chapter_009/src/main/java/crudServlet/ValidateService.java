package crudServlet;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
This is class singleton and used MemoryStore as storage.
Also used methods for adding, updating, deleting and finding users in storage, but for used methods check validation not null data.
Used pattern for check email.
 */
public class ValidateService {
    private static final ValidateService instance = new ValidateService();
    private static final MemoryStore memory = new MemoryStore().getInstance();


    public static ValidateService getInstance() {
        return instance;
    }

    public boolean validateUser(User user) {
        boolean result = false;
        if (user.getId() != null && user.getDataCreate() != null && user.getLogin() != null && user.getName() != null &&
                validateEmail(user)) {
            result = true;
        }
        return result;
    }

    public boolean validateEmail(User user) {
        Pattern pattern = Pattern.compile("\\A[^@]+@([^@\\.]+\\.)+[^@\\.]+\\z");
        Matcher match = pattern.matcher(user.getEmail());
        return match.matches();
    }

    public boolean checkUserExists(User user) {
        boolean result = false;

        for (User userFind : memory.findAllInMap().values()) {
            if (userFind.equals(user)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean checkExistsEmail(User user) {
        boolean result = false;
        for (User userFind : memory.findAllInMap().values()) {
            if (userFind.getEmail().equals(user.getEmail())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean checkExistsLogin(User user) {
        boolean result = false;
        for (User userFind : memory.findAllInMap().values()) {
            if (userFind.getLogin().equals(user.getLogin())) {
                result = true;
                break;
            }
        }
        return result;
    }


    public boolean add(User user) {
        boolean result = false;
        if (validateUser(user) && !checkUserExists(user) && !checkExistsEmail(user) && !checkExistsLogin(user)) {
            memory.add(user);
            result = true;
        }
        return result;
    }

    /**
     * Method for update user attributes.
     *
     * @param user User with old ID and new attributes.
     * @return TRUE if updating successful, else FALSE.
     */
    public boolean update(User user) {
        boolean result = false;
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = validateUser(user);
        User chekedUser = memory.findAllInMap().get(user.getId());
        /**
         * if login not belongs to us, check for free login.
         */
        if (!user.getLogin().equals(chekedUser.getLogin())) {
            flag1 = !checkExistsLogin(user);
        }
        /**
         * if email not belongs to us, check for free email.
         */
        if (!user.getEmail().equals(chekedUser.getEmail())) {
            flag2 = !checkExistsEmail(user);
        }

        if (flag1 && flag2 && flag3) {
            memory.update(user);
            result = true;
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

    public Map<Integer, User> findAllMap() {
        return memory.findAllInMap();
    }

    public Integer giveId() {
        return memory.giveId();
    }

    public List<User> findAll() {
        return memory.findAll();
    }
}