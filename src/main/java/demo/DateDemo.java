package demo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Locale;

public class DateDemo {
    public static void main(String[] args) {
        Instant ins= new Date(System.currentTimeMillis()).toInstant();
        System.out.println(LocalDate.of(2018,11,1).compareTo(LocalDate.now()));
    }
}
