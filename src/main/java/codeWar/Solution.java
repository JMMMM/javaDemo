package codeWar;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution {
    static String toCamelCase(String s) {
        StringBuilder sb = new StringBuilder();
        String[] splits = s.split("[\\_|\\-]");
        sb.append(splits[0]);
        Arrays.stream(splits).skip(1).forEach((cr) -> {
            char[] temps = cr.toCharArray();
            temps[0] = (char) (temps[0] >= 'a' ? temps[0] - 32 : temps[0]);
            sb.append(temps);
        });
        return sb.toString();
    }
//    best answer
//    static String toCamelCase(String s) {
//        Matcher m = Pattern.compile("[_|-](\\w)").matcher(s);
//        StringBuffer sb = new StringBuffer();
//        while (m.find()) {
//            m.appendReplacement(sb, m.group(1).toUpperCase());
//        }
//        return m.appendTail(sb).toString();
//    }

    public static void main(String[] args) {
        System.out.println(toCamelCase("the-Stealth-Warrior"));
    }
}
