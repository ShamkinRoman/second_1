package parserSQL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;

public class MaxPageNumber {
    private int max = 0;
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
            e.printStackTrace();
        }
        return max;
    }
}
