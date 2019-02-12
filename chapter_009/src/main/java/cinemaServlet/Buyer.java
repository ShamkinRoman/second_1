package cinemaServlet;

public class Buyer {
    private String place;
    private String name;
    private String phone;

    public Buyer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Buyer(String place, String name, String phone) {
        this.place = place;
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPlace() {
        return place;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "place='" + place + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
