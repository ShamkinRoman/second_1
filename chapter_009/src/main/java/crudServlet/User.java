package crudServlet;

import java.util.Date;
import java.util.Objects;

public class User {
    private Integer id;
    private String name;
    private String login;
    private String email;
    private String dataCreate;

    public User(int id, String name, String login, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.dataCreate = new Date().toString();

    }

    public Integer getId() {
        return id;
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

    public String getDataCreate() {
        return dataCreate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                name.equals(user.name) &&
                login.equals(user.login) &&
                email.equals(user.email) &&
                dataCreate.equals(user.dataCreate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, email, dataCreate);
    }

    @Override
    public String toString() {
        String result = String.format(" id = %s, name = %s, login = %s, email = %s,  dataCreate = %s .... <br><p>", getId(), getName(), getLogin(), getEmail(), getDataCreate());
        return result;
    }
}
