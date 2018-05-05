package httpServelet;

import java.sql.Timestamp;

public class User {
    private String name;
    private String login;
    private String email;
    private Timestamp dateCreate;

    public User(String name, String login, String email, Timestamp dateCreate) {
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

    public Timestamp getDateCreate() {
        return dateCreate;
    }
}
