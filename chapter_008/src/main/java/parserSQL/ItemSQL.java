package parserSQL;

import java.sql.Timestamp;

public class ItemSQL {
    private String url;
    private String name;
    private String autor;
    private Timestamp time;

    public ItemSQL(String url, String name, String autor, Timestamp time) {
        this.url = url;
        this.name = name;
        this.autor = autor;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ItemSQL{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", autor='" + autor + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
