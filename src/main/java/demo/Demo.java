package demo;

import java.util.Calendar;
import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(new Date());
        System.out.println(calender.get(Calendar.DAY_OF_YEAR));


    }
}
