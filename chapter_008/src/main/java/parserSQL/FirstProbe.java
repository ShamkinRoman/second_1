package parserSQL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FirstProbe {

    public static void main(String[] args) {
        List<Arcticle> arcticleList = new ArrayList<>();
        List<Element> timeList = new ArrayList<>();
        List<String> stringTime = new ArrayList<>();
        List<UrlNameTime> urlNameTimes = new ArrayList<>();

        try {
            Document document = Jsoup.connect("http://www.sql.ru/forum/job-offers").get();
            Elements elements = document.getElementsByAttributeValue("class", "postslisttopic");
            elements.forEach(element -> {
                Element helement = element;
                String url = helement.child(0).attr("href");
                String name = helement.child(0).text();
                arcticleList.add(new Arcticle(url, name));
            });

            Elements timeElement = document.getElementsByAttributeValue("class", "altCol");
            Iterator it = timeElement.iterator();
            int i = 0;
            int j = 0;
            while (it.hasNext()) {

                i++;

                if (i % 2 == 0) {
                    timeList.add((Element) it.next());
                } else {
                    it.next();
                }


            }

            timeList.forEach(time -> {
                stringTime.add(time.text());
            });

            for (int k = 0; k < stringTime.size(); k++) {
                urlNameTimes.add(new UrlNameTime(arcticleList.get(k).getUrl(), arcticleList.get(k).getName(), stringTime.get(k)));
            }

            urlNameTimes.forEach(System.out::println);

//            stringTime.forEach(System.out::println);
//              timeElement.forEach(time ->{
//                System.out.println(time.text());
//            });


//           arcticleList.forEach(System.out::println);


            Elements sort = document.getElementsByAttributeValue("class", "sort_options");

                Element element = sort.get(1).child(0).child(0).child(0);
                String find = element.text();
                System.out.println(find);
                String[] strings = find.split(" ");
            System.out.println(strings.length);

            System.out.println(strings[strings.length-1]);


            System.out.println("===== FORMATING=======");
            


        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("==========================");
//        System.out.println(String.format("url = %s", arcticleList.get(0).getUrl()));
//        System.out.println(String.format("name = %s", arcticleList.get(0).getName()));
    }

}


