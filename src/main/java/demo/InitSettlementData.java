package demo;

import com.google.common.collect.Maps;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * demo
 *
 * @author JM
 * @date 2019-10-18
 */
public class InitSettlementData {

    private static int ADD = 3;

    public static void main(String[] args) throws IOException {

        File file = new File("/Users/jimmy/Desktop/SQL/all.sql");
        File out = new File("/Users/jimmy/Desktop/SQL/all-" + DateUtil.getCurrentDate() + ".sql");
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        FileOutputStream fos = new FileOutputStream(out);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);

        String line;
        while ((line = br.readLine()) != null) {

            line = orderReplacer(line);

            line = dateReplacer(line);

            line = specialReplacer(line);
            bw.write(line);
        }

        br.close();
        bw.close();
    }


    private static Map<Long, Long> orderIdMap = Maps.newHashMap();
    private static String ORDER_REGEX = "815[0-9]{13,18}";
    private static Pattern order_pattern = Pattern.compile(ORDER_REGEX);

    public static String orderReplacer(String line) {

        Matcher orderMatcher = order_pattern.matcher(line);
        while (orderMatcher.find()) {
            String matcher = orderMatcher.group();
            long adder = (long) (ADD * Math.pow(10, matcher.length() - 3));
            Long oldId = Long.valueOf(matcher);
            Long newId = oldId + adder;
            Long nowId = orderIdMap.getOrDefault(oldId, newId);
            orderIdMap.put(oldId, nowId);
            line = line.replaceFirst(oldId.toString(), nowId.toString());
        }
        return line;
    }

    private static String DATE_REGEX = "[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))\\s+([0-1]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])";
    private static Pattern date_pattern = Pattern.compile(DATE_REGEX);

    public static String dateReplacer(String line) {
        Matcher dateMatcher = date_pattern.matcher(line);
        while (dateMatcher.find()) {
            String matcher = dateMatcher.group();
            Date date = DateUtil.getDateFromString(matcher, "yyyy-MM-dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, ADD);
            String now = DateUtil.formatDate(cal.getTime());
            line = line.replaceFirst(matcher, now);
        }
        return line;
    }


    public static String specialReplacer(String line) {
        if (line.contains("t_afs_order_header")) {
            line = line.replaceFirst("47", (ADD * 100000 + 47) + "");
        }
        if (line.contains("t_afs_order_item")) {
            line = line.replaceFirst("87", (ADD * 100000 + 87) + "");
            line = line.replaceFirst("47", (ADD * 100000 + 47) + "");
        }
        return line;
    }

}
