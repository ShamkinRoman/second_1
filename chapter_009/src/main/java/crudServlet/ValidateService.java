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
                validateEmail(user) ) {
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

        for ( User userFind : memory.findAllInMap().values()) {
            if (userFind.equals(user)){
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
    /*
    very big crutch (костыль).
    On start copy user from Collection in new exemplar.
    Delete coped user from Collection.
    Create new user with new characteristic, if it create, then new LOGIN and EMAIL uniq.
    Delete creating user.
    Create new User with old characteristic.
    Update user.
    It my solve, because I have a problems with rename login or email.
    I have one side two identically logins or email, either user don't change login or email.
     */

    public boolean update(User user) {
        boolean result = false;
        Integer id = user.getId();
        String name = memory.findAllInMap().get(id).getName();
        String login = memory.findAllInMap().get(id).getLogin();
        String email = memory.findAllInMap().get(id).getEmail();
        User inspectUser = new User(id, name, login, email);
        User userDelete = memory.findAllInMap().get(id);
        memory.delete(userDelete);
        if (add(user)){
            memory.delete(user);
            memory.add(inspectUser);
            memory.update(user);
            result=true;
        } else {
            memory.add(inspectUser);
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

    public Map<Integer, User> findAllMap(){
        return memory.findAllInMap();
    }

    public Integer giveId(){
        return  memory.giveId();
    }

    public List<User> findAll() {
        return memory.findAll();
    }
}