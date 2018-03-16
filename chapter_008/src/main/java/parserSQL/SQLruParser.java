package parserSQL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

public class SQLruParser {
    private List<ItemSQL> itemSQLList = new ArrayList<>();
    private boolean validDate=true;
    private Timestamp lastTime=null;
    private Timestamp year;

    public void start() {


        setYear();
        MaxPageNumber maximumPage = new MaxPageNumber();
        String page = "http://www.sql.ru/forum/job-offers/";
        if (lastTime==null) {
            lastTime=year;
            System.out.println(String.format("Block if == null  %s", lastTime.getTime()));
        }
        for (int index = 1; index < 2; index++) {
            if(validDate) {
                parsePage(page, index);
            } else {
                break;
            }
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
                    Timestamp timestamp= null;
                    try {
                        timeElements = trValue.child(5).text();
                        ConvertTime convertTime = new ConvertTime(timeElements);
                        timestamp = convertTime.start();
//                        if (timestamp.getTime()<lastTime.getTime()){
//                            validDate=false;
//                            System.out.println(String.format("Block  timestamp.getTime()<lastTime.getTime() "));
//                        }

                    } catch (IndexOutOfBoundsException tee) {
                        System.out.println(tee);
                    }
                    String autorElement = null;
                    try {
                        autorElement = trValue.child(2).child(0).text();
                    } catch (IndexOutOfBoundsException ewe) {
                        autorElement = "Don't know";
                    }
                    String topicName = postValue.child(0).text();
                    if (topicName.toUpperCase().contains("JAVA") && !topicName.toUpperCase().contains("SCRIPT")) {
                        if (timestamp.getTime()>lastTime.getTime()) {
                            itemSQLList.add(new ItemSQL(link, topicName, autorElement, timestamp));
                        } else {
                            validDate=false;
                        }
                    }
                });
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void  setYear() {
        Calendar calendar = new GregorianCalendar();
        calendar.getTime();
        calendar.set(Calendar.MONTH,0);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        System.out.println(calendar);
        Date date = calendar.getTime();
        Instant instant = date.toInstant();
        year = Timestamp.from(instant);
        System.out.println(year+" ========");
    }

    public List<ItemSQL> getItemSQLList() {
        return itemSQLList;
    }
    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }
}