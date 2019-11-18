package demo;

import java.util.Date;

/**
 * demo
 *
 * @author JM
 * @date 2019-11-18
 */
public class Demo9 {
    public static void main(String[] args) {
        String dateStr = "2019-11-07 00:00:00";
        Date date = DateUtil.getDateFromString(dateStr, "yyyy-MM-dd HH:mm:ss");
        System.out.println(date.getTime());
    }
}
