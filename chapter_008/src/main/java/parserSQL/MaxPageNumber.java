package parserSQL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

/*
 * Class for detect all pages on forum  http://www.sql.ru/forum/job-offers/
 */
public class MaxPageNumber {
    private int max = 0;
    private static final Logger log = LoggerFactory.getLogger(CheckConnectToDataBase.class.getName());

    public int maxPage() {
        try {
            Document document = Jsoup.connect("http://www.sql.ru/forum/job-offers/1").get();
            Elements elements = document.getElementsByAttributeValue("class", "sort_options");
            elements.forEach(value -> {
                Elements tdElements = value.getElementsByAttributeValue("style", "text-align:left");
                tdElements.forEach(tdValue -> {
                    max = Integer.parseInt(tdValue.child(10).text());
                });
            });
        } catch (IOException e) {
            log.warn("You have a problem in MaxPage class", e);
        }
        return max;
    }
}
