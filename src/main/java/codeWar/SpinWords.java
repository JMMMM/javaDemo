package codeWar;

import java.util.Collections;

public class SpinWords {
    public static String spinWords(String sentence) {
        String[] sentences = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String str : sentences) {
            char[] crs = str.toCharArray();
            int i = 0, j = crs.length - 1;
            if (crs.length>=5) {
                while (i <= j) {
                    char temp = crs[i];
                    crs[i++] = crs[j];
                    crs[j--] = temp;
                }
            }
            sb.append(new String(crs)+" ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(spinWords("Welcome"));
        System.out.println(spinWords("Hey fellow warriors"));
    }
}