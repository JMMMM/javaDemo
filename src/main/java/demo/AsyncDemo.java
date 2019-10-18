package demo;

import com.google.common.collect.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AsyncDemo {
    public static void main(String[] args) throws ParseException {
        List<Integer> result = Lists.newArrayList(1,2,3,4);
        System.out.println(result);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2019-10-09 00:00:00");
        Date date2 = sdf.parse("2019-10-09 23:59:59");
        System.out.println(date.getTime());
        System.out.println(date2.getTime());
    }
}
