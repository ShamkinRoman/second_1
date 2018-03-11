package parserSQL;

public class UrlNameTime {
    private String url;
    private String name;
    private String time;

    public UrlNameTime(String url, String name, String time) {
        this.url = url;
        this.name = name;
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "UrlNameTime{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
