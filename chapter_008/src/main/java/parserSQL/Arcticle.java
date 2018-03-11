package parserSQL;

public class Arcticle {
    private String url;
    private String name;

    public Arcticle(String url, String name) {
        this.url = url;
        this.name = name;
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

    @Override
    public String toString() {
        return "Arcticle{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
