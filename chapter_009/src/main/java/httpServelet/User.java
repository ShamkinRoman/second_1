package httpServelet;

import java.sql.Timestamp;
import java.util.Objects;

public class User {
    private String id;
    private String name;
    private String login;
    private String email;
    private String dateCreate;

    public User(String id, String name, String login, String email, String dateCreate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.dateCreate = dateCreate;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                name.equals(user.name) &&
                login.equals(user.login) &&
                email.equals(user.email) &&
                dateCreate.equals(user.dateCreate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, email, dateCreate);
    }
}
