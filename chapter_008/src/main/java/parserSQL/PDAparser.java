package parserSQL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDAparser {
    public static void main(String[] args) {
        List<Arcticle> arcticleList = new ArrayList<>();
        try {
            Document document = Jsoup.connect("http://4pda.ru").get();
            Elements aelements = document.getElementsByAttributeValue("class","list-post-title");
            aelements.forEach(elements -> {
                Element helement = elements.child(0);
                String url = helement.attr("href");
                String name = helement.child(0).text();
                arcticleList.add(new Arcticle(url, name));

            } );

            arcticleList.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
