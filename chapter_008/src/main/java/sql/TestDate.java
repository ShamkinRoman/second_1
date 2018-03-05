package sql;

import javafx.scene.input.DataFormat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

public class TestDate {
    public static void main(String[] args) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        LocalDateTime today;
        today = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println("DateTime : " + String.valueOf(today.format(dtf)));

        System.out.println("+++++++++++++");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println( String.valueOf(timestamp.getTime()));


        PathUrl pathUrl = new PathUrl();
        pathUrl.print();



    }
}
