package parserSQL;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ConvertTime {
    private SimpleDateFormat ft = new SimpleDateFormat("d MMM yy, HH:mm");
    private ItemSQL item;

    public ConvertTime(ItemSQL item) {
        this.item = item;
    }

    public Timestamp start() {
        Timestamp timestamp;
            if (!item.getTime().contains("сегодня") && !item.getTime().contains("вчера")) {
                timestamp = covertTime(item.getTime());
            } else {
                timestamp = covertTime(convertYestodayToday(item.getTime()));
            }
            return timestamp;
    }

    public Timestamp covertTime(String string) {
        Date parsingDate;
        Timestamp timestamp = null;
        Instant instant;
        try {
            parsingDate = ft.parse(string);
            instant = parsingDate.toInstant();
            timestamp = Timestamp.from(instant);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Нераспаршена с помощью " + ft);
        }
        System.out.println(timestamp);
        return timestamp;
    }

    public String convertYestodayToday(String string) {
        Calendar calendar = new GregorianCalendar();
        Date date = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMM yy");
        String newString = null;
        if (string.contains("сегодня")) {
            newString = string.replace("сегодня",String.valueOf(simpleDateFormat.format(date)));
        }
        if (string.contains("вчера")) {
            calendar.add(Calendar.DAY_OF_MONTH,-1);
            newString = string.replace("вчера",String.valueOf(simpleDateFormat.format(date)));
        }
        return newString;
    }
}
