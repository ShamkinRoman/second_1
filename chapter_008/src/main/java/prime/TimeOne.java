package prime;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeOne {
    public static void main(String args[]) {
        SimpleDateFormat ft = new SimpleDateFormat("d MMM yy, HH:mm");
        String str = "11 мар 18, 10:50";
        Calendar calendar = new GregorianCalendar();
        calendar.getTime();
        Date date = new Date();
        System.out.println(date.toString());


        System.out.println( Integer.parseInt((String.valueOf(calendar.get(Calendar.MONTH))))+1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.YEAR));
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        System.out.print("Строка " + str + " распаршена как ");
        Date parsingDate;
        Timestamp timestamp;
        Instant instant;
        try {
            parsingDate = ft.parse(str);
            System.out.println(parsingDate);
            instant=parsingDate.toInstant();
            timestamp=Timestamp.from(instant);
            System.out.println("------");
            System.out.println(timestamp);


        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Нераспаршена с помощью " + ft);
        }
    }
}