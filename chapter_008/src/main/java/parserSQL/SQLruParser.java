package parserSQL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

/*
 * Class for parsing page.
 */
public class SQLruParser {
    private List<ItemSQL> itemSQLList = new ArrayList<>(); // Storage Items.
    private boolean validDate = true; // For check validate Date.
    private Timestamp lastTime = null; // Time last record.
    private Timestamp year; // present Year.
    private static final Logger log = LoggerFactory.getLogger(CheckConnectToDataBase.class.getName());

    public void start() {
        setYear();
        MaxPageNumber maximumPage = new MaxPageNumber();
        String page = "http://www.sql.ru/forum/job-offers/";
        if (lastTime == null) {
            lastTime = year;
        }
        for (int index = 1; index < maximumPage.maxPage(); index++) {
            if (validDate) {
                parsePage(page, index);
            } else {
                break;
            }
        }
    }

    /*
     * Parsing page.
     */
    public void parsePage(String page, int index) {
        try {
            log.warn(String.format("Parsing page № %s. ", index));
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
                    Timestamp timestamp = null;
                    try {
                        timeElements = trValue.child(5).text();
                        ConvertTime convertTime = new ConvertTime(timeElements);
                        timestamp = convertTime.start();
                    } catch (IndexOutOfBoundsException tee) {
                        tee.printStackTrace();
                    }
                    String autorElement = null;
                    try {
                        autorElement = trValue.child(2).child(0).text();
                    } catch (IndexOutOfBoundsException ewe) {
                        autorElement = "Don't know";
                    }
                    String topicName = postValue.child(0).text();
                    if (topicName.toUpperCase().contains("JAVA") && !topicName.toUpperCase().contains("SCRIPT")) {
                        if (timestamp.getTime() > lastTime.getTime()) {
                            itemSQLList.add(new ItemSQL(link, topicName, autorElement, timestamp));
                            log.warn("Add new record.");
                        } else {
                            validDate = false;
                        }
                    }
                });
            });
            log.warn(String.format("End parsing page № %s",index));
        } catch (IOException e) {
            log.warn("I don't parsing page. ", e);
        }
    }

    /*
     * Set 01.01. presetn YEAR.
     */
    public void setYear() {
        Calendar calendar = new GregorianCalendar();
        calendar.getTime();
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        Date date = calendar.getTime();
        Instant instant = date.toInstant();
        year = Timestamp.from(instant);
    }

    public List<ItemSQL> getItemSQLList() {
        return itemSQLList;
    }

    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }
}