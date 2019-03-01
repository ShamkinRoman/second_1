package httpPages;

public class Customer {
    private String name;
    private String surname;
    private String email;
    private String sex;

    public Customer(String name, String surname, String email, String sex) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getSex() {
        return sex;
    }
}
