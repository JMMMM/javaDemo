package leetCode;

import scala.Char;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Demo3 {
    public int lengthOfLongestSubstring(String s) {
        char[] c = s.toCharArray();
        int start = 0;
        int maxLength = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < c.length; i++) {
            if (charMap.containsKey(c[i])) {
                int length = i - start;
                maxLength = maxLength > length ? maxLength : length;
                start = start > charMap.get(c[i]) ? start : charMap.get(c[i]) + 1;
            }
            charMap.put(c[i], i);
        }
        return (c.length - start > maxLength) ? c.length - start : maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new Demo3().lengthOfLongestSubstring("abba"));
    }
}
