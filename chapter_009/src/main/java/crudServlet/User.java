package crudServlet;
import java.util.Date;
import java.util.Objects;
/*
Base class for User
 */
public class User {
    /*
    Some different variable for description class
     */
    private Integer id;
    private String name;
    private String login;
    private String email;
    private String dataCreate;
    /*
    Default construct
     */
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
        return  name.equals(user.name) &&
                login.equals(user.login) &&
                email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, email);
    }

    @Override
    public String toString() {
        String result = String.format(" id = %s, name = %s, login = %s, email = %s,  dataCreate = %s ", getId(), getName(), getLogin(), getEmail(), getDataCreate());
        return result;
    }
}
