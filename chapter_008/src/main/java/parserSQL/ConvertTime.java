package parserSQL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/*
 * Class for parsing time from String and
 * convert time to TimeStamp.
 */
public class ConvertTime {
    private SimpleDateFormat ft = new SimpleDateFormat("d MMM yy, HH:mm");
    private String time;
    private static final Logger log = LoggerFactory.getLogger(CheckConnectToDataBase.class.getName());

    public ConvertTime(String string) {
        this.time = string;
    }

    /*
     * Check if string contain word today and yesterday.
     */
    public Timestamp start() {
        Timestamp timestamp;
        if (!time.contains("сегодня") && !time.contains("вчера")) {
            timestamp = covertTime(time);
        } else {
            timestamp = covertTime(convertYestodayToday(time));
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
            log.warn("I don't parse time", e);
        }
        return timestamp;
    }

    public String convertYestodayToday(String string) {
        Calendar calendar = new GregorianCalendar();
        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMM yy");
        String newString = null;
        if (string.contains("сегодня")) {
            date = calendar.getTime();
            newString = string.replace("сегодня", String.valueOf(simpleDateFormat.format(date)));
        }
        if (string.contains("вчера")) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            date = calendar.getTime();
            newString = string.replace("вчера", String.valueOf(simpleDateFormat.format(date)));
        }
        return newString;
    }
}
