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

    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"place\":");
        sb.append("\"" + place + "\",");
        sb.append("\"name\":");
        sb.append("\"" + name + "\",");
        sb.append("\"phone\":");
        sb.append("\"" + phone + "\"}");
        return sb.toString();
    }
}
