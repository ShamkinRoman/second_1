package parserSQL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SQLruParser {
    private List<ItemSQL> itemSQLList = new ArrayList<>();
    private boolean lastRecordflag=true;
    private Timestamp lastTime;

    public void start() {

        MaxPageNumber maximumPage = new MaxPageNumber();
        String page = "http://www.sql.ru/forum/job-offers/";
        for (int index = 1; index < 2; index++) {
            parsePage(page, index);
        }
    }

    public void parsePage(String page, int index) {
        try {
            System.out.println(String.format("Обрабатываю %s страницу", index));
            Document document = Jsoup.connect(page + String.valueOf(index)).get();
            Elements trElements = document.getElementsByTag("tr");
            trElements.forEach(trValue -> {
                Elements postElements = trValue.getElementsByAttributeValue("class", "postslisttopic");
                postElements.forEach(postValue -> {
                    String link = null;
                    try {
                        link = postValue.child(0).attr("href");
                    } catch (IndexOutOfBoundsException pve) {
                        link = "Don't know link";
                    }
                    String timeElements = null;
                    try {
                        timeElements = trValue.child(5).text();

                    } catch (IndexOutOfBoundsException tee) {
                        timeElements = "Don't know time";
                    }
                    String autorElement = null;
                    try {
                        autorElement = trValue.child(2).child(0).text();
                    } catch (IndexOutOfBoundsException ewe) {
                        autorElement = "Don't know";
                    }
                    String topicName = postValue.child(0).text();
                    if (topicName.toUpperCase().contains("JAVA") && !topicName.toUpperCase().contains("SCRIPT")) {
                        itemSQLList.add(new ItemSQL(link, topicName, autorElement, timeElements));
                    }
                });
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ItemSQL> getItemSQLList() {
        return itemSQLList;
    }
    public boolean checkLstTime(Timestamp oldValue, Timestamp newValue){

        return (oldValue.getTime()<newValue.getTime());

    }

    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }
}

