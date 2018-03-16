package prime;

import parserSQL.ItemSQL;
import parserSQL.SQLruParser;

import java.sql.Timestamp;
import java.util.*;

public class ConTime {
    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(18, 03, 13, 18, 37, 00);
        long rr = calendar.getTimeInMillis();
        Timestamp timestamp = new Timestamp(rr);
        System.out.println(timestamp);
//        SQLruParser sqLruParser = new SQLruParser();
//        sqLruParser.start();
        splitTime();

    }

    public static void splitTime() {
        SQLruParser sqLruParser = new SQLruParser();
        sqLruParser.start();
        List<ItemSQL> items = sqLruParser.getItemSQLList();
        String[] timeDate = items.get(0).getName().split(", ");

        for (int i = 0; i < 2; i++) {
            System.out.println(timeDate[i]);
        }

        String[] date = timeDate[0].split(" ");
        String[] time = timeDate[1].split(":");

        HashMap<String, Integer> month = new HashMap<>();
        month.put("янв", 0);
        month.put("фев", 1);
        month.put("мар", 2);
        month.put("апр", 3);
        month.put("май", 4);
        month.put("июн", 5);
        month.put("июл", 6);
        month.put("авг", 7);
        month.put("сен", 8);
        month.put("окт", 9);
        month.put("ноя", 10);
        month.put("дек", 11);
        month.put("сегодня", 21);
        month.put("вчера", 31);


        Calendar calendar = new GregorianCalendar();



        calendar.set( Integer.parseInt("20"+date[2]), month.get(date[1]), Integer.parseInt(date[0]), Integer.parseInt(time[0]), Integer.parseInt(time[1]), 0);
        Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
        System.out.println("====");
        System.out.println(timestamp);

    }
}
